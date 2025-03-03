import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MulliganSimulation {
    private static final int TOTAL_CARDS = 60;
    private static final int HAND_SIZE = 7;
    private static final int EXPERIMENTS = 10_000;

    public static void main(String[] args) {
        String fileName = "mulligan_results.csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Pokemon Count,Mulligan Probability (%)\n");

            for (int pokemonCount = 1; pokemonCount <= TOTAL_CARDS; pokemonCount++) {
                double mulliganProbability = simulateMulliganProbability(pokemonCount);
                writer.write(pokemonCount + "," + mulliganProbability + "\n");
                System.out.printf("Pokemon Count: %d -> Mulligan Probability: %.2f%%\n", pokemonCount, mulliganProbability);
            }

            System.out.println("Results saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    private static double simulateMulliganProbability(int pokemonCount) {
        int mulliganCount = 0;

        for (int i = 0; i < EXPERIMENTS; i++) {
            if (isMulligan(pokemonCount)) {
                mulliganCount++;
            }
        }

        return (mulliganCount / (double) EXPERIMENTS) * 100;
    }

    private static boolean isMulligan(int pokemonCount) {
        ArrayList<String> deck = new ArrayList<>();

        for (int i = 0; i < pokemonCount; i++) {
            deck.add("Pokemon");
        }
        for (int i = pokemonCount; i < TOTAL_CARDS; i++) {
            deck.add("Energy");
        }

        Collections.shuffle(deck, new Random());

        for (int i = 0; i < HAND_SIZE; i++) {
            if (deck.get(i).equals("Pokemon")) {
                return false; // No mulligan, found at least one Pokémon
            }
        }
        return true; // Mulligan, no Pokémon in the first 7 cards
    }
}