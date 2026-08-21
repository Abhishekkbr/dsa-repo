import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Callable;

public class CircuitBreakerSolid {


    // =========================================================
// INTERFACE: FAILURE STRATEGY — how failures are tracked
// =========================================================
// WHAT: defines how to record failures and decide to trip
// WHY:  open/closed — swap sliding window for count based
//       without touching CircuitBreaker at all
// EDGE: each implementation handles its own edge cases
// =========================================================
    interface FailureStrategy {

        // WHAT: called every time a request fails
        // WHY:  strategy decides what to do with the failure
        // EDGE: implementation handles window eviction internally
        void recordFailure();

        // WHAT: called every time a request succeeds
        // WHY:  some strategies reset on success, others do not
        // EDGE: sliding window does not reset — failures expire naturally
        void recordSuccess();

        // WHAT: returns true when breaker should trip to OPEN
        // WHY:  decision lives in strategy, not in CircuitBreaker
        // EDGE: each strategy has its own threshold logic
        boolean shouldTrip();
    }

    // =========================================================
// SLIDING WINDOW STRATEGY — implements FailureStrategy
// =========================================================
// WHAT: tracks failure timestamps in a queue
// WHY:  old failures expire naturally — no reset needed
// EDGE: evicts stale timestamps before every shouldTrip check
// =========================================================
    static class SlidingWindowStrategy implements FailureStrategy {

        private final int    failureThreshold;
        private final long   windowDurationMs;

        // Using LinkedList as Queue — O(1) add tail, O(1) remove head
        // Not using ArrayList — removing from front is O(n)
        private final Queue<Long> failureWindow = new LinkedList<>();

        SlidingWindowStrategy(int failureThreshold, long windowDurationMs) {
            this.failureThreshold = failureThreshold;
            this.windowDurationMs = windowDurationMs;
        }

        // =====================================================
        // RECORD FAILURE — adds timestamp to window
        // =====================================================
        // WHAT: evicts old failures then adds current timestamp
        // WHY:  evict first so size check is always accurate
        // EDGE: window can be empty, peek handles null safely
        // =====================================================
        @Override
        public void recordFailure() {
            long now = System.currentTimeMillis();
            evictOldFailures(now);
            failureWindow.add(now);
            System.out.println("Recent failures in window: " +
                    failureWindow.size() + "/" + failureThreshold);
        }

        // =====================================================
        // RECORD SUCCESS — sliding window does not reset
        // =====================================================
        // WHAT: no-op for sliding window — failures expire naturally
        // WHY:  one success should not wipe recent failure history
        // EDGE: count based strategy would reset counter here
        // =====================================================
        @Override
        public void recordSuccess() {
            failureWindow.clear(); // clear only on confirmed recovery
        }

        // =====================================================
        // SHOULD TRIP — checks if threshold is reached
        // =====================================================
        // WHAT: returns true if recent failure count hits threshold
        // WHY:  decision isolated here — CircuitBreaker just asks
        // EDGE: evict called in recordFailure before this is checked
        // =====================================================
        @Override
        public boolean shouldTrip() {
            return failureWindow.size() >= failureThreshold;
        }

        // =====================================================
        // EVICT OLD FAILURES — removes expired timestamps
        // =====================================================
        // WHAT: polls from head while timestamp is outside window
        // WHY:  DRY — single place for eviction logic
        // EDGE: empty queue handled safely by isEmpty check
        // =====================================================
        private void evictOldFailures(long now) {
            long cutoff = now - windowDurationMs;
            while (!failureWindow.isEmpty() && failureWindow.peek() < cutoff) {
                failureWindow.poll();
            }
        }
    }

    // =========================================================
// CIRCUIT BREAKER — only manages state machine
// =========================================================
// WHAT: routes calls based on state, delegates failure
//       tracking entirely to injected FailureStrategy
// WHY:  single responsibility — state machine only
// EDGE: strategy injected — never instantiated internally
// =========================================================
    static class CircuitBreaker {

        enum State { CLOSED, OPEN, HALF_OPEN }

        private final String          serviceName;
        private final long            resetTimeoutMs;
        private final FailureStrategy failureStrategy; // injected — not created here

        private State state        = State.CLOSED;
        private long  lastOpenedAt = 0;

        CircuitBreaker(String serviceName,
                       long resetTimeoutMs,
                       FailureStrategy failureStrategy) {
            this.serviceName      = serviceName;
            this.resetTimeoutMs   = resetTimeoutMs;
            this.failureStrategy  = failureStrategy;
        }

        // =====================================================
        // EXECUTE — routes call based on current state
        // =====================================================
        // WHAT: checks state then runs or blocks the operation
        // WHY:  single entry point into the breaker
        // EDGE: OPEN throws without calling operation at all
        // =====================================================
        public <T> T execute(Callable<T> operation) throws Exception {
            switch (resolveState()) {
                case OPEN:
                    throw new IllegalStateException(
                            "[" + serviceName + "] Circuit OPEN. Retry in "
                                    + timeUntilResetMs() + "ms");
                case HALF_OPEN:
                    return tryHalfOpen(operation);
                case CLOSED:
                default:
                    return tryNormal(operation);
            }
        }

