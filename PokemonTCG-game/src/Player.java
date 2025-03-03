import java.util.*;

public class Player {
    private String name;
    private ArrayList<Card> hand, pool;
    private ArrayList<PokemonCard> bench;
    private PokemonCard[] active;

    private Deck deck;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.bench = new ArrayList<>();
        this.deck = new Deck();
        this.pool = new ArrayList<>();
        active = new PokemonCard[1];

        initializePool();
    }

    public String getName() {
        return name;
    }

    public PokemonCard getActive() {
        return (active.length > 0) ? active[0] : null;
    }

    public ArrayList<PokemonCard> getBench() {
        return bench;
    }

    public void drawInitialHand(Deck deck) {
        for (int i = 0; i < 7; i++) {
            drawCard(this.deck);
        }
    }

    public void prebattle(Deck deck){
        drawInitialHand(this.deck);

    }
    public String attackOpponent(Player opponent) {
        if (getActive() == null) {
            return "No active Pokémon to attack with!";
        }
        if (opponent.getActive() == null) {
            return "Opponent has no active Pokémon!";
        }

        getActive().attack(opponent.getActive()); // Attack opponent’s active Pokémon
        return getActive().getName() + " attacked " + opponent.getActive().getName() + "!";
    }


    public void drawCard(Deck deck) {
        if (deck.getDeckSize() > 0) {
            hand.add(deck.drawCard());

        }
    }

    public int getPrizeAmount(){
        return pool.size();
    }

    public String displayHand() {
        if (hand.isEmpty()) return "No Cards in Hand";
        StringBuilder handDisplay = new StringBuilder(" ");
        for (Card card : hand) {
            handDisplay.append(card.getName()).append(", ");
        }
        return handDisplay.toString();
    }

    public String displayBench() {
        if (bench.isEmpty()) return "No Pokémon on Bench";
        StringBuilder benchDisplay = new StringBuilder("Bench: ");
        for (PokemonCard pokemon : bench) {
            benchDisplay.append(pokemon.getName()).append(", ");
        }
        return benchDisplay.toString();
    }

    public PokemonCard getFirstPokemon() {
        for (PokemonCard pokemon : bench) {
            return pokemon; // Returns the first Pokémon on the bench
        }
        return null;
    }

    // Method to initialize the prize pool with 1 card
    public void initializePool() {
        pool = new ArrayList<>(); // ✅ Initialize prize pool list

        for (int i = 0; i < 3; i++) { // ✅ Ensure exactly 3 prize cards are added
            if (deck.getDeckSize() > 0) {
                Card prizeCard = deck.drawCard(); // ✅ Draw one card from deck
                pool.add(prizeCard);  // ✅ Store in prize pool
            } else {
                System.out.println("Deck is empty! No prize card can be added.");
                break; // ✅ Prevent infinite loop if deck runs out
            }
        }
    }


    // Method to claim the prize card
    public void claimPrizeCard() {
        if (!pool.isEmpty()) {
            Card claimedCard = pool.remove(0); // ✅ Take the prize card
            hand.add(claimedCard); // ✅ Add it to player's hand
            System.out.println(name + " claimed their prize card: " + claimedCard.getName());
        } else {
            System.out.println("No prize card available to claim.");
        }
    }

    // Getter for pool (needed for UI updates)
    public ArrayList<Card> getPool() {
        return pool;
    }



    public PokemonCard playPokemonToBenchUI() {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) instanceof PokemonCard) {
                PokemonCard pokemon = (PokemonCard) hand.remove(i);
                bench.add(pokemon);
                System.out.println(name + " played " + pokemon.getName() + " on the bench!");
                return pokemon;
            }
        }
        System.out.println("No Pokémon available to play.");
        return null;
    }

    public String useTrainerCardUI(Deck deck) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) instanceof TrainerCard) {
                TrainerCard trainer = (TrainerCard) hand.remove(i);
                return "Used Trainer Card: " + trainer.getName();
            }
        }
        return "No Trainer Card available!";
    }

    public String addPokemonToField() {
        if (active[0] != null) {
            return "You already have an active Pokémon!";
        }
        if (bench.isEmpty()) {
            return "No Pokémon available on the bench!";
        }

        active[0] = bench.remove(0); // Move first bench Pokémon to active slot
        return active[0].getName() + " is now the active Pokémon!";
    }

    public String addPokemonToBench() {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) instanceof PokemonCard) {
                PokemonCard pokemon = (PokemonCard) hand.remove(i);
                bench.add(pokemon);
                return "Added " + pokemon.getName() + " to the bench!";
            }
        }
        return "No Pokémon available to add to the bench!";
    }
    public String attachEnergyToPokemon() {
        if (active[0] == null) {
            return "No active Pokémon to attach energy!";
        }
        return "Energy attached to " + active[0].getName() + "!";
    }

    public void setHand(ArrayList<Card> newHand) {
        this.hand = newHand;
    }
    public ArrayList<Card> getHand() {
        return hand;
    }


    public void setActivePokemon(PokemonCard pokemon) {
        this.active[0] = pokemon;
    }




    public String attachEnergyToPokemonUI() {
        if (active[0] != null) {
            return "Energy Attached to " + active[0].getName() + "!";
        }
        return "No Active Pokémon to Attach Energy!";
    }

    public void switchToNextPokemon() {
        if (!bench.isEmpty()) {
            active[0] = bench.remove(0); // Move first Pokémon from bench to active
        } else {
            active[0] = null; // No Pokémon left
        }
    }


}
