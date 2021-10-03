/*
 * Class that represents a bot dealer.
 */
package Players;

import java.util.List;

/**
 *
 * @author Jason Christian - 21136899
 */
public class BotDealer extends Participant {

    private List<Participant> players;

    /**
     * BotDealer constructor
     *
     * @param name
     * @param players
     */
    public BotDealer(String name, List<Participant> players) {
        super(name);
        this.players = players;
    }

    /**
     * Another botDealer constructor
     */
    public BotDealer() {
        super("Dealer");
    }

    /**
     * Determines if this dealer has a soft 17.
     *
     * <p>
     * A hand that contains an ace counted as 11 is a soft hand. If the hand is
     * soft and has a value of 17, it is a soft 17.
     *
     * @return true if this dealer has a soft 17
     */
    public boolean hasSoft17() {
        return getHandValue() == 17 && hasSoftHand();
    }

    /**
     * Dealer score
     *
     * @return
     */
    private int dealerScore() {
        return super.getCurrentHand().getScore();
    }

    @Override
    /**
     * do not touch this method
     */
    public int makeABet() {
        // the Dealer doesn't bet so is always zero
        return 0;
    }

    @Override
    public Action decideAction() {
        // TODO
        int i = 0, noOfWins = 0;

        for (i = 0; i < players.size(); i++) {
            if (dealerScore() < players.get(i).getCurrentHand().getScore() && (players.get(i).getCurrentHand()
                    .getScore() <= 21) && dealerScore() <= 21) {
                noOfWins++;
            } else if (players.get(i).getCurrentHand().isBlackJack()) {
                noOfWins++;
            } else if ((players.get(i).getCurrentHand().getScore() <= 21) && (dealerScore() > 21)) {
                noOfWins++;
            }
        }
        if (noOfWins >= 2) {
            return Action.HIT;
        } else {
            return Action.HOLD;
        }
//		return new Random().nextBoolean() ? Action.HIT : Action.HOLD; // FIXME
    }
}
