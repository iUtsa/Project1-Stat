import javax.swing.*;

/**
 * The Pikachu class represents a Pikachu Pokemon card.
 */
public class Pikachu extends PokemonCard {
    private Attack thunderShock; // The first attack of Pikachu
    private Attack thunderBall; // The second attack of Pikachu

    /**
     * Constructor to initialize the Pikachu card with its properties.
     */
    public Pikachu() {
        super("Pikachu", 60, "Electric", 20, "pikachu.png",
                new Attack("Thunder Shock", 20),
                new Attack("Thunder Ball", 25));

        // Assign attacks from the superclass
        this.thunderShock = getAttack1();
        this.thunderBall = getAttack2();
    }

    /**
     * Method to use the first attack (Thunder Shock) on an opponent.
     * @param opponent The opponent Pokemon card.
     */
    @Override
    public void useAttack(PokemonCard opponent) {
        if (getEnergy() >= 1) {
            int damageDealt = thunderShock.getDamage();
            thunderShock.performAttack(this, opponent);
            opponent.takeDamage(damageDealt);

            // 10% chance to paralyze the opponent
            if (Math.random() < 0.1) {
                opponent.setStatusEffect("Paralyze");
                System.out.println(opponent.getName() + " is now Paralyzed!");
            }

            JOptionPane.showMessageDialog(null, this.getName() + " used Thunder Shock and dealt " + damageDealt + " damage to " + opponent.getName(),
                    "Attack Info", JOptionPane.INFORMATION_MESSAGE);

            this.minusEnergy(1);
        }
    }

    /**
     * Method to use the second attack (Thunder Ball) on an opponent.
     * @param opponent The opponent Pokemon card.
     */
    @Override
    public void useAttack2(PokemonCard opponent) {
        if (getEnergy() >= 2) {
            int damageDealt = thunderBall.getDamage();
            thunderBall.performAttack(this, opponent);
            opponent.takeDamage(damageDealt);

            // 10% chance to paralyze the opponent
            if (Math.random() < 0.1) {
                opponent.setStatusEffect("Paralyze");
                System.out.println(opponent.getName() + " is now Paralyzed!");
            }

            JOptionPane.showMessageDialog(null, this.getName() + " used Thunder Ball and dealt " + damageDealt + " damage to " + opponent.getName(),
                    "Attack Info", JOptionPane.INFORMATION_MESSAGE);

            this.minusEnergy(2);
        }
    }
}