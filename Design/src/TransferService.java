import java.util.HashMap;
import java.util.Map;

public class TransferService {
    static class Account {

        private final String accountId;
        private final String currency;
        private       double balance;

        Account(String accountId, String currency, double initialBalance) {
            if (initialBalance < 0) throw new IllegalArgumentException(
                    "Initial balance must not be negative");
            this.accountId = accountId;
            this.currency  = currency.toUpperCase();
            this.balance   = initialBalance;
        }

        // WHAT: adds amount to balance on deposit
        // WHY:  balance mutation in one place — never set directly
        // EDGE: throws if amount is zero or negative
        void deposit(double amount) {
            if (amount <= 0) throw new IllegalArgumentException(
                    "Deposit amount must be greater than zero");
            balance += amount;
        }

        // WHAT: reduces balance on withdrawal if sufficient funds
        // WHY:  balance mutation in one place — never set directly
        // EDGE: throws if amount invalid or insufficient balance
        void withdraw(double amount) {
            if (amount <= 0) throw new IllegalArgumentException(
                    "Withdrawal amount must be greater than zero");
            if (balance < amount) throw new IllegalStateException(
                    "Insufficient balance. Available: " + balance +
                            " Requested: " + amount);
            balance -= amount;
        }

        public String getAccountId() { return accountId; }
        public String getCurrency()  { return currency; }
        public double getBalance()   { return balance; }

        @Override
        public String toString() {
            return String.format("Account[id=%s currency=%s balance=%.2f]",
                    accountId, currency, balance);
        }
    }

    // =========================================================
// ACCOUNT REPOSITORY — contract for account storage
// =========================================================
// WHAT: defines save, find and exists for any storage impl
// WHY:  open/closed — swap storage without touching service
// EDGE: implementations handle their own null checks
// =========================================================
    interface AccountRepository {

        // WHAT: persists a new account to storage
        // WHY:  service never touches storage directly
        // EDGE: throws if account with same id already exists
        void save(Account account);

        // WHAT: finds account by id, throws if not found
        // WHY:  service needs account or clear error — never null
        // EDGE: throws with clear message naming missing account
        Account findById(String accountId);

        // WHAT: returns true if account exists in storage
        // WHY:  save uses this to prevent duplicate accounts
        // EDGE: returns false for null or blank accountId
        boolean exists(String accountId);
    }

    // =========================================================
// IN MEMORY ACCOUNT REPOSITORY — implements AccountRepository
// =========================================================
// WHAT: stores accounts in a HashMap — no external dependency
// WHY:  simple in-memory impl — swap for DB impl in production
// EDGE: case insensitive account ids via lowercase normalization
// =========================================================
    static class InMemoryAccountRepository implements AccountRepository {

        // Using HashMap — O(1) lookup by accountId
        // Not using List — searching by accountId is O(n)
        private final Map<String, Account> store = new HashMap<>();

        // WHAT: puts account into map by accountId
        // WHY:  single place for account persistence logic
        // EDGE: throws if accountId already exists in store
        @Override
        public void save(Account account) {
            if (exists(account.getAccountId())) throw new IllegalArgumentException(
                    "Account already exists: " + account.getAccountId());
            store.put(account.getAccountId().toLowerCase(), account);
        }

        // WHAT: looks up account by id, throws if missing
        // WHY:  service always gets valid account or clear error
        // EDGE: case insensitive lookup via lowercase normalization
        @Override
        public Account findById(String accountId) {
            Account account = store.get(accountId.toLowerCase());
            if (account == null) throw new IllegalArgumentException(
                    "Account not found: " + accountId);
            return account;
        }

        // WHAT: checks if accountId exists in store
        // WHY:  prevents duplicate account creation
        // EDGE: returns false for null accountId safely
        @Override
        public boolean exists(String accountId) {
            if (accountId == null) return false;
            return store.containsKey(accountId.toLowerCase());
        }
    }

    // =========================================================
// TRANSACTION SERVICE — contract for money operations
// =========================================================
// WHAT: defines deposit, withdraw, transfer for any impl
// WHY:  liskov — any implementation fully substitutable
// EDGE: implementations validate all inputs before mutation
// =========================================================
    interface TransactionService {

