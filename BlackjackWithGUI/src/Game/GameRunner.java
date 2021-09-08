/*
 * Main class to run Blackjack game
 */
package Game;

import File.FileManagement;
import Players.BotDealer;
import Players.BotPlayer;
import Players.HumanPlayer;
import Players.Participant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jason Christian - 21136899
 */
public class GameRunner {

    private List<Participant> players;
    private Participant dealer;
    FileManagement file;
    int number;
    Message message = new Message(number);

    /**
     * Initialize human player and bot dealer
     *
     */
    public GameRunner() {
        try {
            this.file = new FileManagement();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        players = new ArrayList<>();
        message.printingMessage(1);
        file.savedPlayer();
        message.printingMessage(2);
        String loadAnswer = Utils.scanner.next();
        while (!loadAnswer.equals("1") && !loadAnswer.equals("2")) {
            message.printingMessage(3);
            loadAnswer = Utils.scanner.next();
        }
        message.printingMessage(4);
        String name = Utils.scanner.next();
        while (!name.matches("[a-zA-Z]+")) {
            System.err.println("Please enter a valid name!");
            name = Utils.scanner.next();
        }
        if ("1".equals(loadAnswer)) {
            file.createNewFile();
            players.add(new HumanPlayer(name, 100));
        } else {
            players.add(new HumanPlayer(name, file.checkUser(name).getTotalGain()));
        }
        dealer = new BotDealer("Dealer", players);
    }

    /**
     * Method to add player bot
     *
     * @param a
     */
    private void addPlayerBot(int a) {
        for (int i = 1; i <= a; i++) {
            players.add(new BotPlayer("Bot" + i));
        }
    }

    /**
     * Method to exit game
     *
     */
    public void quit() {
        message.printingMessage(5);
        System.exit(0);
    }

    /**
     * Start the game
     *
     */
    protected void start() {

        boolean isValid = false;
        do {
            message.printingMessage(6);
            String botPlayer = Utils.scanner.next();
            if (botPlayer.trim().equalsIgnoreCase("x")) {
                quit();
            }
            try {
                int noOfBot = Integer.parseInt(botPlayer);
                addPlayerBot(noOfBot);
                isValid = true;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please input integer.");
            }
        } while (!isValid);
        // create a new deck of cards
        Deck deck = new Deck();
        String result;
        result = "0";
        do {
            if (result.equals("0") || result.equals("1")) {
                for (Participant player : players) {
                    player.play(deck);
                }
                dealer.play(deck);
                checkWinner();
            } else if (result.equals("2")) {
                printPlayerTotalGain();
            }
            message.printingMessage(7);
            result = Utils.scanner.next();
            while (!result.equals("1") && !result.equals("2") && !result.equals("3")) {
                System.out.println("please type either \"1\" or \"2\" or \"3\"");
                result = Utils.scanner.next();
            }
        } while (result.equals("1") || result.equals("2"));
        printPlayerHighestGain();
        try {
            file.updateScore(new HumanPlayer(getPlayers().get(0).getName(), (getPlayers().get(0).getTotalGain())));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        quit();
    }

    /**
     * Checking winner of all players
     */
    public void checkWinner() {
        int dealerScore = dealer.getCurrentHand().getScore();

        for (Participant player : players) {
            int playerScore = player.getCurrentHand().getScore();

            if ((playerScore > dealerScore) && (playerScore <= 21) || ((playerScore <= 21) && (dealerScore > 21))) {
                System.out.println(player.getName() + " wins against dealer.");
            }
        }
    }

    /**
     * Calculate player total gains
     */
    public void playerTotalGain() {
        Participant playerWithHighestGain = players.get(0);
        int numberOfHands = players.get(0).getHands().size();

        // This for loop iterates through every hand for each player and compares it to the dealer's hand 
        // in the same round to determine each player's total gain.
        for (Participant player : players) {
            for (int hand = 0; hand < numberOfHands; hand++) {

                int dealerScore = dealer.getHands().get(hand).getScore();
                int playerScore = player.getHands().get(hand).getScore();

                // The winMultiplier variable is the amount that the player's bet should be 
                // multiplied by to calculate the winnings for each hand.
                //
                //           Lost the hand: -1
                //                    Push:  0
                //            Won the hand:  1
                // Won 21 without blackjack: 1
                //     Won with blackjack:  1.5
                double winMultiplier = -1;

                if ((playerScore == dealerScore) && (playerScore <= 21)) {
                    winMultiplier = 0;
                } else if ((playerScore > dealerScore) && (playerScore <= 21) || ((playerScore <= 21) && (dealerScore > 21))) {
                    if (player.getHands().get(hand).isBlackJack()) {
                        winMultiplier = 1.5;
                    } else {
                        winMultiplier = 1;
                    }
                }
                // Add the hand's winnings to the total gain for the player
                player.setTotalGain(player.getTotalGain() + (winMultiplier * (double) player.getHands().get(hand).getBet()));
            }
        }
    }

    /**
     * Printing player total gain
     */
    public void printPlayerTotalGain() {
        playerTotalGain();
        // Determine the player with the highest total gain
        for (Participant player : players) {
            System.out.println(player.getName() + " now have " + player.getTotalGain() + " chips");
        }
    }

    /**
     * Printing player highest gain
     */
    public void printPlayerHighestGain() {
        Participant playerWithHighestGain = players.get(0);
        int numberOfHands = players.get(0).getHands().size();

        // Determine the player with the highest total gain
        for (Participant player : players) {
            if (player.getTotalGain() > playerWithHighestGain.getTotalGain()) {
                playerWithHighestGain = player;
            }
        }
        System.out.println("The player with the highest chips is: " + playerWithHighestGain.getName()
                + " with " + playerWithHighestGain.getTotalGain() + " chips");
        if (playerWithHighestGain != players.get(0)) {
            System.out.println(players.get(0).getName() + " you lose to bot :(");
        } else {
            System.out.println("CONGRATULATION! YOU WIN AGAINST BOT!");
        }
    }

    public List<Participant> getPlayers() {
        return players;
    }

    public Participant getDealer() {
        return dealer;
    }

    public void setPlayers(List<Participant> players) {
        this.players = players;
    }
}
