/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

import Cards.Card;
import Cards.CardContainer;
import Cards.Shoe;
import Game.Hand;
import Players.BotDealer;
import Players.HumanPlayer;
import Players.Participant;

/**
 *
 * @author Jason Christian - 21136899
 */
public class Model {

    private HumanPlayer player;
    private int minimumBet;
    private CardContainer shoe;
    private int runningCount;
    private BotDealer dealer;
    private boolean stand17;
    private static final int[] CHIPS = {100, 50, 25, 10, 5};
    private static final String[] CHOICES = {
        "Hit", "Hold"
    };
    private static final String[] OPTIONS = {
        "Deal", "Next Round", "New Game", "Quit Game"
    };

    /**
     * Returns the possible types of chips a player may bet.
     *
     * <p>
     * Every int element in the private array {@code CHIPS} should be displayed
     * on the screen. A note about the order of the elements in the array: the
     * last element must be the smallest value. This is to keep the rightmost
     * insets intact as a chip option get disabled.
     *
     * @return the chips
     */
    public static final int[] chips() {
        return CHIPS.clone();
    }

    /**
     * Returns the possible choices for the player when they are playing.
     *
     * @return the choices
     */
    public static final String[] choices() {
        return CHOICES.clone();
    }

    /**
     * Returns the possible options for the player regarding the game.
     *
     * @return the options
     */
    public static final String[] options() {
        return OPTIONS.clone();
    }

    public void loadSettings(Object[] settings) {
        String name = (String) settings[0];
        if (name.isEmpty()) {
            player = new HumanPlayer();
        } else {
            player = new HumanPlayer(name, 100);
        }
        minimumBet = (int) settings[2];
    }

    /**
     * Returns the true count of cards played in the shoe.
     *
     * <p>
     * The true count is calculated by dividing the running count by the number
     * of decks remaining.
     *
     * @return the true count
     */
    public int getTrueCount() {
        if (shoe.getClass() == Shoe.class) {
            Shoe s = (Shoe) this.shoe;
            if (s.deckCount() > 0) {
                return Math.round(runningCount / s.deckCount());
            }
        }
        return runningCount;
    }

    public double playerChips() {
        return player.getBankroll();
    }

    public double playerBet() {
        return player.getTotalGain();
    }

    /**
     * Returns the number of decks in the shoe.
     *
     * @return the number of decks
     */
    public int deckCount() {
        if (shoe.getClass() == Shoe.class) {
            Shoe s = (Shoe) shoe;
            return s.deckCount();
        }
        return 1;
    }

    /**
     * Checks if the player's current bet has reached the minimum bet.
     *
     * @return true if player's bet has reached the minimum bet
     */
    public boolean betIsSufficient() {
        return player.getTotalGain() >= minimumBet;
    }

    /**
     * This method draws a card from the shoe.
     *
     * @return a card
     */
    public Card drawCard() {
        return shoe.drawCard();
    }

    /**
     * Adds a card to the player's hand.
     *
     * @param card the card to be added
     */
    public void playerHit(Card card) {
        player.hit(card);
    }

    /**
     * Add a card to the running count.
     *
     * @param rank the card's value
     */
    public void updateRunningCount(int rank) {
        if (rank >= 2 && rank <= 6) {
            runningCount++;
        } else if (rank == 1 || rank == 10) {
            runningCount--;
        }
    }

    public String playerName() {
        return player + "";
    }

    public int playerHandValue() {
        return player.getHandValue();
    }

    /**
     * Determines if the player has a soft hand.
     *
     * @return true if the player has a soft hand
     */
    public boolean playerHasSoftHand() {
        return player.hasSoftHand();
    }

    public String[] playerCardNames() {
        int handSize = player.getHand().size();
        String[] cardNames = new String[handSize];
        for (int i = 0; i < handSize; i++) {
            cardNames[i] = player.getHand().get(i).toString();
        }
        return cardNames;
    }

    /**
     * This method determines if the player's hand has busted.
     *
     * @return true if player's hand has busted
     */
    public boolean wentOver() {
        return !player.isBelowLimit();
    }

    /**
     * Determines if there are any remaining cards in the shoe.
     *
     * @return true if there are any remaining cards in the shoe
     */
    public boolean shoeIsEmpty() {
        return shoe.isEmpty();
    }

    public Card holeCard() {
        return dealer.getHand().get(0);
    }

    /**
     * Performs a dealer's turn in blackjack.
     *
     * <p>
     * In blackjack, the dealer hits until their hand value is greater than 16.
     * The dealer may also hit a soft 17 in some games, depending on the casino
     * rules.
     */
    public void dealerTurn() {
        while (dealer.hasSoft17() || dealer.getHandValue() <= 16) {
            if ((dealer.hasSoft17() && stand17) || shoe.isEmpty()) {
                return;
            }
            Card card = shoe.drawCard();
            dealer.hit(card);
            updateRunningCount(card.getRanks());
        }
    }

    public String[] dealerCardNames() {
        int handSize = dealer.getHand().size();
        String[] cardNames = new String[handSize];
        for (int i = 0; i < handSize; i++) {
            cardNames[i] = dealer.getHand().get(i).toString();
        }
        return cardNames;
    }

}
