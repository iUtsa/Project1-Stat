public class BinomialCalculator {

    /**
     * Method to calculate the factorial of a number.
     * @param n The number to calculate the factorial for.
     * @return The factorial of the number.
     */
    public static long factorial(int n) {
        long fact = 1;
        // Loop to multiply numbers from 2 to n
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    /**
     * Method to calculate combinations (nCr).
     * @param n The total number of items.
     * @param r The number of items to choose.
     * @return The number of combinations (nCr).
     */
    public static long combinations(int n, int r) {
        // If r is greater than n, return 0 as it's not possible
        if (r > n) return 0;
        // Calculate nCr using factorials
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    /**
     * Method to calculate binomial probability.
     * @param n The number of trials.
     * @param x The number of successes.
     * @param p The probability of success on a single trial.
     * @return The binomial probability.
     */
    public static double binomialProbability(int n, int x, double p) {
        // q is the probability of failure (1 - p)
        double q = 1 - p;
        // Calculate binomial probability using the formula: nCr * p^x * q^(n-x)
        return combinations(n, x) * Math.pow(p, x) * Math.pow(q, n - x);
    }
}