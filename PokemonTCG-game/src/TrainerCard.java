public class TrainerCard extends Card {
    public TrainerCard(String name) {
        super(name);
    }

    public void useEffect(Player player, Deck deck) {
        switch (name) {
            case "Potion50":
                System.out.println(player.getName() + " used a Potion! Heals 20 HP.");
                if (player.getActive() != null) {
                    player.getActive().heal(20);
                }
                break;

            case "Full Heal":
                System.out.println(player.getName() + " used Full Heal! Removes all status effects.");
                PokemonCard pokemon = player.getActive();
                if (pokemon != null) {
                    pokemon.setStatusEffect("None");
                }
                break;

            case "Professor Oak":
                System.out.println(player.getName() + " used Professor Oak! Draws 3 cards.");
                for (int i = 0; i < 3; i++) {
                    player.drawCard(deck);
                }
                break;

            case "Rare Candy":  // ✅ EVOLUTION FUNCTIONALITY
                System.out.println(player.getName() + " used Rare Candy! Instantly evolves a Pokémon.");
                evolvePokemon(player);
                break;

            default:
                System.out.println("Trainer card has no effect.");
        }
    }

    private void evolvePokemon(Player player) {
        PokemonCard activePokemon = player.getActive();

        if (activePokemon == null) {
            System.out.println("No Active Pokémon to evolve!");
            return;
        }

        // Evolution logic: Only specific Pokémon can evolve
        switch (activePokemon.getName()) {
            case "Charmander":
                player.setActivePokemon(new Charizard());  // ✅ Charmander evolves into Charizard
                System.out.println("Charmander evolved into Charizard!");
                break;

            case "Pikachu":
                player.setActivePokemon(new Raikou());  // ✅ Pikachu evolves into Raikou
                System.out.println("Pikachu evolved into Raikou!");
                break;

            default:
                System.out.println(activePokemon.getName() + " cannot evolve further!");
                break;
        }
    }

}
