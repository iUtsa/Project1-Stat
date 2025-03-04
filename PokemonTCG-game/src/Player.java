import java.util.*;

/**
 * The Player class represents a player in the Pokemon TCG game.
 * It contains methods to manage the player's hand, bench, active Pokemon, and deck.
 */
public class Player {
    private String name; // The name of the player
    private ArrayList<Card> hand, pool; // The player's hand and prize pool
    private ArrayList<PokemonCard> bench; // The player's bench
    private PokemonCard[] active; // The player's active Pokemon
    private Deck deck; // The player's deck

    /**
     * Constructor to initialize the player with a name.
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.bench = new ArrayList<>();
        this.deck = new Deck();
        this.pool = new ArrayList<>();
        active = new PokemonCard[1];

        initializePool();
    }

    /**
     * Getter method to retrieve the player's name.
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method to retrieve the player's active Pokemon.
     * @return The active Pokemon, or null if there is no active Pokemon.
     */
    public PokemonCard getActive() {
        return (active.length > 0) ? active[0] : null;
    }

    /**
     * Getter method to retrieve the player's bench.
     * @return The player's bench.
     */
    public ArrayList<PokemonCard> getBench() {
        return bench;
    }

    /**
     * Method to draw the initial hand of 7 cards from the deck.
     * @param deck The player's deck.
     */
    public void drawInitialHand(Deck deck) {
        for (int i = 0; i < 7; i++) {
            drawCard(this.deck);
        }
    }

    /**
     * Method to prepare for battle by drawing the initial hand.
     * @param deck The player's deck.
     */
    public void prebattle(Deck deck) {
        drawInitialHand(this.deck);
    }

    /**
     * Method to attack the opponent's active Pokemon.
     * @param opponent The opponent player.
     * @return A message indicating the result of the attack.
     */
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

    /**
     * Method to draw a card from the deck and add it to the player's hand.
     * @param deck The player's deck.
     */
    public void drawCard(Deck deck) {
        if (deck.getDeckSize() > 0) {
            hand.add(deck.drawCard());
        }
    }

    /**
     * Method to get the number of prize cards in the pool.
     * @return The number of prize cards in the pool.
     */
    public int getPrizeAmount() {
        return pool.size();
    }

    /**
     * Method to display the cards in the player's hand.
     * @return A string representation of the cards in the hand.
     */
    public String displayHand() {
        if (hand.isEmpty()) return "No Cards in Hand";
        StringBuilder handDisplay = new StringBuilder(" ");
        for (Card card : hand) {
            handDisplay.append(card.getName()).append(", ");
        }
        return handDisplay.toString();
    }

    /**
     * Method to display the Pokemon on the player's bench.
     * @return A string representation of the Pokemon on the bench.
     */
    public String displayBench() {
        if (bench.isEmpty()) return "No Pokémon on Bench";
        StringBuilder benchDisplay = new StringBuilder("Bench: ");
        for (PokemonCard pokemon : bench) {
            benchDisplay.append(pokemon.getName()).append(", ");
        }
        return benchDisplay.toString();
    }

    /**
     * Method to get the first Pokemon on the player's bench.
     * @return The first Pokemon on the bench, or null if the bench is empty.
     */
    public PokemonCard getFirstPokemon() {
        for (PokemonCard pokemon : bench) {
            return pokemon; // Returns the first Pokémon on the bench
        }
        return null;
    }

    /**
     * Method to initialize the prize pool with 3 cards from the deck.
     */
    public void initializePool() {
        pool = new ArrayList<>(); // Initialize prize pool list

        for (int i = 0; i < 3; i++) { // Ensure exactly 3 prize cards are added
            if (deck.getDeckSize() > 0) {
                Card prizeCard = deck.drawCard(); // Draw one card from deck
                pool.add(prizeCard);  // Store in prize pool
            } else {
                System.out.println("Deck is empty! No prize card can be added.");
                break; // Prevent infinite loop if deck runs out
            }
        }
    }

    /**
     * Method to claim a prize card from the pool and add it to the player's hand.
     */
    public void claimPrizeCard() {
        if (!pool.isEmpty()) {
            Card claimedCard = pool.remove(0); // Take the prize card
            hand.add(claimedCard); // Add it to player's hand
            System.out.println(name + " claimed their prize card: " + claimedCard.getName());
        } else {
            System.out.println("No prize card available to claim.");
        }
    }

    /**
     * Getter method to retrieve the prize pool.
     * @return The prize pool.
     */
    public ArrayList<Card> getPool() {
        return pool;
    }

    /**
     * Method to play a Pokemon card from the hand to the bench.
     * @return The Pokemon card played to the bench, or null if no Pokemon card is available.
     */
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

    /**
     * Method to use a Trainer card from the hand.
     * @param deck The player's deck.
     * @return A message indicating the result of using the Trainer card.
     */
    public String useTrainerCardUI(Deck deck) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) instanceof TrainerCard) {
                TrainerCard trainer = (TrainerCard) hand.remove(i);
                return "Used Trainer Card: " + trainer.getName();
            }
        }
        return "No Trainer Card available!";
    }

    /**
     * Method to add a Pokemon from the bench to the active slot.
     * @return A message indicating the result of adding the Pokemon to the active slot.
     */
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

    /**
     * Method to add a Pokemon from the hand to the bench.
     * @return A message indicating the result of adding the Pokemon to the bench.
     */
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

    /**
     * Method to attach energy to the active Pokemon.
     * @return A message indicating the result of attaching energy.
     */
    public String attachEnergyToPokemon() {
        if (active[0] == null) {
            return "No active Pokémon to attach energy!";
        }
        return "Energy attached to " + active[0].getName() + "!";
    }

    /**
     * Setter method to set the player's hand.
     * @param newHand The new hand of cards.
     */
    public void setHand(ArrayList<Card> newHand) {
        this.hand = newHand;
    }

    /**
     * Getter method to retrieve the player's hand.
     * @return The player's hand.
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Setter method to set the player's active Pokemon.
     * @param pokemon The Pokemon to set as active.
     */
    public void setActivePokemon(PokemonCard pokemon) {
        this.active[0] = pokemon;
    }

    /**
     * Method to attach energy to the active Pokemon (UI version).
     * @return A message indicating the result of attaching energy.
     */
    public String attachEnergyToPokemonUI() {
        if (active[0] != null) {
            return "Energy Attached to " + active[0].getName() + "!";
        }
        return "No Active Pokémon to Attach Energy!";
    }

    /**
     * Method to switch to the next Pokemon on the bench.
     */
    public void switchToNextPokemon() {
        if (!bench.isEmpty()) {
            active[0] = bench.remove(0); // Move first Pokémon from bench to active
        } else {
            active[0] = null; // No Pokémon left
        }
    }
}