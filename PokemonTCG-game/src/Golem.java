import javax.swing.*;

/**
 * The Golem class represents a Golem Pokemon card.
 */
public class Golem extends PokemonCard {
    private Attack earthQuake; // The first attack of Golem
    private Attack stoneEdge; // The second attack of Golem

    /**
     * Constructor to initialize the Golem card with its properties.
     */
    public Golem() {
        super("Golem", 88, "Rock", 30, "rock.png",
                new Attack("Earthquake", 20),
                new Attack("Stone Edge", 30));

        // Assign attacks from the superclass
        this.earthQuake = getAttack1();
        this.stoneEdge = getAttack2();
    }

    /**
     * Method to use the first attack (Earthquake) on an opponent.
     * @param opponent The opponent Pokemon card.
     */
    @Override
    public void useAttack(PokemonCard opponent) {
        if (getEnergy() >= 1) {
            int damageDealt = earthQuake.getDamage();
            earthQuake.performAttack(this, opponent);
            opponent.takeDamage(damageDealt);

            JOptionPane.showMessageDialog(null, this.getName() + " used Earthquake and dealt " + damageDealt + " damage to " + opponent.getName(),
                    "Attack Info", JOptionPane.INFORMATION_MESSAGE);

            this.minusEnergy(1);
        } else {
            JOptionPane.showMessageDialog(null, this.getName() + " does not have enough Energy to use Earthquake!",
                    "Energy Required", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Method to use the second attack (Stone Edge) on an opponent.
     * @param opponent The opponent Pokemon card.
     */
    @Override
    public void useAttack2(PokemonCard opponent) {
        if (getEnergy() >= 2) {
            int damageDealt = stoneEdge.getDamage();
            stoneEdge.performAttack(this, opponent);
            opponent.takeDamage(damageDealt);

            // 10% chance to paralyze the opponent
            if (Math.random() < 0.1) {
                opponent.setStatusEffect("Paralyze");
                JOptionPane.showMessageDialog(null, opponent.getName() + " is now Paralyzed!",
                        "Paralyze Effect", JOptionPane.INFORMATION_MESSAGE);
            }

            JOptionPane.showMessageDialog(null, this.getName() + " used Stone Edge and dealt " + damageDealt + " damage to " + opponent.getName(),
                    "Attack Info", JOptionPane.INFORMATION_MESSAGE);

            this.minusEnergy(2);
        } else {
            JOptionPane.showMessageDialog(null, this.getName() + " does not have enough Energy to use Stone Edge!",
                    "Energy Required", JOptionPane.WARNING_MESSAGE);
        }
    }
}