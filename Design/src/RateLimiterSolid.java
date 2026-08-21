import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RateLimiterSolid {

    interface WindowStrategy {
        void add(long now, int count);
        int size(long now);
    }

    static class SlidingStrategy implements WindowStrategy {

        private final long windowDurationMs;
        private final Queue<Long> timestamps = new LinkedList<>();

        SlidingStrategy(long windowDurationMs) {
            this.windowDurationMs = windowDurationMs;
        }

        @Override
        public void add(long now, int count) {
            evict(now);
            for (int i = 0 ; i < count ; i++) {
                timestamps.add(now);
            }
        }

        @Override
        public int size(long now) {
            evict(now);
            return timestamps.size();
        }

        private void evict(long now) {
            long cutOff = now - windowDurationMs;
            while (!timestamps.isEmpty() && timestamps.peek() < cutOff) {
                timestamps.poll();
            }
        }
    }

    interface RateLimiterStrategy {
        void allow(int count);
        int getCurrentCount();
    }

    static class RateLimiter implements RateLimiterStrategy {

        private final String serviceName;
        private final int maxRequests;
        private final WindowStrategy windowStrategy;

        RateLimiter(String serviceName, int maxRequests, WindowStrategy windowStrategy) {
            this.serviceName = serviceName;
            this.maxRequests = maxRequests;
            this.windowStrategy = windowStrategy;
        }


        @Override
        public void allow(int count) {
            if (count <= 0) throw new IllegalArgumentException(
                    "Count must be greater than zero");
            long now = System.currentTimeMillis();
            int current = windowStrategy.size(now);

            if (current + count > maxRequests) throw new IllegalStateException(
                    "[" + serviceName + "] Limit exceeded. " +
                            "Current: " + current + "/" + maxRequests +
                            " Requested: " + count);
            windowStrategy.add(now, count);
            System.out.println("[" + serviceName + "] Allowed " + count +
                    ". Total: " + (current + count) + "/" + maxRequests);
        }

        @Override
        public int getCurrentCount() {
            return windowStrategy.size(System.currentTimeMillis());
        }

        @Override
        public String toString() {
            return serviceName + " [" + getCurrentCount() + "/" + maxRequests + "]";
        }
    }

    static class RateLimiterFactory {
        public RateLimiterStrategy create(String serviceName, int maxRequests, long windowDurationMs) {
            if (serviceName == null || serviceName.isBlank())
                throw new IllegalArgumentException("Name must not be empty");
            if (maxRequests <= 0)
                throw new IllegalArgumentException("Max requests must be > 0");
            if (windowDurationMs <= 0)
                throw new IllegalArgumentException("Window must be > 0");

            return new RateLimiter(serviceName, maxRequests, new SlidingStrategy(windowDurationMs));
        }
    }

    interface ServiceRegistry {
        void register(String name, int max, long windowMs);
    }

    interface RequestGate {
        void allow(String name);
        void allowBulk(String name, int count);
        String getStatus(String serviceName);
    }

    static class RateLimiterManager implements ServiceRegistry, RequestGate {

        private final Map<String, RateLimiterStrategy> limiters = new HashMap<>();
        private final RateLimiterFactory factory;

        RateLimiterManager(RateLimiterFactory factory) {
            this.factory = factory;
        }


        @Override
        public void allow(String name) {
            getOrThrow(name).allow(1);
        }

        private RateLimiterStrategy getOrThrow(String name) {
            RateLimiterStrategy rateLimiterStrategy = limiters.get(name);
            if (rateLimiterStrategy == null) throw new IllegalArgumentException(
                    "Service not registered: " + name);
            return rateLimiterStrategy;
        }

        @Override
        public void allowBulk(String name, int count) {
            getOrThrow(name).allow(count);
        }

        @Override
        public String getStatus(String serviceName) {
            return getOrThrow(serviceName).toString();
        }

        @Override
        public void register(String name, int max, long windowMs) {
            if (limiters.containsKey(name))
                throw new IllegalArgumentException(
                    "Already registered: " + name);

            limiters.put(name, factory.create(name, max, windowMs));

            System.out.println("[Manager] Registered: " + name +
                    " limit=" + max + " window=" + windowMs + "ms");
        }
    }

    public static void main(String[] args) throws Exception {
        RateLimiterManager manager =
                new RateLimiterManager(new RateLimiterFactory());

        manager.register("payment", 5, 5000);
        manager.register("search",  3, 3000);

        // ── Scenario 1: happy path ─────────────────────────────
        System.out.println("\n── Scenario 1: single requests ──");
        manager.allow("payment");
        manager.allow("payment");
        manager.allow("payment");

        // ── Scenario 2: bulk requests ──────────────────────────
        System.out.println("\n── Scenario 2: bulk ──");
        manager.allowBulk("search", 2);
        System.out.println(manager.getStatus("search"));

        // ── Scenario 3: limit exceeded ─────────────────────────
        System.out.println("\n── Scenario 3: limit exceeded ──");
        try {
            manager.allowBulk("search", 2);
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ── Scenario 4: services independent ───────────────────
        System.out.println("\n── Scenario 4: independent ──");
        manager.allow("payment");
        System.out.println(manager.getStatus("payment"));

        // ── Scenario 5: window expires ─────────────────────────
        System.out.println("\n── Scenario 5: expiry ──");
        Thread.sleep(3100);
        manager.allowBulk("search", 3);
        System.out.println(manager.getStatus("search"));

        // ── Scenario 6: duplicate registration ─────────────────
        System.out.println("\n── Scenario 6: duplicate ──");
        try {
            manager.register("payment", 5, 5000);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ── Scenario 7: unregistered service ───────────────────
        System.out.println("\n── Scenario 7: unregistered ──");
        try {
            manager.allow("unknown");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ── Scenario 8: invalid inputs ─────────────────────────
        System.out.println("\n── Scenario 8: invalid ──");
        try { manager.register(null, 10, 1000); }
        catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }

        try { manager.allowBulk("payment", 0); }
        catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
    }
}
