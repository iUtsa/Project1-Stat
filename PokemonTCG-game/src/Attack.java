public class Attack {
    private String name;
    private int damage;

    public Attack(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }



    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public void performAttack(PokemonCard attacker, PokemonCard opponent) {
        int damageDealt = getDamage();

        // Apply damage to opponent
        opponent.takeDamage(damageDealt);

        System.out.println(attacker.getName() + " used " + name + " on " + opponent.getName() + " for " + damageDealt + " damage!");
    }

}
