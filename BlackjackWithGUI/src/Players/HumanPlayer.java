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

    /**
     * HumanPlayer constructor
     *
     * @param name
     * @param gain
     */
    public HumanPlayer(String name, double gain) {
        super(name);
        setTotalGain(gain);
    }

    public HumanPlayer(String name) {
        super(name);
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
}