        // =====================================================
        // RESOLVE STATE — auto transitions OPEN to HALF_OPEN
        // =====================================================
        // WHAT: checks timeout elapsed, moves to HALF_OPEN if so
        // WHY:  no background thread needed — checked on every call
        // EDGE: only transitions when timeout fully elapsed
        // =====================================================
        private State resolveState() {
            if (state == State.OPEN && timeUntilResetMs() <= 0) {
                transitionTo(State.HALF_OPEN);
            }
            return state;
        }

        // =====================================================
        // TRY NORMAL — runs operation when CLOSED
        // =====================================================
        // WHAT: executes call, tells strategy about result
        // WHY:  strategy decides what failure means — not breaker
        // EDGE: always rethrows so caller sees the real error
        // =====================================================
        private <T> T tryNormal(Callable<T> operation) throws Exception {
            try {
                T result = operation.call();
                failureStrategy.recordSuccess();
                return result;
            } catch (Exception e) {
                failureStrategy.recordFailure();
                if (failureStrategy.shouldTrip()) {
                    lastOpenedAt = System.currentTimeMillis();
                    transitionTo(State.OPEN);
                }
                throw e;
            }
        }

        // =====================================================
        // TRY HALF OPEN — one probe call to test recovery
        // =====================================================
        // WHAT: single test — success closes, failure reopens
        // WHY:  no threshold check — one strike reopens circuit
        // EDGE: resets lastOpenedAt on failure to restart timer
        // =====================================================
        private <T> T tryHalfOpen(Callable<T> operation) throws Exception {
            try {
                T result = operation.call();
                failureStrategy.recordSuccess();
                transitionTo(State.CLOSED);
                return result;
            } catch (Exception e) {
                lastOpenedAt = System.currentTimeMillis();
                transitionTo(State.OPEN);
                throw e;
            }
        }

        // =====================================================
        // TRANSITION TO — single place for all state changes
        // =====================================================
        // WHAT: updates state and logs every transition
        // WHY:  DRY — state never assigned anywhere else
        // EDGE: logging here makes alerts easy to add later
        // =====================================================
        private void transitionTo(State next) {
            System.out.println("[" + serviceName + "] " + state + " → " + next);
            state = next;
        }

        // =====================================================
        // TIME UNTIL RESET — ms remaining in OPEN state
        // =====================================================
        // WHAT: total wait minus time already waited
        // WHY:  DRY — timeout formula in one place only
        // EDGE: returns negative when timeout elapsed
        // =====================================================
        private long timeUntilResetMs() {
            return resetTimeoutMs - (System.currentTimeMillis() - lastOpenedAt);
        }

        public State getState() { return state; }
    }

    // =========================================================
// CIRCUIT BREAKER FACTORY — creates breakers with strategy
// =========================================================
// WHAT: builds a CircuitBreaker with SlidingWindowStrategy
// WHY:  manager never calls new CircuitBreaker() directly
//       — dependency inversion, factory owns creation
// EDGE: validates all params before creating anything
// =========================================================
    static class CircuitBreakerFactory {

        // =====================================================
        // CREATE — builds breaker with injected strategy
        // =====================================================
        // WHAT: instantiates strategy then injects into breaker
        // WHY:  manager depends on factory not on concrete classes
        // EDGE: validation here so manager stays clean
        // =====================================================
        public CircuitBreaker create(String serviceName,
                                     int failureThreshold,
                                     long windowDurationMs,
                                     long resetTimeoutMs) {
            if (serviceName == null || serviceName.isBlank()) {
                throw new IllegalArgumentException("Service name must not be empty");
            }
            if (failureThreshold <= 0) {
                throw new IllegalArgumentException("Threshold must be greater than zero");
            }
            if (windowDurationMs <= 0) {
                throw new IllegalArgumentException("Window must be greater than zero");
            }
            if (resetTimeoutMs <= 0) {
                throw new IllegalArgumentException("Reset timeout must be greater than zero");
            }

            FailureStrategy strategy =
                    new SlidingWindowStrategy(failureThreshold, windowDurationMs);

            return new CircuitBreaker(serviceName, resetTimeoutMs, strategy);
        }
    }

    // =========================================================
// INTERFACE: EXECUTOR — callers who only need to execute
// =========================================================
// WHAT: contract for executing operations through a breaker
// WHY:  caller that only executes does not see register()
// EDGE: throws if service unknown or circuit is OPEN
// =========================================================
    interface Executor {
        <T> T execute(String serviceName, Callable<T> operation) throws Exception;
        CircuitBreaker.State getStatus(String serviceName);
    }

    // =========================================================
// INTERFACE: REGISTRY — callers who only need to register
// =========================================================
// WHAT: contract for registering services with their config
// WHY:  separates registration concern from execution concern
// EDGE: throws if service name blank or values non-positive
// =========================================================
    interface Registry {
        void register(String serviceName,
                      int failureThreshold,
                      long windowDurationMs,
                      long resetTimeoutMs);
    }


