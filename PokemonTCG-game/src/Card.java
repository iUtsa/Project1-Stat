public abstract class Card {
    protected String name; // The name of the card

    /**
     * Constructor to initialize the name of the card.
     * @param name The name of the card.
     */
    public Card(String name) {
        this.name = name;
    }

    /**
     * Getter method to retrieve the name of the card.
     * @return The name of the card.
     */
    public String getName() {
        return name;
    }
}