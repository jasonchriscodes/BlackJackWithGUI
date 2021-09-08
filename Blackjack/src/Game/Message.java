/*
 * Class represents printing message
 */
package Game;

/**
 *
 * @author Jason Christian - 21136899
 */
public class Message {

    public Message(int number) {
        printingMessage(number);
    }

    /**
     * Printing game message
     *
     * @param number
     */
    public void printingMessage(int number) {
        String messageString;
        switch (number) {
            case 1:
                messageString = "Current saved player: ";
                break;
            case 2:
                messageString = "Select from the following options: \n"
                        + "1. Delete all current saved game and start new \n"
                        + "2. Load the game or start new game without deleting saved game \n"
                        + "Note: u can start new game without delete all current saved game by entering name that is not in the saved list. \n"
                        + "please type either (1) or (2)";
                break;
            case 3:
                messageString = "Please type either (1) or (2)";
                break;
            case 4:
                messageString = "Please type letter only. (No Number or Symbol) \nEnter your name: ";
                break;
            case 5:
                messageString = "Thank you for playing. See you later! \n";
                break;
            case 6:
                messageString = "Press 'x' to exit. How many bot player you want to add?";
                break;
            case 7:
                messageString = "Select from the following options? \n"
                        + "(1) Continue the game. \n"
                        + "(2) Check current chips of all player. \n"
                        + "(3) Exit game. \n";
                break;
            default:
                messageString = "";
                break;
        }
        System.out.println(messageString);
    }
}
