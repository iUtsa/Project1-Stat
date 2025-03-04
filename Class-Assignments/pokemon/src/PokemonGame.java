import java.util.Scanner;

/**
 * The PokemonGame class represents the main entry point for the Pokémon game.
 * It allows for a Player vs AI or AI vs AI game mode.
 */
public class PokemonGame {
    public static void main(String[] args) {
        playGame(true); // Set to false for AI vs AI, true for Player vs AI
    }

    /**
     * Plays the Pokémon game.
     * @param playerVsAI If true, the game is Player vs AI. If false, the game is AI vs AI.
     */
    private static void playGame(boolean playerVsAI) {
        Scanner scanner = new Scanner(System.in);
        Player player1 = new Player("Player 1");
        Player player2 = new Player("AI");

        // Build decks with Pokémon, Trainer, and Energy cards
        player1.buildDeck(20, 30, 10);
        player2.buildDeck(20, 30, 10);

        // Draw starting hands
        player1.drawStartingHand();
        player2.drawStartingHand();

        boolean gameOver = false;
        int turn = 1;

        // Main game loop
        while (!gameOver) {
            System.out.println("\n--- Turn " + turn + " ---");
            
            if (playerVsAI) {
                System.out.println("Your turn, " + player1.getName());
                player1.takeTurn(scanner); // Player's turn
            } else {
                player1.aiTakeTurn(); // AI's turn
            }

            // Check if player1 has won
            if (player1.hasWon()) {
                System.out.println(player1.getName() + " wins!");
                gameOver = true;
                break;
            }

            System.out.println("\nAI's turn");
            player2.aiTakeTurn(); // AI's turn

            // Check if player2 has won
            if (player2.hasWon()) {
                System.out.println(player2.getName() + " wins!");
                gameOver = true;
                break;
            }

            turn++;
        }
        scanner.close();
    }
}