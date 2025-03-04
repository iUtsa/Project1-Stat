import javax.swing.*;

public class Charizard extends PokemonCard {
    private Attack flamethrower;
    private Attack fireBlast;

    public Charizard() {
        super("Charizard", 150, "Fire", 50, "charizard.png",
                new Attack("Flamethrower", 40),
                new Attack("Fire Blast", 50));
        this.flamethrower = new Attack("Flamethrower", 40);
        this.fireBlast = new Attack("Fire Blast", 50);
    }

    @Override
    public void useAttack(PokemonCard opponent) {
        if (getEnergy() >= 2) {
            int damageDealt = flamethrower.getDamage();
            flamethrower.performAttack(this, opponent);
            opponent.takeDamage(damageDealt);

            if (Math.random() < 0.1) {
                opponent.setStatusEffect("Burn");
                System.out.println(opponent.getName() + " is now Burned!");
            }

            JOptionPane.showMessageDialog(null, this.getName() + " used Flamethrower and dealt " + damageDealt + " damage to " + opponent.getName(),
                    "Attack Info", JOptionPane.INFORMATION_MESSAGE);

            this.minusEnergy(2);
        }
    }

    @Override
    public void useAttack2(PokemonCard opponent) {
        if (getEnergy() >= 3) {
            int damageDealt = fireBlast.getDamage();
            fireBlast.performAttack(this, opponent);
            opponent.takeDamage(damageDealt);

            JOptionPane.showMessageDialog(null, this.getName() + " used Fire Blast and dealt " + damageDealt + " damage to " + opponent.getName(),
                    "Attack Info", JOptionPane.INFORMATION_MESSAGE);

            this.minusEnergy(3);
        }
    }
}
