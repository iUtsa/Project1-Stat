import javax.swing.*;

public class Charmander extends PokemonCard {

    public Charmander() {
        super("Charmander", 60, "Fire", 12, "charmander.png",
                new Attack("Flamethrower", 30),
                new Attack("Fire Blast", 35));
    }

    @Override
    public void useAttack(PokemonCard opponent) {
        if (getEnergy() >= 1) {
            Attack flamethrower = getAttack1(); // ✅ Retrieve the attack from superclass
            int damageDealt = flamethrower.getDamage(); // ✅ Get correct damage
            flamethrower.performAttack(this, opponent);
            opponent.takeDamage(damageDealt); // ✅ Ensure HP is reduced properly

            JOptionPane.showMessageDialog(null, this.getName() + " used Flamethrower and dealt " + damageDealt + " damage to " + opponent.getName(),
                    "Attack Info", JOptionPane.INFORMATION_MESSAGE);

            this.minusEnergy(1); // ✅ Reduce energy only if attack is performed
        } else {
            JOptionPane.showMessageDialog(null, this.getName() + " does not have enough Energy to use Flamethrower!",
                    "Energy Required", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void useAttack2(PokemonCard opponent) {
        if (getEnergy() >= 2) {
            Attack fireBlast = getAttack2(); // ✅ Retrieve the second attack from superclass
            int damageDealt = fireBlast.getDamage(); // ✅ Get correct damage
            fireBlast.performAttack(this, opponent);
            opponent.takeDamage(damageDealt); // ✅ Ensure HP is reduced properly

            JOptionPane.showMessageDialog(null, this.getName() + " used Fire Blast and dealt " + damageDealt + " damage to " + opponent.getName(),
                    "Attack Info", JOptionPane.INFORMATION_MESSAGE);

            this.minusEnergy(2); // ✅ Reduce energy only if attack is performed
        } else {
            JOptionPane.showMessageDialog(null, this.getName() + " does not have enough Energy to use Fire Blast!",
                    "Energy Required", JOptionPane.WARNING_MESSAGE);
        }
    }
}

