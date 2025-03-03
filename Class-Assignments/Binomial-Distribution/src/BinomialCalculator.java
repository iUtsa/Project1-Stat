public class BinomialCalculator {

    // Method to calculate factorial
    public static long factorial(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    // Method to calculate combinations (nCr)
    public static long combinations(int n, int r) {
        if (r > n) return 0;
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    // Method to calculate binomial probability
    public static double binomialProbability(int n, int x, double p) {
        double q = 1 - p;
        return combinations(n, x) * Math.pow(p, x) * Math.pow(q, n - x);
    }
}
