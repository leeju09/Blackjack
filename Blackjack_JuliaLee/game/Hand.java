package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a hand of cards for a player and dealer.
 * Why: It calculates its own toal, but does NOT compare itself to other hands.
 * That logic belongs to the Game class (SRP).
 */
public class Hand {
    private final String ownerName;
    private final List<Card> cards;

    public Hand(String ownerName) {
        this.ownerName = ownerName;
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getTotal() {
        int total = 0;
        int aces = 0;

        // First pass: Sum all values, counting how many Aces we have.
        for (Card card : cards) {
            total += card.getValue();
            if (card.isAce()) {
                aces++;
            }
        }

        // Why this loop: Aces start at 11 (from the Rank enum).
        // If we bust (>21), we convert an Ace to 1 by subtracting 10.
        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        return total;
    }

    public boolean isBust() {
        return getTotal() > 21;
    }

    public boolean isBlackjack() {
        // Why size == 2: Blackjack must be exactly 2 cards totaling 21.
        return cards.size() == 2 && getTotal() == 21;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override 
    public String toString() {
        return ownerName + "'s hands: " + cards + " (total: " + getTotal() + ")";
    }
}