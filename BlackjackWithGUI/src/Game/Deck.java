/*
 * Class represents a shuffled Deck of playing Cards.
 */
package Game;

import Cards.Card;
import Cards.Card.Suit;
import Cards.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Jason Christian - 21136899
 */
public class Deck {

    private final List<Card> deck;
    private int position;

    /**
     * Deck constructor
     *
     */
    public Deck() {
        deck = new ArrayList<>(52);
        create();
        shuffle();
        position = 0; // set to the top of deck
    }

    /**
     * Shuffle deck using Collections class
     *
     */
    private void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Add new deck
     */
    private void create() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    /**
     * Draw cards
     *
     * @return
     */
    public Card draw() {
        if (deck.size() == position) {
            // The is empty card, create a new deck
            newDeck();
        }
        return deck.get(position++);
    }

    /**
     * Create new deck
     */
    public void newDeck() {
        position = 0;
        shuffle();
    }

    public List<Card> getDeck() {
        return deck;
    }

}