        // WHAT: creates a new account with currency and balance
        // WHY:  accounts must exist before any operation
        // EDGE: throws if accountId exists or balance negative
        void createAccount(String accountId, String currency, double initialBalance);

        // WHAT: adds amount to account balance
        // WHY:  increases available funds for an account
        // EDGE: throws if account not found or amount invalid
        void deposit(String accountId, double amount);

        // WHAT: removes amount from account balance
        // WHY:  decreases available funds for an account
        // EDGE: throws if insufficient balance or amount invalid
        void withdraw(String accountId, double amount);

        // WHAT: moves amount from one account to another
        // WHY:  core transfer operation between two accounts
        // EDGE: throws if currencies differ or funds insufficient
        void transfer(String fromAccountId, String toAccountId, double amount);

        // WHAT: prints current balance for an account
        // WHY:  callers need to verify account state
        // EDGE: throws if account not found
        void getBalance(String accountId);
    }

    // =========================================================
// MONEY TRANSFER SERVICE — implements TransactionService
// =========================================================
// WHAT: executes all money operations using injected repository
// WHY:  single responsibility — transaction logic only
// EDGE: repository injected — never created here
// =========================================================
    static class MoneyTransferService implements TransactionService {

        private final AccountRepository repository; // injected — not created here

        MoneyTransferService(AccountRepository repository) {
            this.repository = repository;
        }

        // WHAT: validates inputs then saves new account to repository
        // WHY:  accounts must be registered before any operation
        // EDGE: throws if id blank, currency blank, balance negative
        @Override
        public void createAccount(String accountId, String currency, double initialBalance) {
            validateAccountId(accountId);
            validateCurrency(currency);
            repository.save(new Account(accountId, currency, initialBalance));
            System.out.println("[Service] Account created: " + accountId +
                    " currency=" + currency.toUpperCase() +
                    " balance=" + initialBalance);
        }

        // WHAT: finds account then delegates deposit to account
        // WHY:  service coordinates — account handles mutation
        // EDGE: throws if account not found or amount invalid
        @Override
        public void deposit(String accountId, double amount) {
            validateAccountId(accountId);
            Account account = repository.findById(accountId);
            account.deposit(amount);
            System.out.printf("[Service] Deposited %.2f to %s | New balance: %.2f%n",
                    amount, accountId, account.getBalance());
        }

        // WHAT: finds account then delegates withdrawal to account
        // WHY:  service coordinates — account handles mutation
        // EDGE: throws if account not found or insufficient funds
        @Override
        public void withdraw(String accountId, double amount) {
            validateAccountId(accountId);
            Account account = repository.findById(accountId);
            account.withdraw(amount);
            System.out.printf("[Service] Withdrew %.2f from %s | New balance: %.2f%n",
                    amount, accountId, account.getBalance());
        }

        // WHAT: validates currencies match then moves funds atomically
        // WHY:  transfer must be all-or-nothing — no partial transfer
        // EDGE: throws if same account, currencies differ, funds insufficient
        @Override
        public void transfer(String fromId, String toId, double amount) {
            validateAccountId(fromId);
            validateAccountId(toId);

            if (fromId.equalsIgnoreCase(toId)) throw new IllegalArgumentException(
                    "Cannot transfer to same account: " + fromId);

            Account from = repository.findById(fromId);
            Account to   = repository.findById(toId);

            if (!from.getCurrency().equals(to.getCurrency()))
                throw new IllegalStateException(
                        "Currency mismatch. From: " + from.getCurrency() +
                                " To: " + to.getCurrency() +
                                ". Cross currency transfer not supported yet.");

            // withdraw first — throws if insufficient, so deposit never runs
            from.withdraw(amount);
            to.deposit(amount);

            System.out.printf("[Service] Transferred %.2f %s from %s to %s%n",
                    amount, from.getCurrency(), fromId, toId);
            System.out.printf("  %s new balance: %.2f%n", fromId, from.getBalance());
            System.out.printf("  %s new balance: %.2f%n", toId,   to.getBalance());
        }

