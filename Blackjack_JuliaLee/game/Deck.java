package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a standard 52-card deck.
 * Why: It knows nothing about hands or game rules (SRP).
 * It only handles card creations, shuffling, and dealing.
 */
public class Deck {
    private final List<Card> cards;

    public Deck() {
        // Why ArrayList: Cards are dynamically removed as they are dealt/
        cards = new ArrayList<>();

        // Why nested loop: Guarantees exactly 52 unique cards.
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        // Why: Fisher-Yates: Unbiased
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
        

            // Swap the cards
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public Card deal() {
        // Why removes(size-1): Simulates dealing from the top of the deck.
        // It physically removes the card so it cannot be dealt twice.
        return cards.remove(cards.size() - 1);
    }
}
