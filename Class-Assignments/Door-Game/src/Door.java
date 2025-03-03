import java.util.Random;

class Door {
    private boolean hasPrize;

    public Door() {
        this.hasPrize = false;
    }

    public void placePrize() {
        this.hasPrize = true;
    }

    public boolean hasPrize() {
        return this.hasPrize;
    }
}
