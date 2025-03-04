import java.util.ArrayList;
import javax.swing.*;

/**
 * The PrizeCards class represents the prize cards in the Pokemon TCG game.
 * It contains methods to manage the prize cards and update the display.
 */
public class PrizeCards {
    private ArrayList<Card> prizeCards; // List of prize cards
    private JLabel prizeCardLabel; // Swing label to display the number of prize cards left

    /**
     * Constructor to initialize the PrizeCards with an empty list and a label.
     */
    public PrizeCards() {
        prizeCards = new ArrayList<>();
        prizeCardLabel = new JLabel("Prize Cards Left: " + prizeCards.size());
    }

    /**
     * Method to add a prize card to the list.
     * @param card The card to add to the prize cards.
     */
    public void addPrizeCard(Card card) {
        prizeCards.add(card);
        updatePrizeDisplay();
    }

    /**
     * Method to take a prize card from the list.
     * @return The card taken from the prize cards, or null if the list is empty.
     */
    public Card takePrizeCard() {
        if (!prizeCards.isEmpty()) {
            Card card = prizeCards.remove(0);
            updatePrizeDisplay();
            return card;
        }
        return null;
    }

    /**
     * Method to check if the prize cards list is empty.
     * @return True if the prize cards list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return prizeCards.isEmpty();
    }

    /**
     * Getter method to retrieve the prize card label.
     * @return The prize card label.
     */
    public JLabel getPrizeCardLabel() {
        return prizeCardLabel;
    }

    /**
     * Method to update the prize card display label.
     */
    private void updatePrizeDisplay() {
        prizeCardLabel.setText("Prize Cards Left: " + prizeCards.size());
    }
}