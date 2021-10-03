/*
 * Class that represents a human dealer.
 */
package Players;

import Game.Utils;
import java.util.InputMismatchException;

/**
 *
 * @author Jason Christian - 21136899
 */
public class HumanPlayer extends Participant {

    private double bankroll;
    private double bet;

    /**
     * HumanPlayer constructor
     *
     * @param name
     * @param bankroll
     */
    public HumanPlayer(String name, double bankroll) {
        super(name);
        setTotalGain(bankroll);
        this.bankroll = bankroll;
        this.bet = 0;
    }

    public HumanPlayer(String name) {
        super(name, 100);
    }

    public HumanPlayer() {
        this("Player", 100);
    }

    /**
     * Creates a new {@code BlackjackPlayer} named Player with the specified
     * amount of chips.
     *
     * @param bankroll the starting amount of chips
     */
    public HumanPlayer(double bankroll) {
        this("Player", bankroll);
    }

    @Override
    int makeABet() {
        System.out.println("How much do you want to bet?");
        int bet = 0;
        do {
            try {
                System.out.println("Please enter a number > 0 and <= 100");
                bet = Integer.valueOf(Utils.scanner.next());
            } catch (InputMismatchException | NumberFormatException nf) {
            }
        } while (bet <= 0 || bet > 100);
        return bet;
    }

    @Override
    Action decideAction() {

        System.out.println("Decide your action (hit or hold)");
        String res = Utils.scanner.next();
        while (!res.equals("hit") && !res.equals("hold")) {
            System.out.println("please type either \"hit\" or \"hold\"");
            res = Utils.scanner.next();
        }
        return res.equals("hit") ? Action.HIT : Action.HOLD;
    }

    /**
     * Adds to the existing bet of this player.
     *
     * @param amount the desired bet
     */
    public void addBet(double amount) {
        if (amount <= bankroll) {
            bankroll -= amount;
        } else {
            amount = bankroll;
            bankroll = 0;
        }
        bet += amount;
    }

    /**
     * Adds to this player's bankroll.
     *
     * @param amount the desired amount
     */
    public void addChips(double amount) {
        bankroll += amount;
    }

    /**
     * Doubles this player's current bet.
     */
    public void doubleBet() {
        if (bet <= bankroll) {
            addBet(bet);
        }
    }

    /**
     * Bets the specified amount of chips.
     *
     * @param amount the desired amount of chips
     */
    public void setBet(double amount) {
        if (amount <= bankroll) {
            bankroll -= amount;
        } else {
            amount = bankroll;
            bankroll = 0;
        }
        bet = amount;
    }

    /**
     * Returns this player's bankroll
     *
     * @return this player's bankroll
     */
    public double getBankroll() {
        return bankroll;
    }

    /**
     * Returns this player's bet
     *
     * @return this player's bet
     */
    public double getBet() {
        return bet;
    }
}
