import javax.swing.*;

/**
 * The MewTwo class represents a MewTwo Pokemon card.
 */
public class MewTwo extends PokemonCard {
    private Attack ancientPower; // The first attack of MewTwo
    private Attack psycStrike; // The second attack of MewTwo

    /**
     * Constructor to initialize the MewTwo card with its properties.
     */
    public MewTwo() {
        super("Mewtwo", 100, "Psychic", 35, "mewtwo.png",
                new Attack("Ancient Power", 25),
                new Attack("Psycstrike", 35));
        this.psycStrike = new Attack("Psyc Strike", 35);
        this.ancientPower = new Attack("Ancient Power", 25);
    }

    /**
     * Method to use the first attack (Ancient Power) on an opponent.
     * @param opponent The opponent Pokemon card.
     */
    @Override
    public void useAttack(PokemonCard opponent) {
        if (getEnergy() >= 1) {
            int damageDealt = ancientPower.getDamage();
            ancientPower.performAttack(this, opponent);
            opponent.takeDamage(damageDealt);

            // 10% chance to paralyze the opponent
            if (Math.random() < 0.1) {
                opponent.setStatusEffect("Paralyze");
                System.out.println(opponent.getName() + " is now Paralyzed!");
            }

            JOptionPane.showMessageDialog(null, this.getName() + " used Ancient Power and dealt " + damageDealt + " damage to " + opponent.getName(),
                    "Attack Info", JOptionPane.INFORMATION_MESSAGE);

            this.minusEnergy(1);
        }
    }

    /**
     * Method to use the second attack (Psyc Strike) on an opponent.
     * @param opponent The opponent Pokemon card.
     */
    @Override
    public void useAttack2(PokemonCard opponent) {
        if (getEnergy() >= 2) {
            int damageDealt = psycStrike.getDamage();
            psycStrike.performAttack(this, opponent);
            opponent.takeDamage(damageDealt);

            // 10% chance to paralyze the opponent
            if (Math.random() < 0.1) {
                opponent.setStatusEffect("Paralyze");
                System.out.println(opponent.getName() + " is now Paralyzed!");
            }

            JOptionPane.showMessageDialog(null, this.getName() + " used Psyc Strike and dealt " + damageDealt + " damage to " + opponent.getName(),
                    "Attack Info", JOptionPane.INFORMATION_MESSAGE);

            this.minusEnergy(2);
        }
    }
}