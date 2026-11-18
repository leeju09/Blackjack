package game;

/**
 * Why enum: Guarantees only the 4 valid suits can exist.
 * Why parameterized: Allows us to store a clean String display name.
 */
public enum Suit {
    HEARTS("Hearts"), DIAMONDS("Diamonds"), CLUBS("Clubs"), SPADES("Spades");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}