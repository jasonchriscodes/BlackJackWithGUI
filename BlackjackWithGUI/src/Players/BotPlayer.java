/*
 * Class that represents a bot player.
 */
package Players;

/**
 *
 * @author Jason Christian - 21136899
 */
public class BotPlayer extends Participant {

    /**
     * BotPlayer constructor
     *
     * @param name
     */
    public BotPlayer(String name) {
        super(name);
    }

    @Override
    public int makeABet() {
        // TODO
        // Create random number 0 - 99
        double randNumber = Math.random();
        double d = randNumber * 100;

        // Type cast double to int & Shifting range from 0 - 99 to 1 - 100 
        int randomInt = (int) d + 1;

        return randomInt; // FIXME
    }

    @Override
    public Action decideAction() {
        // TODO
        if (getCurrentHand().getScore() < 17) {
            return Action.HIT;
        } else {
            return Action.HOLD;
        }
//		return new Random().nextBoolean() ? Action.HIT : Action.HOLD; // FIXME
    }
}
