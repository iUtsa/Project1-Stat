public class EnergyCard extends Card {
    private String energyType; // The type of energy provided by the card
    private int energyBoost; // The amount of energy boost provided by the card
    private int hpBoost; // The amount of HP boost provided by the card

    /**
     * Constructor to initialize the EnergyCard with its properties.
     * @param name The name of the card.
     * @param energyType The type of energy provided by the card.
     * @param energyBoost The amount of energy boost provided by the card.
     * @param hpBoost The amount of HP boost provided by the card.
     */
    public EnergyCard(String name, String energyType, int energyBoost, int hpBoost) {
        super(name);
        this.energyType = energyType;
        this.energyBoost = energyBoost;
        this.hpBoost = hpBoost;
    }

    /**
     * Getter method to retrieve the type of energy provided by the card.
     * @return The type of energy provided by the card.
     */
    public String getEnergyType() {
        return energyType;
    }

    /**
     * Getter method to retrieve the amount of energy boost provided by the card.
     * @return The amount of energy boost provided by the card.
     */
    public int getEnergyBoost() {
        return energyBoost;
    }

    /**
     * Getter method to retrieve the amount of HP boost provided by the card.
     * @return The amount of HP boost provided by the card.
     */
    public int getHpBoost() {
        return hpBoost;
    }
}