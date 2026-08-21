import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Callable;

public class CircuitBreakerManager {

    Map<String, CircuitBreaker> store = new HashMap<>();

    public void register(String serviceName, int failureThreshold,
                         long windowDurationMs, long resetTimeoutMs) {
        if (serviceName == null || serviceName.isBlank()) {
            throw new IllegalArgumentException("Service name cannot be null");
        }
        if (failureThreshold <= 0) {
            throw new IllegalArgumentException("Failure threshold cannot be less than or 0");
        }
        if (windowDurationMs <= 0) {
            throw new IllegalArgumentException("Window duration cannot be less than or 0");
        }
        if (resetTimeoutMs <= 0) {
            throw new IllegalArgumentException("Reset timeout cannot be less than or 0");
        }
        store.put(serviceName, new CircuitBreaker(serviceName, failureThreshold, windowDurationMs, resetTimeoutMs));
        System.out.println("[Manager] Registered: " + serviceName +
                " threshold=" + failureThreshold +
                " window=" + windowDurationMs + "ms" +
                " timeout=" + resetTimeoutMs + "ms");
    }

    public <T> T execute(String serviceName, Callable<T> operation) throws Exception {
        CircuitBreaker circuitBreaker = getOrThrow(serviceName);
        return circuitBreaker.execute(operation);
    }

    public CircuitBreaker.State getStatus(String serviceName) {
        return getOrThrow(serviceName).getPublicState();
    }

    private CircuitBreaker getOrThrow(String serviceName) {
        CircuitBreaker circuitBreaker = store.get(serviceName);
        if (circuitBreaker == null) {
            throw new IllegalArgumentException(
                    "Service not registered: " + serviceName);
        }
        return circuitBreaker;
    }

    static class CircuitBreaker {

        enum State {CLOSED, OPEN, HALF_OPEN};

        private final String serviceName;
        private final int threshold;
        private final long windowTimeoutMs;
        private final long resetTimeoutMs;

        private State state = State.CLOSED;
        private long lastOpenedAt = 0;

        private final Queue<Long> failureWindow = new LinkedList<>();

        CircuitBreaker(String serviceName, int threshold, long windowTimeoutMs, long resetTimeoutMs) {
            this.serviceName = serviceName;
            this.threshold = threshold;
            this.windowTimeoutMs = windowTimeoutMs;
            this.resetTimeoutMs = resetTimeoutMs;
        }

        public State getPublicState() {
            return state;
        }

        public <T> T execute(Callable<T> operation) throws Exception {
            switch(resolveStatus()) {
                case OPEN: throw new IllegalStateException(
                                "[" + serviceName + "] Circuit OPEN. Retry in "
                                        + timeUntilReset() + "ms");

                case HALF_OPEN:
                    return tryHalfOpen(operation);
                case CLOSED:
                default:
                    return tryClose(operation);
            }
        }

        private <T> T tryClose(Callable<T> operation) throws Exception {
            try {
                T result = operation.call();
                onSuccess();
                return result;
            } catch (Exception e) {
                onFailure();
                throw e;
            }
        }

        private <T> T tryHalfOpen(Callable<T> operation) throws Exception {
            try {
                T result = operation.call();
                onSuccess();
                return result;
            } catch (Exception e) {
                lastOpenedAt = System.currentTimeMillis();
                transitionTo(State.OPEN);
                throw e;
            }
        }

        private void onSuccess() {
            if (state == State.HALF_OPEN) {
                failureWindow.remove();
                transitionTo(State.CLOSED);
            }
        }

        private void onFailure() {
            long now = System.currentTimeMillis();
            evictOldFailure(now);
            failureWindow.add(now);
            System.out.println("[" + serviceName + "] Failure recorded. " +
                    "Recent failures: " + failureWindow.size() + "/" + threshold);
            if (failureWindow.size() >= threshold) {
                lastOpenedAt = now;
                transitionTo(State.OPEN);
            }
        }

        private void transitionTo(State next) {
            System.out.println("[" + serviceName + "] " + state + " → " + next);
            state = next;
        }

        private void evictOldFailure(long now) {
            long cutOff = now - windowTimeoutMs;
            while (!failureWindow.isEmpty() && failureWindow.peek() < cutOff) {
                failureWindow.poll();
            }
        }

        private State resolveStatus() {
            if (state == State.OPEN && timeUntilReset() <= 0) {
                state = State.HALF_OPEN;
                System.out.println("[" + serviceName + "] " + state + " → " + State.HALF_OPEN);
            }
            return state;
        }

        private long timeUntilReset() {
            return resetTimeoutMs - (System.currentTimeMillis() - lastOpenedAt);
        }

        public int getWindowSize() { return failureWindow.size(); }

        @Override
        public String toString() {
            return "CircuitBreaker[service=" + serviceName +
                    ", state=" + state +
                    ", recentFailures=" + failureWindow.size() +
                    "/" + threshold + "]";
        }
    }
    public static void main(String[] args) throws Exception {
        CircuitBreakerManager manager = new CircuitBreakerManager();

        // payment: trips after 3 failures within 5 seconds
        // inventory: trips after 2 failures within 3 seconds
        manager.register("payment-service",   3, 5000, 2000);
        manager.register("inventory-service", 2, 3000, 1000);

        // ── Scenario 1: failures trip payment after threshold ──
        System.out.println("\n── Scenario 1: payment trips after 3 failures ──");
        for (int i = 1; i <= 3; i++) {
            try {
                manager.execute("payment-service",
                        () -> { throw new RuntimeException("Payment down"); });
            } catch (RuntimeException e) {
                System.out.println("Failure " + i + ": " + e.getMessage());
            }
        }

        // ── Scenario 2: payment OPEN, inventory independent ───
        System.out.println("\n── Scenario 2: services fail independently ──");
        try {
            manager.execute("payment-service", () -> "ok");
        } catch (IllegalStateException e) {
            System.out.println("Payment blocked: " + e.getMessage());
        }
        String inv = manager.execute("inventory-service", () -> "inventory ok");
        System.out.println("Inventory still works: " + inv);

        // ── Scenario 3: old failures expire out of window ─────
        System.out.println("\n── Scenario 3: failures expire out of window ──");
        manager.register("search-service", 3, 2000, 1000);

        try {
            manager.execute("search-service",
                    () -> { throw new RuntimeException("down"); });
        } catch (Exception e) {
            System.out.println("Failure 1: " + e.getMessage());
        }

        try {
            manager.execute("search-service",
                    () -> { throw new RuntimeException("down"); });
        } catch (Exception e) {
            System.out.println("Failure 2: " + e.getMessage());
        }

        System.out.println("Search state: " + manager.getStatus("search-service"));
        System.out.println("Waiting 3s for failures to expire out of window...");
        Thread.sleep(3000);

        try {
            manager.execute("search-service",
                    () -> { throw new RuntimeException("down"); });
        } catch (Exception e) {
            System.out.println("One failure but window expired — state: "
                    + manager.getStatus("search-service"));
        }

        // ── Scenario 4: recovery after timeout ────────────────
        System.out.println("\n── Scenario 4: payment recovers ──");
        Thread.sleep(2100);
        String result = manager.execute("payment-service", () -> "Payment back!");
        System.out.println("Recovered: " + result);
        System.out.println(manager.getStatus("payment-service"));
    }
}
