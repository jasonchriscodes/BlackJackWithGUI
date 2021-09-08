/*
 * Class to represent a playing Card
 */
package Cards;

/**
 *
 * @author Jason Christian - 21136899
 */
public class Card {

    /**
     * Suit value of Card.
     */
    private Suit suit;

    /**
     * Rank value of Card.
     */
    private Rank rank;

    /**
     * Type of suits
     */
    public enum Suit {
        HEARTS, DIAMONDS, SPADES, CLUBS
    }

    /**
     * Card constructor.
     *
     * @param face face of the card
     * @param suit suit of the card
     */
    public Card(Rank face, Suit suit) {
        this.rank = face;
        this.suit = suit;
    }

    /**
     * Return the rank of the card
     *
     * @return the rank
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Return the suit of the card
     *
     * @return the suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * card template for printing (Outline border and '?' as suit
     */
    public static final char[][] template = {
        "\u250c\u2500\u2500\u2500\u2500\u2500\u2510".toCharArray(),
        "\u2502?    \u2502".toCharArray(),
        "\u2502  *  \u2502".toCharArray(),
        "\u2502    ?\u2502".toCharArray(),
        "\u2514\u2500\u2500\u2500\u2500\u2500\u2518".toCharArray()
    };

    /**
     * Using the template before, print the card suit and rank Note: 10 is
     * special case and need 2 digits and also we need to space after printing
     * 10
     *
     * @return
     */
    public char[][] getCardToPrint() {
        // reuse template, do not create additional objects
        // the 10 card is a special case because 10 has 2 digits
        if (rank == Rank.TEN) {
            // fill the template
            template[1][1] = convertIntToChar(1);
            template[1][2] = convertIntToChar(0);
            template[3][4] = convertIntToChar(1);
            template[3][5] = convertIntToChar(0);
        } else {
            // lets clean the template in case we just printed a number 10
            template[1][2] = ' ';
            template[3][4] = ' ';
            // fill the template
            template[1][1] = this.getPrintableRank();
            template[3][5] = this.getPrintableRank();
        }
        template[2][3] = this.getPrintableSuit();
        return template;
    }

    /**
     * Convert int number to char because '0' is 48 in ASCII code adding number
     * + '0' is the same to convert int number to char
     *
     * @param number
     * @return
     */
    private char convertIntToChar(int number) {
        // adding plus 0 is a trick to get the char value out of an integer
        return (char) (number + '0');
    }

    /**
     * Print rank of the cards, 2 - 10, ACE, JACK, QUEEN, KING
     *
     * @return
     */
    private char getPrintableRank() {
        switch (rank) {
            case ACE:
                return 'A';
            case JACK:
                return 'J';
            case QUEEN:
                return 'Q';
            case KING:
                return 'K';
            default:
                return convertIntToChar(rank.value);
        }
    }

    /**
     * Print symbol of the suit for each suit Note: need to add RuntimeException
     * because there are only 4 symbols
     *
     * @return
     */
    private char getPrintableSuit() {
        switch (suit) {
            case HEARTS:
                return '\u2665'; //'\u2665' is unicode for hearts
            case DIAMONDS:
                return '\u2666';
            case SPADES:
                return '\u2660';
            case CLUBS:
                return '\u2663';
            default:
                // execute here should be impossible since SUIT has only those 4 possible values
                throw new RuntimeException("something is incredibly wrong");
        }
    }
}
