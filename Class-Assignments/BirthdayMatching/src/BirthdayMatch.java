import java.util.Random;
import java.util.Scanner;

public class BirthdayMatch {
    /**
     * Method to calculate the probability of at least two people sharing the same birthday.
     * @param classSize The number of people in the class.
     * @param runs The number of simulation runs.
     * @return The probability of at least two people sharing the same birthday as a percentage.
     */
    public static double calculateProbability(int classSize, int runs) {
        int count = 0; // Counter for simulations with at least one shared birthday
        Random random = new Random();

        // Run the simulation 'runs' times
        for (int i = 0; i < runs; i++) {
            boolean[] birthdays = new boolean[365]; // Track birthdays using a boolean array
            boolean hasDuplicate = false; // Flag to check for duplicate birthdays

            // Simulate birthdays for 'classSize' number of people
            for (int j = 0; j < classSize; j++) {
                Person person = new Person(random); // Create a new person with a random birthday
                int birthday = person.getBirthday(); // Get the person's birthday

                // If the birthday is already marked, it's a duplicate
                if (birthdays[birthday]) {
                    hasDuplicate = true; // Set the flag to true if a duplicate is found
                    break; // Exit the loop if a duplicate is found
                }
                birthdays[birthday] = true; // Mark the birthday as used
            }

            if (hasDuplicate) {
                count++; // Increment count if a duplicate birthday was found
            }
        }

        return (double) count / runs * 100; // Convert probability to percentage
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of people in class: ");
        int classSize = scanner.nextInt(); // Get the class size from user input
        System.out.print("Enter number of simulations: ");
        int runs = scanner.nextInt(); // Get the number of simulations from user input
        scanner.close(); // Close the scanner to prevent resource leaks

        if (runs < 10) {
            System.out.println("Warning: Too few simulations! Results may not be accurate.");
        }

        double probability = calculateProbability(classSize, runs); // Calculate the probability
        System.out.printf("Probability of shared birthday in a class of %d: %.2f%%\n", classSize, probability); // Display the result
    }
}