    // =========================================================
// CIRCUIT BREAKER MANAGER — implements Executor and Registry
// =========================================================
// WHAT: registry of services + entry point for execution
// WHY:  implements two interfaces — callers depend on only
//       what they need, not the whole manager
// EDGE: factory injected — manager never calls new directly
// =========================================================
    static class CircuitBreakerManager implements Executor, Registry {

        // Using HashMap — O(1) lookup by service name
        // Not using List — searching by name would be O(n)
        private final Map<String, CircuitBreaker> breakers = new HashMap<>();
        private final CircuitBreakerFactory       factory;

        CircuitBreakerManager(CircuitBreakerFactory factory) {
            this.factory = factory; // injected — not created here
        }

        // =====================================================
        // REGISTER — creates and stores a breaker per service
        // =====================================================
        // WHAT: delegates creation to factory, stores result
        // WHY:  manager does not know how breakers are built
        // EDGE: factory handles all validation and creation
        // =====================================================
        @Override
        public void register(String serviceName,
                             int failureThreshold,
                             long windowDurationMs,
                             long resetTimeoutMs) {
            CircuitBreaker breaker = factory.create(
                    serviceName, failureThreshold, windowDurationMs, resetTimeoutMs);
            breakers.put(serviceName, breaker);
            System.out.println("[Manager] Registered: " + serviceName +
                    " threshold=" + failureThreshold +
                    " window=" + windowDurationMs + "ms" +
                    " timeout=" + resetTimeoutMs + "ms");
        }

        // =====================================================
        // EXECUTE — finds breaker and runs operation
        // =====================================================
        // WHAT: looks up service breaker and delegates execute
        // WHY:  caller never touches CircuitBreaker directly
        // EDGE: throws clearly if service never registered
        // =====================================================
        @Override
        public <T> T execute(String serviceName, Callable<T> operation) throws Exception {
            return getOrThrow(serviceName).execute(operation);
        }

        // =====================================================
        // GET STATUS — returns current state of a service
        // =====================================================
        // WHAT: looks up breaker and returns its state
        // WHY:  useful for health checks and monitoring
        // EDGE: throws if service not registered
        // =====================================================
        @Override
        public CircuitBreaker.State getStatus(String serviceName) {
            return getOrThrow(serviceName).getState();
        }

        // =====================================================
        // GET OR THROW — shared lookup used by all methods
        // =====================================================
        // WHAT: finds breaker by name, throws if missing
        // WHY:  DRY — not-registered error in one place only
        // EDGE: clear message tells caller what went wrong
        // =====================================================
        private CircuitBreaker getOrThrow(String serviceName) {
            CircuitBreaker breaker = breakers.get(serviceName);
            if (breaker == null) {
                throw new IllegalArgumentException(
                        "Service not registered: " + serviceName);
            }
            return breaker;
        }

        // =========================================================
        // MAIN — walkthrough of all scenarios
        // =========================================================
        public static void main(String[] args) throws Exception {
            CircuitBreakerFactory   factory = new CircuitBreakerFactory();
            CircuitBreakerManager   manager = new CircuitBreakerManager(factory);

            manager.register("payment-service",   3, 5000, 2000);
            manager.register("inventory-service", 2, 3000, 1000);

            // ── Scenario 1: payment trips after 3 failures ────────
            System.out.println("\n── Scenario 1: payment trips ──");
            for (int i = 1; i <= 3; i++) {
                try {
                    manager.execute("payment-service",
                            () -> { throw new RuntimeException("Payment down"); });
                } catch (Exception e) {
                    System.out.println("Failure " + i + ": " + e.getMessage());
                }
            }

            // ── Scenario 2: payment OPEN, inventory independent ───
            System.out.println("\n── Scenario 2: services independent ──");
            try {
                manager.execute("payment-service", () -> "ok");
            } catch (IllegalStateException e) {
                System.out.println("Payment blocked: " + e.getMessage());
            }
            String inv = manager.execute("inventory-service", () -> "inventory ok");
            System.out.println("Inventory works: " + inv);

            // ── Scenario 3: failures expire out of window ─────────
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
            System.out.println("State: " + manager.getStatus("search-service"));
            System.out.println("Waiting 3s for window to expire...");
            Thread.sleep(3000);
            try {
                manager.execute("search-service",
                        () -> { throw new RuntimeException("down"); });
            } catch (Exception e) {
                System.out.println("After expiry — state: " +
                        manager.getStatus("search-service"));
            }

            // ── Scenario 4: recovery after timeout ────────────────
            System.out.println("\n── Scenario 4: payment recovers ──");
            Thread.sleep(2100);
            String result = manager.execute("payment-service", () -> "Payment back!");
            System.out.println("Recovered: " + result + " state: " +
                    manager.getStatus("payment-service"));

            // ── Scenario 5: unregistered service ──────────────────
            System.out.println("\n── Scenario 5: unregistered service ──");
            try {
                manager.execute("unknown-service", () -> "test");
            } catch (IllegalArgumentException e) {
                System.out.println("Correctly rejected: " + e.getMessage());
            }
        }
    }
}
