import java.util.Random;
import javax.swing.*;

/**
 * The CoinFlip class provides a method to simulate a coin flip.
 */
public class CoinFlip {
    private static final Random random = new Random(); // Random number generator

    /**
     * Method to simulate a coin flip.
     * @return True if the result is heads, false if the result is tails.
     */
    public static boolean flip() {
        boolean isHeads = random.nextBoolean(); // Randomly determine if the result is heads or tails
        String result = isHeads ? "Heads" : "Tails"; // Convert the boolean result to a string

        // Display the result in a message dialog
        JOptionPane.showMessageDialog(null, "Coin Flip Result: " + result, "Coin Flip", JOptionPane.INFORMATION_MESSAGE);
        return isHeads; // Return the result of the coin flip
    }
}