        // WHAT: finds account and prints current balance
        // WHY:  callers need to verify state without mutating
        // EDGE: throws if account not found
        @Override
        public void getBalance(String accountId) {
            validateAccountId(accountId);
            Account account = repository.findById(accountId);
            System.out.printf("[Service] %s balance: %.2f %s%n",
                    accountId, account.getBalance(), account.getCurrency());
        }

        // ── Validation helpers — DRY, one place per check ──────────

        // WHAT: throws if accountId is null or blank
        // WHY:  DRY — accountId check used by all operations
        // EDGE: covers null and whitespace only strings
        private void validateAccountId(String accountId) {
            if (accountId == null || accountId.isBlank())
                throw new IllegalArgumentException("AccountId must not be blank");
        }

        // WHAT: throws if currency is null or blank
        // WHY:  DRY — currency check used by createAccount
        // EDGE: covers null and whitespace only strings
        private void validateCurrency(String currency) {
            if (currency == null || currency.isBlank())
                throw new IllegalArgumentException("Currency must not be blank");
        }
    }

    // =========================================================
// TRANSACTION SERVICE FACTORY — wires dependencies
// =========================================================
// WHAT: creates MoneyTransferService with repository injected
// WHY:  dependency inversion — main never calls new directly
// EDGE: swap repository here without touching service
// =========================================================
    static class TransactionServiceFactory {

        // WHAT: creates repository then injects into service
        // WHY:  factory owns all creation — main stays clean
        // EDGE: change storage impl here only — one place
        public TransactionService create() {
            AccountRepository repository = new InMemoryAccountRepository();
            return new MoneyTransferService(repository);
        }
    }

    public static void main(String[] args) {
        TransactionService service = new TransactionServiceFactory().create();

        // ── Setup: create accounts ─────────────────────────────
        System.out.println("── Setup ──");
        service.createAccount("alice", "USD", 1000.00);
        service.createAccount("bob",   "USD", 500.00);
        service.createAccount("carol", "EUR", 800.00);

        // ── Scenario 1: deposit ────────────────────────────────
        System.out.println("\n── Scenario 1: deposit ──");
        service.deposit("alice", 500.00);
        service.getBalance("alice");

        // ── Scenario 2: withdraw ───────────────────────────────
        System.out.println("\n── Scenario 2: withdraw ──");
        service.withdraw("bob", 200.00);
        service.getBalance("bob");

        // ── Scenario 3: transfer same currency ─────────────────
        System.out.println("\n── Scenario 3: transfer ──");
        service.transfer("alice", "bob", 300.00);
        service.getBalance("alice");
        service.getBalance("bob");

        // ── Scenario 4: insufficient balance ───────────────────
        System.out.println("\n── Scenario 4: insufficient balance ──");
        try {
            service.withdraw("bob", 10000.00);
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ── Scenario 5: transfer insufficient funds ────────────
        System.out.println("\n── Scenario 5: transfer insufficient ──");
        try {
            service.transfer("bob", "alice", 10000.00);
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ── Scenario 6: currency mismatch ──────────────────────
        System.out.println("\n── Scenario 6: currency mismatch ──");
        try {
            service.transfer("alice", "carol", 100.00);
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ── Scenario 7: same account transfer ──────────────────
        System.out.println("\n── Scenario 7: same account ──");
        try {
            service.transfer("alice", "alice", 100.00);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ── Scenario 8: account not found ──────────────────────
        System.out.println("\n── Scenario 8: account not found ──");
        try {
            service.deposit("unknown", 100.00);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ── Scenario 9: duplicate account ──────────────────────
        System.out.println("\n── Scenario 9: duplicate account ──");
        try {
            service.createAccount("alice", "USD", 100.00);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ── Scenario 10: null and invalid inputs ───────────────
        System.out.println("\n── Scenario 10: invalid inputs ──");
        try {
            service.deposit(null, 100.00);
        } catch (IllegalArgumentException e) {
            System.out.println("Null accountId: " + e.getMessage());
        }
        try {
            service.deposit("alice", -50.00);
        } catch (IllegalArgumentException e) {
            System.out.println("Negative amount: " + e.getMessage());
        }
        try {
            service.createAccount("dave", "", 100.00);
        } catch (IllegalArgumentException e) {
            System.out.println("Blank currency: " + e.getMessage());
        }
    }
}
