/*
 * Main Class represents a Black Jack Game
 */
package Game;

import Controller.Controller;
import Model.Model;
import View.View;

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
    }
}
