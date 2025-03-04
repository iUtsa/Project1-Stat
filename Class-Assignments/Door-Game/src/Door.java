import java.util.Random;

/**
 * The Door class represents a door in the Door Game.
 * Each door can either have a prize behind it or not.
 */
class Door {
    private boolean hasPrize; // Indicates whether the door has a prize behind it

    /**
     * Constructor that initializes the door without a prize.
     */
    public Door() {
        this.hasPrize = false;
    }

    /**
     * Places a prize behind the door.
     */
    public void placePrize() {
        this.hasPrize = true;
    }

    /**
     * Checks if the door has a prize behind it.
     * @return True if the door has a prize, false otherwise.
     */
    public boolean hasPrize() {
        return this.hasPrize;
    }
}