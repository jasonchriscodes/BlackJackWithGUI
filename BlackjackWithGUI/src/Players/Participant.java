/*
 * Super class that represents all participants.
 */
package Players;

import Cards.Card;
import Game.Deck;
import Game.Hand;
import Game.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jason Christian - 21136899
 */
public abstract class Participant {

    private String name;
    private List<Hand> hands;
    private double totalGain;
    private final List<Card> hand;

    /**
     * Participant constructor.
     *
     * @param name of the card
     */
    public Participant(String name) {
        this.name = name;
        hands = new ArrayList<>();
        totalGain = 100;
        this.hand = new ArrayList<>();
    }

    /**
     * Participant constructor
     *
     * @param name
     * @param totalGain
     */
    public Participant(String name, double totalGain) {
        this.name = name;
        this.totalGain = totalGain;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Hand> getHands() {
        return hands;
    }

    public double getTotalGain() {
        return totalGain;
    }

    public void setTotalGain(double totalGain) {
        this.totalGain = totalGain;
    }

    public Hand createNewHand(int bet) {
        Hand newHand = new Hand(bet);
        hands.add(newHand);
        return newHand;
    }

    /**
     * Playing deck
     *
     * @param deck
     */
    public void play(Deck deck) {
        System.out.println("==================");
        System.out.println(name + " is playing");
        int bet = makeABet();
        Hand hand = createNewHand(bet);
        // each player initially have 2 cards
        hand.addCard(deck.draw());
        hand.addCard(deck.draw());
        System.out.println("the initial two cards are");
        hand.print();
        System.out.println(name + "'s score is: " + hand.getScore());
        // 'instanceof' is used to test if the object is an instance of HymanPlayer class
        if (hand.countAces() > 0 && this instanceof HumanPlayer) {
            System.out.println("Remember that an ACE can have rank either 1 or 11");
        }
        Action decision = decideAction();
        while (decision == Action.HIT) {
            hand.addCard(deck.draw());
            hand.print();
            System.out.println(name + "'s score is: " + hand.getScore());
            if (hand.is21() || hand.isBust()) {
                break;
            }
            decision = decideAction();
        }
        pressEnterKeyToContinue();
    }

    /**
     * Getter card in current hand
     *
     * @return
     */
    public Hand getCurrentHand() {
        if (hands.isEmpty()) {
            throw new RuntimeException("You should't call this method if there are no hands");
        }
        return hands.get(hands.size() - 1);
    }

    /**
     * Enter to continue
     */
    private void pressEnterKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        Utils.scanner.nextLine();
    }

    abstract int makeABet();

    abstract Action decideAction();

    public enum Action {

        HIT, HOLD
    }

    /**
     * Adds a card to this player's hand.
     *
     * <p>
     * A hit is when a player takes another card and adds it to their hand.
     *
     * @param card the card
     */
    public void hit(Card card) {
        hand.add(card);
    }

}
