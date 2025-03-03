import javax.swing.*;

public class Pikachu extends PokemonCard {
    private Attack thunderShock;
    private Attack thunderBall;

    public Pikachu() {
        super("Pikachu", 60, "Electric", 20, "pikachu.png",
                new Attack("Thunder Shock", 20),
                new Attack("Thunder Ball", 25));

        // Assign attacks from the superclass
        this.thunderShock = getAttack1();
        this.thunderBall = getAttack2();
    }

    @Override
    public void useAttack(PokemonCard opponent) {
        if (getEnergy() >= 1) {
            int damageDealt = thunderShock.getDamage();
            thunderShock.performAttack(this, opponent);
            opponent.takeDamage(damageDealt);

            if (Math.random() < 0.1) {
                opponent.setStatusEffect("Paralyze");
                System.out.println(opponent.getName() + " is now Paralyzed!");
            }

            JOptionPane.showMessageDialog(null, this.getName() + " used Thunder Shock and dealt " + damageDealt + " damage to " + opponent.getName(),
                    "Attack Info", JOptionPane.INFORMATION_MESSAGE);

            this.minusEnergy(1);
        }
    }

    @Override
    public void useAttack2(PokemonCard opponent) {
        if (getEnergy() >= 2) {
            int damageDealt = thunderBall.getDamage();
            thunderBall.performAttack(this, opponent);
            opponent.takeDamage(damageDealt);

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

