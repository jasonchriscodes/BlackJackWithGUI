/*
 * Main Class represents a Black Jack Game
 */
package Game;

import Graphic.Controller;
import Graphic.Model;
import Graphic.View;

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
        //GUI
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.run();
        //CUI
//        Utils.printBlackJack();
//        GameRunner game = new GameRunner();
//        game.start();
    }
}
