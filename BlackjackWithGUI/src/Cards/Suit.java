/**
 * Class represents a suit of a playing card.
 */
package Cards;

/**
 *
 * @author Jason Christian - 21136899
 */
public enum Suit {

    DIAMONDS("Diamonds"),
    CLUBS("Clubs"),
    SPADES("Spades"),
    HEARTS("Hearts");

    private final String suit;

    Suit(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return suit;
    }
}
