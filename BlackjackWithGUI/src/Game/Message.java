/*
 * Class represents printing message
 */
package Game;

import Graphic.Format;

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
                        + "1. Start new game \n"
                        + "2. Load the game \n"
                        + "Please type either (1) or (2)";
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

    public String printRule(int number) {
        String messageString;
        switch (number) {
            case 1:
                messageString = "--------------------------------------------------------Game Rules--------------------------------------------------- \n"
                        + "\n"
                        + "0.  Choosing whether (1) delete all current saved game and start new or (2) load the game or start new game without deleting saved game. \n"
                        + "    " + "Then choosing the number of bots (opponents) to play with. \n"
                        + "Note: u can start new game without delete all current saved game by entering name that is not in the saved list. \n"
                        + "1.  The goal of blackjack is to beat the dealer and compete with all the bot players until you have gain the most chips. \n"
                        + "2.  Playing with 52 cards deck, which has 13 cards of each suit, namely clubs(" + '\u2663' + "), diamonds (" + '\u2666'
                        + "), hearts( \n"
                        + "    " + '\u2665' + ") and spades (" + '\u2660' + "). Each suit has 13 cards: Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King. \n"
                        + "3.  Jack, Queen, King are worth 10 (rank=10). Aces are worth 1 or 11, whichever makes a better hand. The ranks \n"
                        + "    " + "of the remaining cards are their corresponding numbers. \n"
                        + "4.  Before the hand starts, the player bets some chips between 1 and 100. \n"
                        + "5.  Each player hand starts with two cards. The ranks of these two cards are summed together. This is the current \n"
                        + "    has a total score of 18. \n"
                        + "6.  Then the player can 'hit' to ask for another card, or 'hold' to hold the total and end the turn. \n"
                        + "7.  If the total goes over 21 the player is busted, and the dealer wins regardless of the dealer's hand (i.e., the dealer \n"
                        + "    " + "wins even if it also exceeds 21). For example, if the player asked for another card ('hit'): \n"
                        + "    The total score is 24, the player is busted and loses the bet. \n"
                        + "    If the player has 21 from the start: this a blackjack and the player wins 1.5 times the bet, regardless if \n"
                        + "    the dealer also does blackjack or not \n"
                        + "9.  Then is the turn of the other players \n"
                        + "10. When all the players finish their turn, the dealer plays. When the dealer finishes their turn, we need to check which \n"
                        + "    " + "player wins against the dealer. \n"
                        + "11. winning player gets paid 1x the bet. if the player wins with a blackjack, they get back 1.5x the bet. For example, \n"
                        + "    " + "if the original bet was 100 chips a winning player gets back the original bet (100 chips), plust 100 chips from the \n"
                        + "    " + "dealer. if the player won with a blackjack, they get back the original bet (100 chips), plus 150 chips from the \n"
                        + "    " + "dealer. Intuitively, a player loses the original bet if the dealer wins. \n"
                        + "12. Then we can choose from the following option whether we want to (1) Continue the game or (2) Check current chips of all player  \n"
                        + "   " + "or (3) exit the game. "
                        + "13. Then system will showed whether we wins against bots or not. \n"
                        + "\n"
                        + "--------------------------------------------------------End Rules--------------------------------------------------- \n";
                break;
            default:
                messageString = "";
                break;
        }
        return messageString;
    }

    public static String welcome() {
        return "Welcome to Blackjack! Place a bet.";
    }

    public static String hit(String card) {
        return "You draw " + card + ".";
    }

    public static String deckIsEmpty() {
        return "The deck is empty. The Dealer will reshuffle in the next hand";
    }

    public static String tie() {
        return "You pushed for a tie. \nNote: A push is when the player and "
                + "the dealer have the same hand value so neither players win in a push.";

    }

    public static String playerBlackjack(double bet) {
        return "You got Blackjack! " + Message.playerWon(bet * 1.5);
    }

    public static String playerWon(double bet) {
        if (bet == 0) {
            return "You won!";
        }
        String chips = Format.currency(bet);
        return "You won " + chips + " Chips!";
    }

    public static String dealerBlackjack(double bet) {
        return "The Dealer got Blackjack. " + Message.playerLost(bet);
    }

    public static String playerLost(double bet) {
        if (bet == 0) {
            return "You lost.";
        }
        String chips = Format.currency(bet);
        return "You lost " + chips + " chips.";
    }

    public static String bothOver() {
        return "You both went over.";
    }

    public static String outOfChips() {
        return "You're out of chips.";
    }

    public static String deal(String firstCard, String secondCard) {
        return "Your first two cards are " + firstCard + " and " + secondCard + ".";
    }

    public static String deal(String[] initialCards) {
        if (initialCards.length < 2) {
            return "";
        }
        return deal(initialCards[0], initialCards[1]);
    }

    public static String hint(String action) {
        String[] templates = {
            "You should " + action.toLowerCase() + ".",
            "Your best option is to " + action.toLowerCase() + ".",
            action + " is your best option.",
            "The cards dictate that you should " + action.toLowerCase() + "."
        };
        return templates[(int) (Math.random() * templates.length)];
    }

    public static String nextHand() {
        return "Place a bet.";
    }

    public static String reshuffle() {
        return "Deck is reshuffled.";
    }

    public static String quit() {
        return "Are you sure you want to quit? "
                + "You will lose your current winnings.";
    }

    public static String minimumBet(int amount) {
        return "You need to bet a minimum of " + amount + " chips to play.";
    }
}
