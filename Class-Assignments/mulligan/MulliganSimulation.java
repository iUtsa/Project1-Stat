import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The MulliganSimulation class simulates the probability of drawing a mulligan
 * in a Pokémon card game. A mulligan occurs when no Pokémon cards are drawn
 * in the initial hand of 7 cards.
 */
public class MulliganSimulation {
    private static final int TOTAL_CARDS = 60; // Total number of cards in the deck
    private static final int HAND_SIZE = 7; // Number of cards drawn in a hand
    private static final int EXPERIMENTS = 10_000; // Number of experiments to run

    public static void main(String[] args) {
        String fileName = "mulligan_results.csv"; // Output file name

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Pokemon Count,Mulligan Probability (%)\n"); // Write CSV header

            // Simulate for each possible number of Pokémon cards in the deck
            for (int pokemonCount = 1; pokemonCount <= TOTAL_CARDS; pokemonCount++) {
                double mulliganProbability = simulateMulliganProbability(pokemonCount);
                writer.write(pokemonCount + "," + mulliganProbability + "\n"); // Write result to CSV
                System.out.printf("Pokemon Count: %d -> Mulligan Probability: %.2f%%\n", pokemonCount, mulliganProbability); // Print result to console
            }

            System.out.println("Results saved to " + fileName); // Indicate completion
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage()); // Handle file writing errors
        }
    }

    /**
     * Simulates the mulligan probability for a given number of Pokémon cards.
     * @param pokemonCount The number of Pokémon cards in the deck.
     * @return The probability of drawing a mulligan as a percentage.
     */
    private static double simulateMulliganProbability(int pokemonCount) {
        int mulliganCount = 0; // Counter for the number of mulligans

        // Run the simulation for the specified number of experiments
        for (int i = 0; i < EXPERIMENTS; i++) {
            if (isMulligan(pokemonCount)) {
                mulliganCount++;
            }
        }

        return (mulliganCount / (double) EXPERIMENTS) * 100; // Calculate and return the mulligan probability as a percentage
    }

    /**
     * Determines if a hand is a mulligan (no Pokémon in the first 7 cards).
     * @param pokemonCount The number of Pokémon cards in the deck.
     * @return True if the hand is a mulligan, false otherwise.
     */
    private static boolean isMulligan(int pokemonCount) {
        ArrayList<String> deck = new ArrayList<>();

        // Add Pokémon cards to the deck
        for (int i = 0; i < pokemonCount; i++) {
            deck.add("Pokemon");
        }
        // Add Energy cards to the deck
        for (int i = pokemonCount; i < TOTAL_CARDS; i++) {
            deck.add("Energy");
        }

        // Shuffle the deck
        Collections.shuffle(deck, new Random());

        // Check the first 7 cards for at least one Pokémon
        for (int i = 0; i < HAND_SIZE; i++) {
            if (deck.get(i).equals("Pokemon")) {
                return false; // No mulligan, found at least one Pokémon
            }
        }
        return true; // Mulligan, no Pokémon in the first 7 cards
    }
}