/*
 * Main Class represents a Black Jack Game
 */
package Game;

/**
 *
 * @author Jason Christian - 21136899
 */
public class Blackjack {

    /**
     * Main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Utils.printBlackJack();
        GameRunner game = new GameRunner();
        game.start();
    }
}
