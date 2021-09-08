/*
 * Class to scan user interface and create ASCII art for opening of the game
 */
package Game;

import java.util.Scanner;

/**
 *
 * @author Jason Christian - 21136899
 */
public class Utils {

    // scan user input
    public static Scanner scanner = new Scanner(System.in);

    // ASCII art for opening of the game
    public static void printBlackJack() {
        System.out.println("                          " + "          _     _            _    _            _    \n"
                + "                          " + "         | |   | |          | |  (_)          | |   \n"
                + "                          " + "         | |__ | | __ _  ___| | ___  __ _  ___| | __\n"
                + "                          " + "         | '_ \\| |/ _` |/ __| |/ / |/ _` |/ __| |/ /\n"
                + "                          " + "         | |_) | | (_| | (__|   <| | (_| | (__|   < \n"
                + "                          " + "         |_.__/|_|\\__,_|\\___|_|\\_\\ |\\__,_|\\___|_|\\_\\\n"
                + "                          " + "                                _/ |                \n"
                + "                          " + "                               |__/   			   \n" + "\n"
                + "                          " + ".------..------..------..------..------..------..------..------..------.\n"
                + "                          " + "| .--. || .--. ||J.--. ||A.--. ||S.--. ||O.--. ||N.--. || .--. || .--. |\n"
                + "                          " + "| :/\\: || :/\\: || :/\\: || :/\\: || :/\\: || :(): || :/\\: || :(): || :/\\: |\n"
                + "                          " + "| :\\/: || :\\/: || :\\/: || :\\/: || :\\/: || ()() || (__) || ()() || :\\/: |\n"
                + "                          " + "| '--'C|| '--'H|| '--'R|| '--'I|| '--'S|| '--'T|| '--'I|| '--'A|| '--'N|\n"
                + "                          " + "`------'`------'`------'`------'`------'`------'`------'`------'`------'\n" + "\n"
                + "                          " + "          ____  _ _ _____  __    ___  ___   ___  \n"
                + "                          " + "         |___ \\/ / |___ / / /_  ( _ )/ _ \\ / _ \\ \n"
                + "                          " + "           __) | | | |_ \\| '_ \\ /(_)| (_) | (_) | \n"
                + "                          " + "          / __/| | |___) | (_) | (_) \\__, |\\__, | \n"
                + "                          " + "         |_____|_|_|____/ \\___/ \\___/  /_/   /_/ \n"
                + "\n"
                + "--------------------------------------------------------Game Rules--------------------------------------------------- \n"
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
                + "    " + "\u250c\u2500\u2500\u2500\u2500\u2500\u2510" + " " + "\u250c\u2500\u2500\u2500\u2500\u2500\u2510 \n"
                + "    " + "\u2502" + "J" + "    " + "\u2502" + " " + "\u2502" + "8" + "    " + "\u2502 \n"
                + "    " + "\u2502" + "  " + '\u2660' + "  " + "\u2502" + " " + "\u2502" + "  " + '\u2665' + "  " + "\u2502" + " has a total score of 18. \n"
                + "    " + "\u2502" + "    " + "J" + "\u2502" + " " + "\u2502" + "    " + "8" + "\u2502 \n"
                + "    " + "\u2514\u2500\u2500\u2500\u2500\u2500\u2518" + " " + "\u2514\u2500\u2500\u2500\u2500\u2500\u2518 \n"
                + "6.  Then the player can 'hit' to ask for another card, or 'hold' to hold the total and end the turn. \n"
                + "7.  If the total goes over 21 the player is busted, and the dealer wins regardless of the dealer's hand (i.e., the dealer \n"
                + "    " + "wins even if it also exceeds 21). For example, if the player asked for another card ('hit'): \n"
                + "    " + "\u250c\u2500\u2500\u2500\u2500\u2500\u2510" + " " + "\u250c\u2500\u2500\u2500\u2500\u2500\u2510" + " " + "\u250c\u2500\u2500\u2500\u2500\u2500\u2510 \n"
                + "    " + "\u2502" + "J" + "    " + "\u2502" + " " + "\u2502" + "8" + "    " + "\u2502" + " " + "\u2502" + "6" + "    " + "\u2502 \n"
                + "    " + "\u2502" + "  " + '\u2660' + "  " + "\u2502" + " " + "\u2502" + "  " + '\u2665' + "  " + "\u2502" + " " + "\u2502" + "  " + '\u2666' + "  " + "\u2502" + " The total score is 24, the player is busted and loses the bet. \n"
                + "    " + "\u2502" + "    " + "J" + "\u2502" + " " + "\u2502" + "    " + "8" + "\u2502" + " " + "\u2502" + "    " + "6" + "\u2502 \n"
                + "    " + "\u2514\u2500\u2500\u2500\u2500\u2500\u2518" + " " + "\u2514\u2500\u2500\u2500\u2500\u2500\u2518" + " " + "\u2514\u2500\u2500\u2500\u2500\u2500\u2518 \n"
                + "                                     " + "\u250c\u2500\u2500\u2500\u2500\u2500\u2510" + " " + "\u250c\u2500\u2500\u2500\u2500\u2500\u2510 \n"
                + "                                     " + "\u2502" + "J" + "    " + "\u2502" + " " + "\u2502" + "A" + "    " + "\u2502 \n"
                + "If the player has 21 from the start: " + "\u2502" + "  " + '\u2660' + "  " + "\u2502" + " " + "\u2502" + "  " + '\u2665' + "  " + "\u2502" + " this a blackjack and the player wins 1.5 times the bet, regardless if \n"
                + "                                     " + "\u2502" + "    " + "J" + "\u2502" + " " + "\u2502" + "    " + "A" + "\u2502 \n"
                + "                                     " + "\u2514\u2500\u2500\u2500\u2500\u2500\u2518" + " " + "\u2514\u2500\u2500\u2500\u2500\u2500\u2518 \n"
                + "the dealer also does blackjack or not \n"
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
                + "--------------------------------------------------------End Rules--------------------------------------------------- \n"
        );
    }

}
