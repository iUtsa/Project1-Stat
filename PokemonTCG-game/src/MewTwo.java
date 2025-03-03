import javax.swing.*;
public class MewTwo extends PokemonCard {
    private Attack ancientPower;
    private Attack psycStrike;


    public MewTwo() {
        super("Mewtwo", 100, "Pcychic", 35, "mewtwo.png",
                new Attack("Ancient Power", 25),
                new Attack("Psycstrike", 35));
        this.psycStrike = new Attack("Psyc Strike", 35);
        this.ancientPower = new Attack("Ancient Power", 25);
    }

    @Override
    public void useAttack(PokemonCard opponent) {
        if (getEnergy() >= 1) {
            int damageDealt = ancientPower.getDamage();
            ancientPower.performAttack(this, opponent);
            opponent.takeDamage(damageDealt);

            if (Math.random() < 0.1) {
                opponent.setStatusEffect("Paralyze");
                System.out.println(opponent.getName() + " is now Paralyzed!");
            }

            JOptionPane.showMessageDialog(null, this.getName() + " used Ancient Power and dealt " + damageDealt + " damage to " + opponent.getName(),
                    "Attack Info", JOptionPane.INFORMATION_MESSAGE);

            this.minusEnergy(1);
        }
    }

    @Override
    public void useAttack2(PokemonCard opponent) {
        if (getEnergy() >= 2) {
            int damageDealt = psycStrike.getDamage();
            psycStrike.performAttack(this, opponent);
            opponent.takeDamage(damageDealt);

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
