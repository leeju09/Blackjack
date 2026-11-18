package game;

/**
 * Program entry point.
 * Why it's so short: The main method must not store state, contain logic, or handles user interaction.
 */
public class TestBlackjack {
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}