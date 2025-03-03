import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get inputs from user
        System.out.print("Enter the number of trials (n): ");
        int n = scanner.nextInt();

        System.out.print("Enter the number of successes (x): ");
        int x = scanner.nextInt();

        System.out.print("Enter the probability of success (p): ");
        double p = scanner.nextDouble();

        // Calculate binomial probability using BinomialCalculator class
        double probability = BinomialCalculator.binomialProbability(n, x, p);

        // Display the result
        System.out.printf("The binomial probability P(X = %d) is: %.5f%n", x, probability);

        scanner.close();
    }
}
