public class EnergyCard extends Card {
    private String energyType;
    private int energyBoost;
    private int hpBoost;

    public EnergyCard(String name, String energyType, int energyBoost, int hpBoost) {
        super(name);
        this.energyType = energyType;
        this.energyBoost = energyBoost;
        this.hpBoost = hpBoost;
    }

    public String getEnergyType() {
        return energyType;
    }



    public int getEnergyBoost() {
        return energyBoost;
    }

    public int getHpBoost() {
        return hpBoost;
    }
}
