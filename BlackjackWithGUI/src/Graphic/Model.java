/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

import Cards.Card;
import Cards.CardContainer;
import Cards.Shoe;
import Players.HumanPlayer;

/**
 *
 * @author Jason Christian - 21136899
 */
public class Model {

    private HumanPlayer player;
    private int minimumBet;
    private CardContainer shoe;
    private int runningCount;
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
}
