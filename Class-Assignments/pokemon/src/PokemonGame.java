package src;
import java.util.Scanner;

import Player;

public class PokemonGame {
    public static void main(String[] args) {
        playGame(true); // Set to false for AI vs AI, true for Player vs AI
    }

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

        while (!gameOver) {
            System.out.println("\n--- Turn " + turn + " ---");
            
            if (playerVsAI) {
                System.out.println("Your turn, " + player1.getName());
                player1.takeTurn(scanner);
            } else {
                player1.aiTakeTurn();
            }

            if (player1.hasWon()) {
                System.out.println(player1.getName() + " wins!");
                gameOver = true;
                break;
            }

            System.out.println("\nAI's turn");
            player2.aiTakeTurn();

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
