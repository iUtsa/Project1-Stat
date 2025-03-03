import java.util.*;

public class Player {
    private String name;
    private List<String> deck;
    private List<String> hand;
    private List<String> prizePile;
    private List<String> discardPile;
    private List<String> board;
    private Random rand = new Random();

    public Player(String name) {
        this.name = name;
        this.deck = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.prizePile = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.board = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    /**
     * Builds a deck with the given number of Pokémon, Trainer, and Energy cards.
     */
    public void buildDeck(int pokemonCount, int trainerCount, int energyCount) {
        deck.clear();
        hand.clear();
        prizePile.clear();
        discardPile.clear();
        board.clear();

        for (int i = 0; i < pokemonCount; i++) deck.add("Pokemon");
        for (int i = 0; i < trainerCount; i++) deck.add("Trainer");
        for (int i = 0; i < energyCount; i++) deck.add("Energy");

        Collections.shuffle(deck);
    }

    /**
     * Draws a starting hand and sets up the prize pile.
     */
    public void drawStartingHand() {
        for (int i = 0; i < 7; i++) {
            hand.add(deck.remove(0));
        }
        for (int i = 0; i < 6; i++) {
            prizePile.add(deck.remove(0));
        }
    }

    /**
     * Simulates a turn where the player can play Pokémon, use Trainer cards, or attach Energy.
     */
    public void takeTurn(Scanner scanner) {
        System.out.println(name + "'s hand: " + hand);
        System.out.println("Choose an action: ");
        System.out.println("1 - Play a Pokémon");
        System.out.println("2 - Use a Trainer card");
        System.out.println("3 - Attach Energy");
        System.out.println("4 - End turn");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                playPokemon();
                break;
            case 2:
                useTrainer();
                break;
            case 3:
                attachEnergy();
                break;
            default:
                System.out.println("Turn ended.");
                break;
        }
    }

    /**
     * AI automatically plays Pokémon, uses a Trainer, and attaches Energy.
     */
    public void aiTakeTurn() {
        playPokemon();
        useTrainer();
        attachEnergy();
        System.out.println(name + " ends their turn.");
    }

    /**
     * Plays a Pokémon card if available.
     */
    private void playPokemon() {
        if (hand.contains("Pokemon")) {
            board.add("Pokemon");
            hand.remove("Pokemon");
            System.out.println(name + " played a Pokémon.");
        }
    }

    /**
     * Uses a Trainer card if available.
     */
    private void useTrainer() {
        if (hand.contains("Trainer")) {
            hand.remove("Trainer");
            discardPile.add("Trainer");
            System.out.println(name + " used a Trainer card.");
        }
    }

    /**
     * Attaches an Energy card to a Pokémon if available.
     */
    private void attachEnergy() {
        if (hand.contains("Energy")) {
            hand.remove("Energy");
            System.out.println(name + " attached an Energy card.");
        }
    }

    /**
     * Checks if the player has won (dummy condition: board has 3 Pokémon).
     */
    public boolean hasWon() {
        return board.size() >= 3;
    }
}