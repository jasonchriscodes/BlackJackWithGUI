/**
 * Class represents the kinds of payouts used in blackjack.
 */
package Model;

import java.util.function.DoubleUnaryOperator;

/**
 *
 * @author Jason Christian - 21136899
 */
public enum Payout {

    /**
     * A 3-to-2 payout, or 1.5 times the bet
     */
    BLACKJACK((bet) -> bet + (bet * 1.5)),
    /**
     * A payout equal to the bet
     */
    REGULAR((bet) -> bet * 2),
    /**
     * A payout half of the bet
     */
    HALF((bet) -> bet / 2);

    private final DoubleUnaryOperator op;

    Payout(DoubleUnaryOperator op) {
        this.op = op;
    }

    public double pay(double bet) {
        return op.applyAsDouble(bet);
    }
}
