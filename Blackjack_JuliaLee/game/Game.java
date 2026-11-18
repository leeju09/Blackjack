package game;

import java.util.Scanner;

/**
 * Coordinates the flow of the Blackjack game.
 * Why: It is the ONLY place that owns the deck, the hands, and the win/loss rules.
 * It delegates work to other classes to avoid being monolithic.
 */
public class Game {
    private Scanner scanner;
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;

    public Game() {
        scanner = new Scanner(System.in);
        deck = new Deck();
        playerHand = new Hand("Player");
        dealerHand = new Hand("Dealer");
    }

    public void play() {
        System.out.println("=== Welcome to Blackjack ===");

        // Why here: The deck must be shuffled oves at the start of the game.
        deck.shuffle();


        dealInitialCards();
        printHands(true); // Hide dealer's second card

        // Check if the game ended immediately due to a Blackjack
        if (checkForInitialBlackjack()) {
            return;
        }

        playerTurn();

        // Why here: If the player busts, the dealer doesn't need to play.
        if (playerHand.isBust()) {
            System.out.println("\nPlayer busts! Dealer wins!");
            printHands(false);
            return;
        }

        dealerTurn();
        printHands(false); // Reveal dealer's hole card
        determineWinner();
    }

    private void dealInitialCards() {
        // Standard dealing order: Player, Dealer, Player, Dealer
        playerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
        playerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
    }

    private void printHands(boolean hideHolecard) {
        // Why boolean param: We reuse this method for both player's turn (hide) and the final showdown (reveal).
        System.out.println("\n--- Current Table ---");
        System.out.println(playerHand);

        if (hideHolecard) {
            Card visible = dealerHand.getCards().get(0);
            System.out.println("Dealer's hands: [" + visible + ", <hidden>]");
        } else {
            System.out.println(dealerHand);
        }
    }

    private boolean checkForInitialBlackjack() {
        boolean playerBJ = playerHand.isBlackjack();
        boolean dealerBJ = dealerHand.isBlackjack();
    

        // Why: Handles all three standard instant-win scenarios.
        if (playerBJ && dealerBJ) {
            System.out.println("\nBoth have Blackjack! Push.");
            printHands(false);
            return true;
        } else if (playerBJ) {
            System.out.println("\nBlackjack! Player wins!");
            printHands(false);
            return true;
        } else if (dealerBJ) {
            System.out.println("\nDealer has Blackjack! Dealer wins!");
            printHands(false);
            return true;
        }
        return false;
    }

    private void playerTurn() {
        boolean playing = true;
        while (playing) {
            System.out.print("\nHit or Stand? (h/s): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("h")) {
                Card drawn = deck.deal();
                playerHand.addCard(drawn);
                System.out.println("You drew: " + drawn);
                System.out.println(playerHand);

                // If they bust, break the loop and end their turn immediately.
                if (playerHand.isBust()) {
                    playing = false;
                }
            } else if (input.equals("s")) {
                System.out.println("Player stands.");
                playing = false;
            } else {
                System.out.println("Invalid input. Please enter 'h' or 's'.");
            }
        }
    }

    private void dealerTurn() {
        System.out.println("\nDealer reveals the hole card.");
        System.out.println(dealerHand);

        // Why while < 17: standard casino rule (Dealer hits on 16 or less).
        while (dealerHand.getTotal() < 17) {
            Card drawn = deck.deal();
            dealerHand.addCard(drawn);
            System.out.println("Dealer draws: " + drawn);
            System.out.println(dealerHand);
        }
    }

    private void determineWinner() {
        int playerTotal = playerHand.getTotal();
        int dealerTotal = dealerHand.getTotal();

        System.out.println("\n--- Final Result ---");


        // Why this order: Dealer busting is an instant player win, regardless of the player's total.
        if (dealerHand.isBust()) {
            System.out.println("Dealer busts! Player wins!");
        } else if (playerTotal > dealerTotal) {
            System.out.println("Player wins! " + playerTotal + " vs " + dealerTotal);
        } else if (playerTotal < dealerTotal) {
            System.out.println("Dealer wins! " + dealerTotal + " vs " + playerTotal);
        } else {
            System.out.println("Push! both have " + playerTotal);
        }
    }
}
