/*
 * Interface represents a card container
 */
package Cards;

import java.util.List;

/**
 *
 * @author Jason Christian - 21136899
 */
public interface CardContainer {

    Card drawCard();

    boolean isEmpty();

    void reset(List<Card> discarded);

    void shuffle();

    int size();
}
