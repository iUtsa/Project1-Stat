public class Battle {
    public static void startBattle(Player player1, Player player2) {
        System.out.println("\n=== Pokémon Battle Begins! ===");
        PokemonCard pokemon1 = player1.getFirstPokemon();
        PokemonCard pokemon2 = player2.getFirstPokemon();

        if (pokemon1 == null || pokemon2 == null) {
            System.out.println("One of the players has no Pokémon! Ending battle.");
            return;
        }

        System.out.println(player1.getName() + " is using " + pokemon1.getName() + " (" + pokemon1.getHp() + " HP)");
        System.out.println(player2.getName() + " is using " + pokemon2.getName() + " (" + pokemon2.getHp() + " HP)");

        while (pokemon1.getHp() > 0 && pokemon2.getHp() > 0) {
            pokemon1.applyStatusEffect(); // Apply status effect before attacking
            pokemon1.attack(pokemon2);
            if (pokemon2.getHp() <= 0) {
                System.out.println(pokemon2.getName() + " has fainted! " + player1.getName() + " wins!");
                return;
            }

            pokemon2.applyStatusEffect();
            pokemon2.attack(pokemon1);
            if (pokemon1.getHp() <= 0) {
                System.out.println(pokemon1.getName() + " has fainted! " + player2.getName() + " wins!");
                return;
            }
        }
    }
}
