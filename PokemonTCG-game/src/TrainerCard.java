import src.Player;

public  class TrainerCard extends Card {
    public TrainerCard(String name) {
        super(name);
    }

    public void useEffect(Player player, Deck deck) {
        switch (name) {
            case "Potion50":
                System.out.println(player.getName() + " used a Potion! Heals 20 HP.");
                break;
            case "Full Heal":
                System.out.println(player.getName() + " used Full Heal! Removes all status effects from their Pokémon.");
                PokemonCard pokemon = player.getFirstPokemon();
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
            default:
                System.out.println("Trainer card has no effect.");
        }
    }
}




