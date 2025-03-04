import javax.swing.*;

public class Raikou extends PokemonCard {
    private Attack thunderFang;
    private Attack voltStorm;

    public Raikou() {
        super("Raikou", 140, "Electric", 45, "raikou.png",
                new Attack("Thunder Fang", 35),
                new Attack("Volt Storm", 45));
        this.thunderFang = new Attack("Thunder Fang", 35);
        this.voltStorm = new Attack("Volt Storm", 45);
    }

    @Override
    public void useAttack(PokemonCard opponent) {
        if (getEnergy() >= 2) {
            int damageDealt = thunderFang.getDamage();
            thunderFang.performAttack(this, opponent);
            opponent.takeDamage(damageDealt);

            if (Math.random() < 0.2) { // 20% chance to Paralyze
                opponent.setStatusEffect("Paralyze");
                System.out.println(opponent.getName() + " is now Paralyzed!");
            }

            JOptionPane.showMessageDialog(null, this.getName() + " used Thunder Fang and dealt " + damageDealt + " damage to " + opponent.getName(),
                    "Attack Info", JOptionPane.INFORMATION_MESSAGE);

            this.minusEnergy(2);
        }
    }

    @Override
    public void useAttack2(PokemonCard opponent) {
        if (getEnergy() >= 3) {
            int damageDealt = voltStorm.getDamage();
            voltStorm.performAttack(this, opponent);
            opponent.takeDamage(damageDealt);

            JOptionPane.showMessageDialog(null, this.getName() + " used Volt Storm and dealt " + damageDealt + " damage to " + opponent.getName(),
                    "Attack Info", JOptionPane.INFORMATION_MESSAGE);

            this.minusEnergy(3);
        }
    }
}
