import java.util.HashMap;
import java.util.Map;

public class TokenManagerSolid {
    static class TokenRecord {

        final String userId;
        final String token;
        final long   ttlMs;
        long         expiresAt;

        TokenRecord(String userId, String token, long ttlMs) {
            this.userId    = userId;
            this.token     = token;
            this.ttlMs     = ttlMs;
            this.expiresAt = System.currentTimeMillis() + ttlMs;
        }

        @Override
        public String toString() {
            return "TokenRecord[user=" + userId + " token=" + token + "]";
        }
    }

    // =========================================================
// EXPIRY CHECKER — contract for expiry logic
// =========================================================
// WHAT: defines how expiry is checked for any token
// WHY:  open/closed — swap expiry logic without touching service
// EDGE: implementations decide what expired means
// =========================================================
    interface ExpiryChecker {

        // WHAT: returns true if token has passed its expiry time
        // WHY:  expiry logic separated from service and storage
        // EDGE: called before every validate and refresh operation
        boolean isExpired(TokenRecord record);
    }

    // =========================================================
// DEFAULT EXPIRY CHECKER — implements ExpiryChecker
// =========================================================
// WHAT: checks current time against expiresAt timestamp
// WHY:  standard time based expiry — most common use case
// EDGE: uses currentTimeMillis — always accurate to ms
// =========================================================
    static class DefaultExpiryChecker implements ExpiryChecker {

        // WHAT: returns true if now is past expiresAt
        // WHY:  single place for expiry calculation — DRY
        // EDGE: never throws — pure boolean check only
        @Override
        public boolean isExpired(TokenRecord record) {
            return System.currentTimeMillis() > record.expiresAt;
        }
    }

    // =========================================================
// TOKEN STORE — contract for any token storage
// =========================================================
// WHAT: defines save, find, remove for any storage impl
// WHY:  open/closed — swap storage without touching service
// EDGE: implementations handle their own null checks
// =========================================================
    interface TokenStore {

        // WHAT: persists a token record by token string key
        // WHY:  service never touches storage directly
        // EDGE: overwrites if token key already exists
        void save(String token, TokenRecord record);

        // WHAT: finds token record, returns null if not found
        // WHY:  service decides what to do when null returned
        // EDGE: returns null — never throws here
        TokenRecord find(String token);

        // WHAT: removes token from storage permanently
        // WHY:  revoke and expired cleanup both need this
        // EDGE: no-op if token does not exist
        void remove(String token);
    }

    // =========================================================
// IN MEMORY TOKEN STORE — implements TokenStore
// =========================================================
// WHAT: stores token records in a HashMap
// WHY:  simple in-memory store — swap for Redis in production
// EDGE: not thread safe — see thread safety note below
// =========================================================
    static class InMemoryTokenStore implements TokenStore {

        // Using HashMap — O(1) lookup by token string
        // Not using List — searching by token is O(n)
        private final Map<String, TokenRecord> store = new HashMap<>();

        // WHAT: puts record into map by token key
        // WHY:  single place for storage write logic
        // EDGE: silently overwrites existing key
        @Override
        public void save(String token, TokenRecord record) {
            store.put(token, record);
        }

        // WHAT: gets record from map, returns null if missing
        // WHY:  caller handles null — store does not decide
        // EDGE: returns null for unknown or removed tokens
        @Override
        public TokenRecord find(String token) {
            return store.get(token);
        }

        // WHAT: removes record from map by token key
        // WHY:  single place for storage delete logic
        // EDGE: no-op if key does not exist in map
        @Override
        public void remove(String token) {
            store.remove(token);
        }
    }

    // =========================================================
// TOKEN SERVICE — contract for any token manager
// =========================================================
// WHAT: defines generate, validate, refresh, revoke
// WHY:  liskov — any implementation fully substitutable
// EDGE: implementations validate all inputs before operation
// =========================================================
    interface TokenService {

        // WHAT: creates a new token for userId with given TTL
        // WHY:  entry point for token lifecycle
        // EDGE: throws if userId blank or ttlMs non-positive
        String generate(String userId, long ttlMs);

        // WHAT: checks token exists and has not expired
        // WHY:  every protected operation calls this first
        // EDGE: throws if token null, not found, or expired
        TokenRecord validate(String token);

        // WHAT: extends token TTL without changing the token
        // WHY:  keeps active sessions alive without re-login
        // EDGE: throws if token not found or already expired
        String refresh(String token);

        // WHAT: removes token immediately from storage
        // WHY:  logout or security breach invalidation
        // EDGE: throws if token not found
        void revoke(String token);
    }

    // =========================================================
// TOKEN MANAGER — implements TokenService
// =========================================================
// WHAT: coordinates token lifecycle using injected store
// WHY:  single responsibility — token operations only
// EDGE: store and checker injected — never created here
// =========================================================
    static class TokenManager implements TokenService {

        private final TokenStore    store;   // injected — not created here
        private final ExpiryChecker checker; // injected — not created here
        private       int           counter = 0;

        TokenManager(TokenStore store, ExpiryChecker checker) {
            this.store   = store;
            this.checker = checker;
        }

