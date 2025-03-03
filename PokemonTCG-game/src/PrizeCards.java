import java.util.ArrayList;
import javax.swing.*;

public class PrizeCards {
    private ArrayList<Card> prizeCards;
    private JLabel prizeCardLabel; // New Swing Label

    public PrizeCards() {
        prizeCards = new ArrayList<>();
        prizeCardLabel = new JLabel("Prize Cards Left: " + prizeCards.size());
    }

    public void addPrizeCard(Card card) {
        prizeCards.add(card);
        updatePrizeDisplay();
    }

    public Card takePrizeCard() {
        if (!prizeCards.isEmpty()) {
            Card card = prizeCards.remove(0);
            updatePrizeDisplay();
            return card;
        }
        return null;
    }

    public boolean isEmpty() {
        return prizeCards.isEmpty();
    }

    public JLabel getPrizeCardLabel() {
        return prizeCardLabel;
    }

    private void updatePrizeDisplay() {
        prizeCardLabel.setText("Prize Cards Left: " + prizeCards.size());
    }
}
