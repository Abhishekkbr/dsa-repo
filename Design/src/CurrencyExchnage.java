import java.util.HashMap;
import java.util.Map;

public class CurrencyExchnage {
    interface RateProvider {
        double getRate(String key);
        void putRate(String key, double amount);
        boolean hasRate(String key);
    }

    static class HardCodeRateProvider implements RateProvider {
        Map<String, Double> hardCodeRateStore = new HashMap<>();

        HardCodeRateProvider() {
            // seed with common hardcoded pairs
            hardCodeRateStore.put("USD_EUR", 0.92);
            hardCodeRateStore.put("EUR_USD", 1.0 / 0.92);
            hardCodeRateStore.put("USD_GBP", 0.79);
            hardCodeRateStore.put("GBP_USD", 1.0 / 0.79);
            hardCodeRateStore.put("USD_INR", 83.50);
            hardCodeRateStore.put("INR_USD", 1.0 / 83.50);
            hardCodeRateStore.put("USD_JPY", 149.50);
            hardCodeRateStore.put("JPY_USD", 1.0 / 149.50);
            hardCodeRateStore.put("EUR_GBP", 0.86);
            hardCodeRateStore.put("GBP_EUR", 1.0 / 0.86);
        }

        @Override
        public double getRate(String key) {
            return hardCodeRateStore.get(key);
        }

        @Override
        public void putRate(String key, double amount) {
            hardCodeRateStore.put(key, amount);
        }

        @Override
        public boolean hasRate(String key) {
            return hardCodeRateStore.containsKey(key);
        }
    }

    interface CurrencyExchange {
        double convert(String from, String to, double amount);

        void addRate(String from, String to, double rate);

        double getRate(String from, String to);
    }

    static class CurrencyExchangeImpl implements CurrencyExchange {

        private final RateProvider rateProvider;

        CurrencyExchangeImpl(RateProvider rateProvider) {
            this.rateProvider = rateProvider;
        }

        @Override
        public double convert(String from, String to, double amount) {
            validateCurrency(from, "from");
            validateCurrency(to, "to");

            if (amount < 0) throw new IllegalArgumentException(
                    "Amount must not be negative: " + amount);

            if (from.equalsIgnoreCase(to)) return amount;

            double rate = getRate(from, to);
            double convertedAmount = rate * amount;
            System.out.printf("[Exchange] %.2f %s → %.2f %s (rate: %.4f)%n",
                    amount, from.toUpperCase(), convertedAmount, to.toUpperCase(), rate);
            return convertedAmount;
        }

        @Override
        public void addRate(String from, String to, double rate) {
            validateCurrency(from, "from");
            validateCurrency(to, "to");

            if (from.equalsIgnoreCase(to)) throw new IllegalArgumentException(
                    "Cannot add rate for same currency: " + from);

            if (rate <= 0) throw new IllegalArgumentException(
                    "Rate must not be negative: " + rate);

            if (rateProvider.hasRate(buildKey(from, to)))
                throw new IllegalArgumentException(
                        "Rate already exists for: " + from + "→" + to +
                                ". Use updateRate() to change it.");
            rateProvider.putRate(buildKey(from, to), rate);
            rateProvider.putRate(buildKey(to, from), 1.0 / rate);

            System.out.println("[Exchange] Added: " +
                    from.toUpperCase() + "→" + to.toUpperCase() + "=" + rate +
                    " | " + to.toUpperCase() + "→" + from.toUpperCase() +
                    "=" + String.format("%.4f", 1.0 / rate));
        }

        @Override
        public double getRate(String from, String to) {
            validateCurrency(from, "from");
            validateCurrency(to, "to");
            String key = buildKey(from, to);
            if (!rateProvider.hasRate(key)) throw new IllegalArgumentException(
                    "No rate found for: " + from.toUpperCase() +
                            "→" + to.toUpperCase() + ". Add it first.");
            return rateProvider.getRate(key);
        }

        private String buildKey(String from, String to) {
            return from.toUpperCase() + "_" + to.toUpperCase();
        }

        private void validateCurrency(String currency, String fieldName) {
            if (currency == null || currency.isBlank())
                throw new IllegalArgumentException(
                        fieldName + " currency must not be null or blank");
        }
    }

    static class CurrencyExchangeFactory {
        public CurrencyExchange create() {
            return new CurrencyExchangeImpl(new HardCodeRateProvider());
        }
    }

    public static void main(String[] args) {
        CurrencyExchange exchange = new CurrencyExchangeFactory().create();

        // ── Scenario 1: basic conversions ──────────────────────
        System.out.println("\n── Scenario 1: basic conversions ──");
        exchange.convert("USD", "EUR", 100);
        exchange.convert("USD", "INR", 100);
        exchange.convert("EUR", "GBP", 50);

        // ── Scenario 2: auto inverse works ─────────────────────
        System.out.println("\n── Scenario 2: auto inverse ──");
        exchange.convert("EUR", "USD", 100);
        exchange.convert("INR", "USD", 1000);

        // ── Scenario 3: same currency returns same amount ──────
        System.out.println("\n── Scenario 3: same currency ──");
        double result = exchange.convert("USD", "USD", 100);
        System.out.println("USD→USD: " + result);

        // ── Scenario 4: add new rate at runtime ────────────────
        System.out.println("\n── Scenario 4: add new rate ──");
        exchange.addRate("USD", "AED", 3.67);
        exchange.convert("USD", "AED", 100);
        exchange.convert("AED", "USD", 367);

        // ── Scenario 5: get raw rate ───────────────────────────
        System.out.println("\n── Scenario 5: get rate ──");
        System.out.println("USD→EUR rate: " + exchange.getRate("USD", "EUR"));

        // ── Scenario 6: missing pair throws ────────────────────
        System.out.println("\n── Scenario 6: missing pair ──");
        try {
            exchange.convert("GBP", "JPY", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ── Scenario 7: negative amount throws ─────────────────
        System.out.println("\n── Scenario 7: negative amount ──");
        try {
            exchange.convert("USD", "EUR", -50);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ── Scenario 8: same currency rate throws ──────────────
        System.out.println("\n── Scenario 8: same currency rate ──");
        try {
            exchange.addRate("USD", "USD", 1.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ── Scenario 9: null currency throws ───────────────────
        System.out.println("\n── Scenario 9: null input ──");
        try {
            exchange.convert(null, "EUR", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ── Scenario 10: case insensitive ──────────────────────
        System.out.println("\n── Scenario 10: case insensitive ──");
        exchange.convert("usd", "eur", 100);
    }
}