        // WHAT: validates inputs, builds token, saves to store
        // WHY:  entry point — counter ensures unique token ids
        // EDGE: throws IllegalArgumentException for bad inputs
        @Override
        public String generate(String userId, long ttlMs) {
            if (userId == null || userId.isBlank())
                throw new IllegalArgumentException("UserId must not be null or blank");
            if (ttlMs <= 0)
                throw new IllegalArgumentException("ttlMs must be greater than zero");

            String      token  = "tok-" + userId + "-" + (counter++);
            TokenRecord record = new TokenRecord(userId, token, ttlMs);
            store.save(token, record);

            System.out.println("[TokenManager] Generated: " + token +
                    " TTL: " + ttlMs + "ms");
            return token;
        }

        // WHAT: finds token, checks expiry, removes if expired
        // WHY:  lazy cleanup — expired tokens removed on access
        // EDGE: throws if null, not found, or expired
        @Override
        public TokenRecord validate(String token) {
            TokenRecord record = findOrThrow(token);

            if (checker.isExpired(record)) {
                store.remove(token); // lazy cleanup on access
                throw new IllegalStateException("Token expired: " + token);
            }

            System.out.println("[TokenManager] Valid: " + token);
            return record;
        }

        // WHAT: checks expiry then extends expiresAt by ttlMs
        // WHY:  refresh must fail on expired — not silently extend
        // EDGE: throws if expired but does NOT remove — checkExpired
        @Override
        public String refresh(String token) {
            TokenRecord record = findOrThrow(token);
            checkExpired(token, record); // throws but does NOT remove

            record.expiresAt = System.currentTimeMillis() + record.ttlMs;
            System.out.println("[TokenManager] Refreshed: " + token +
                    " new expiry in " + record.ttlMs + "ms");
            return token;
        }

        // WHAT: finds token then removes from store permanently
        // WHY:  no further validation possible after revoke
        // EDGE: throws if token not found before removing
        @Override
        public void revoke(String token) {
            findOrThrow(token);
            store.remove(token);
            System.out.println("[TokenManager] Revoked: " + token);
        }

        // WHAT: finds token in store, throws if null or missing
        // WHY:  DRY — not-found error in one place only
        // EDGE: throws IllegalArgumentException for null token
        private TokenRecord findOrThrow(String token) {
            if (token == null)
                throw new IllegalArgumentException("Token must not be null");
            TokenRecord record = store.find(token);
            if (record == null)
                throw new IllegalArgumentException("Token not found: " + token);
            return record;
        }

        // WHAT: throws if expired but does NOT remove from store
        // WHY:  refresh needs to throw without deleting the token
        // EDGE: always called before any mutation in refresh
        private void checkExpired(String token, TokenRecord record) {
            if (checker.isExpired(record))
                throw new IllegalStateException("Token expired: " + token);
        }
    }

    // =========================================================
// TOKEN MANAGER FACTORY — creates manager with deps wired
// =========================================================
// WHAT: builds TokenManager with store and checker injected
// WHY:  dependency inversion — main never calls new directly
// EDGE: swap store or checker here without touching manager
// =========================================================
    static class TokenManagerFactory {

        // WHAT: creates store and checker then injects into manager
        // WHY:  factory owns all creation — main stays clean
        // EDGE: change storage or expiry impl here only
        public TokenService create() {
            TokenStore    store   = new InMemoryTokenStore();
            ExpiryChecker checker = new DefaultExpiryChecker();
            return new TokenManager(store, checker);
        }
    }


    public static void main(String[] args) throws Exception {
        TokenService tm = new TokenManagerFactory().create();

        // ── Scenario 1: generate and validate ─────────────────
        System.out.println("── Scenario 1: generate and validate ──");
        String t1 = tm.generate("alice", 5000);
        tm.validate(t1);

        // ── Scenario 2: refresh ────────────────────────────────
        System.out.println("\n── Scenario 2: refresh ──");
        tm.refresh(t1);

        // ── Scenario 3: revoke then validate ──────────────────
        System.out.println("\n── Scenario 3: revoke ──");
        tm.revoke(t1);
        try {
            tm.validate(t1);
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly rejected: " + e.getMessage());
        }

        // ── Scenario 4: expired token ──────────────────────────
        System.out.println("\n── Scenario 4: expiry ──");
        String t2 = tm.generate("bob", 500);
        Thread.sleep(600);
        try {
            tm.validate(t2);
        } catch (IllegalStateException e) {
            System.out.println("Correctly rejected: " + e.getMessage());
        }

        // ── Scenario 5: refresh expired token ─────────────────
        System.out.println("\n── Scenario 5: refresh expired ──");
        String t3 = tm.generate("carol", 300);
        Thread.sleep(400);
        try {
            tm.refresh(t3);
        } catch (IllegalStateException e) {
            System.out.println("Correctly rejected: " + e.getMessage());
        }

        // ── Scenario 6: invalid inputs ─────────────────────────
        System.out.println("\n── Scenario 6: invalid inputs ──");
        try { tm.generate(null, 1000); }
        catch (IllegalArgumentException e) {
            System.out.println("Null userId: " + e.getMessage());
        }
        try { tm.generate("dave", -1); }
        catch (IllegalArgumentException e) {
            System.out.println("Negative TTL: " + e.getMessage());
        }
        try { tm.validate(null); }
        catch (IllegalArgumentException e) {
            System.out.println("Null token: " + e.getMessage());
        }
    }

}
