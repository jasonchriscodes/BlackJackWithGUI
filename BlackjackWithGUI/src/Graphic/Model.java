/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

/**
 *
 * @author Jason Christian - 21136899
 */
public class Model {

    private static final int[] CHIPS = {100, 50, 25, 10, 5};

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

}
