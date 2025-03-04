import java.util.*;
import javax.swing.*;

/**
 * The PokemonCard class represents a Pokemon card in the game.
 * It contains attributes and methods common to all Pokemon cards.
 */
public abstract class PokemonCard extends Card {
    private int hp, energy; // The HP and energy of the Pokemon
    private String type; // The type of the Pokemon
    private int attackDamage; // The attack damage of the Pokemon
    private String imagePath; // The path to the image of the Pokemon
    private String statusEffect; // The status effect on the Pokemon
    private Attack attack1; // The first attack of the Pokemon
    private Attack attack2; // The second attack of the Pokemon

    /**
     * Constructor to initialize the PokemonCard with its properties.
     * @param name The name of the Pokemon.
     * @param hp The HP of the Pokemon.
     * @param type The type of the Pokemon.
     * @param attackDamage The attack damage of the Pokemon.
     * @param imagePath The path to the image of the Pokemon.
     * @param attack1 The first attack of the Pokemon.
     * @param attack2 The second attack of the Pokemon.
     */
    public PokemonCard(String name, int hp, String type, int attackDamage, String imagePath, Attack attack1, Attack attack2) {
        super(name);
        this.hp = hp;
        this.energy = 0;
        this.type = type;
        this.attackDamage = attackDamage; // Remove this later on
        this.imagePath = imagePath;
        this.statusEffect = "None"; // Default status effect
        this.attack1 = attack1;
        this.attack2 = attack2;
    }

    /**
     * Method to apply damage to the Pokemon.
     * @param damage The amount of damage to apply.
     */
    public void takeDamage(int damage) {
        System.out.println(name + " was hit for " + damage + " damage!");
        this.hp -= damage; // Ensure HP decreases instead of increasing
        if (this.hp <= 0) {
            this.hp = 0; // Prevent negative HP
        }
        System.out.println(name + " now has " + this.hp + " HP left.");
    }

    /**
     * Getter method to retrieve the HP of the Pokemon.
     * @return The HP of the Pokemon.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Getter method to retrieve the energy of the Pokemon.
     * @return The energy of the Pokemon.
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Method to reduce the energy of the Pokemon.
     * @param n The amount of energy to reduce.
     */
    public void minusEnergy(int n) {
        this.energy -= n;
        if (this.energy < 0) {
            this.energy = 0; // Ensure Energy never goes negative
        }
    }

    /**
     * Method to heal the Pokemon.
     * @param num The amount of HP to heal.
     */
    public void heal(int num) {
        hp += num;
    }

    /**
     * Getter method to retrieve the attack damage of the Pokemon.
     * @return The attack damage of the Pokemon.
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * Method to set the status effect on the Pokemon.
     * @param effect The status effect to set.
     */
    public void setStatusEffect(String effect) {
        this.statusEffect = effect;
        System.out.println(name + " is now " + effect + "!");
    }

    /**
     * Method to apply the status effect on the Pokemon.
     */
    public void applyStatusEffect() {
        if (statusEffect.equals("Paralyze")) {
            System.out.println(name + " is paralyzed and cannot attack this turn!");
        } else if (statusEffect.equals("Burn")) {
            hp -= 10;
            System.out.println(name + " is burned and takes 10 damage!");
        } else if (statusEffect.equals("Sleep")) {
            if (new Random().nextBoolean()) {
                System.out.println(name + " woke up!");
                statusEffect = "None";
            } else {
                System.out.println(name + " is still asleep and cannot attack.");
            }
        }
    }

    /**
     * Method to add energy to the Pokemon.
     * @param energy The energy card to add.
     */
    public void addEnergy(EnergyCard energy) {
        this.energy += energy.getEnergyBoost(); // Increase Energy
        this.hp += energy.getHpBoost(); // Increase HP (if applicable)
        // Ensure HP does not exceed max HP
        if (this.hp > 100) {
            this.hp = 100;
        }
        System.out.println(name + " received " + energy.getName() + "! Energy: " + this.energy + ", HP: " + this.hp);
    }

    /**
     * Method to attack an opponent Pokemon.
     * @param opponent The opponent Pokemon card.
     */
    public void attack(PokemonCard opponent) {
        System.out.println(this.name + " is attacking " + opponent.getName() + " with " + attackDamage + " damage!");
        opponent.takeDamage(attackDamage); // Only apply damage to the active Pokémon
    }

    /**
     * Getter method to retrieve the first attack of the Pokemon.
     * @return The first attack of the Pokemon.
     */
    public Attack getAttack1() {
        return attack1;
    }

    /**
     * Getter method to retrieve the second attack of the Pokemon.
     * @return The second attack of the Pokemon.
     */
    public Attack getAttack2() {
        return attack2;
    }

    /**
     * Abstract method to use the first attack on an opponent.
     * @param opponent The opponent Pokemon card.
     */
    public abstract void useAttack(PokemonCard opponent);

    /**
     * Abstract method to use the second attack on an opponent.
     * @param opponent The opponent Pokemon card.
     */
    public abstract void useAttack2(PokemonCard opponent);

    /**
     * Method to retrieve the image icon of the Pokemon.
     * @return The image icon of the Pokemon.
     */
    public ImageIcon getImageIcon() {
        return new ImageIcon(getClass().getResource("/assets/" + imagePath));
    }
}