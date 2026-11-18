package game;

/**
 * Represents a single playing card.
 * Why: Immutable. A dealt card must never be mutated mid-game, so fields are 'final' and there are no setters.
 * Why: Knows nothing about Deck, Hand, or rules (SRP).
 */

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    // Why: Delegates the value to the Rank enum, keeping card-value knowledge in ONE place.
    public int getValue() {
        return rank.getValue();
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    @Override 
    public String toString() {
        // Formates as "Two of HEARTS" for clean console output.
        return rank + " of " + suit;
    }
}