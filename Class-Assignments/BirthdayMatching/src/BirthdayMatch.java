package BirthdayMatching.src ;
import java.util.Random;
import java.util.Scanner;

public class BirthdayMatch {
    public static double calculateProbability(int classSize, int runs) {
        int count = 0;
        Random random = new Random();

        for (int i = 0; i < runs; i++) {
            boolean[] birthdays = new boolean[365]; // Track birthdays using a boolean array
            boolean hasDuplicate = false;

            for (int j = 0; j < classSize; j++) {
                Person person = new Person(random);
                int birthday = person.getBirthday();

                // If the birthday is already marked, it's a duplicate
                if (birthdays[birthday]) {
                    hasDuplicate = true;
                    break;
                }
                birthdays[birthday] = true; // Mark the birthday as used
            }

            if (hasDuplicate) {
                count++;
            }
        }

        return (double) count / runs * 100; // Convert probability to percentage
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of people in class: ");
        int classSize = scanner.nextInt();
        System.out.print("Enter number of simulations: ");
        int runs = scanner.nextInt();
        scanner.close();

        if (runs < 10) {
            System.out.println("Warning: Too few simulations! Results may not be accurate.");
        }

        double probability = calculateProbability(classSize, runs);
        System.out.printf("Probability of shared birthday in a class of %d: %.2f%%\n", classSize, probability);
    }
}
