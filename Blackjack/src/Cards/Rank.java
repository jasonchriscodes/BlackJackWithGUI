/*
 * An enum representing the thirteen possible ranks of card.
 */
package Cards;

/**
 *
 * @author Jason Christian - 21136899
 */
public enum Rank {
    ACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);

    public final int value;

    Rank(int value) {
        this.value = value;
    }
}
