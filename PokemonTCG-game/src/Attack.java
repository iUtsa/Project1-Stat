public class Attack {
    private String name; // The name of the attack
    private int damage; // The damage value of the attack

    /**
     * Constructor to initialize the name and damage of the attack.
     * @param name The name of the attack.
     * @param damage The damage value of the attack.
     */
    public Attack(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    /**
     * Getter method to retrieve the damage value of the attack.
     * @return The damage value of the attack.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Getter method to retrieve the name of the attack.
     * @return The name of the attack.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to perform the attack on an opponent.
     * @param attacker The PokemonCard that is performing the attack.
     * @param opponent The PokemonCard that is being attacked.
     */
    public void performAttack(PokemonCard attacker, PokemonCard opponent) {
        int damageDealt = getDamage();

        // Apply damage to opponent
        opponent.takeDamage(damageDealt);

        System.out.println(attacker.getName() + " used " + name + " on " + opponent.getName() + " for " + damageDealt + " damage!");
    }
}