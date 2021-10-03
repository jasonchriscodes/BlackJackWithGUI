/*
 * Class that represents a single hand of cards for a player.
 */
package Game;

import Cards.Card;
import Cards.Rank;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jason Christian - 21136899
 */
public class Hand {

    private List<Card> cards;
    private int bet;
    private int score;

    /**
     * Hand constructor
     *
     * @param bet
     */
    public Hand(int bet) {
        cards = new ArrayList<>();
        this.bet = bet;
        // total score of card 1 + jack = 13
        score = 0;
    }

    /**
     * getter for bet
     *
     * @return
     */
    public int getBet() {
        return bet;
    }

    /**
     * getter for score
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * getter for cards
     *
     * @return
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Adding card to hand
     *
     * @param c
     */
    public void addCard(Card c) {
        cards.add(c);
        // every time a card is added, update the score
        score = score + c.getRank().value;
        checkIfAcesShouldBeOne();
    }

    /**
     * Checking the number of Aces
     */
    private void checkIfAcesShouldBeOne() {
        if (score > 21) {
            int numAces = countAces();
            // see if there are opportunities to make ACES to have score 1
            if (numAces > 0) {
                score = 0;
                // recount from scratch because some ACEs might be counted as 11
                for (Card card : cards) {
                    score = score + card.getRank().value;
                }
                while (score > 21 && numAces > 0) {
                    // ACES can have score 11 or 1
                    score = score - 10;
                    numAces--;
                }
            }
        }
        // note that the score wll be update after the method ends
    }

    /**
     * Counting Aces
     *
     * @return
     */
    public int countAces() {
        int count = 0;
        for (Card c : cards) {
            if (c.getRank() == Rank.ACE) {
                count++;
            }
        }
        return count;
    }

    /**
     * Check if the player has a total of 21 score in their hand
     *
     * @return
     */
    public boolean isBlackJack() {
        return score == 21 && cards.size() == 2;
    }

    /**
     * Checking if player got 21 or blackjack
     *
     * @return
     */
    public boolean is21() {
        if (isBlackJack()) {
            System.out.println("Black Jack! :D ");
            return true;
        } else if (score == 21) {
            System.out.println("21! :) ");
            return true;
        }
        return false;
    }

    /**
     * Checking if player has score more than 21
     *
     * @return
     */
    public boolean isBust() {
        if (getScore() > 21) {
            System.out.println("Bust! :( ");
            return true;
        }
        return false;
    }

    /**
     * Printing card
     */
    public void print() {
        List<StringBuilder> listSb = new ArrayList<>();
        // + 1 because we also want to print the name of the card
        for (int i = 0; i < Card.template[0].length + 1; i++) {
            listSb.add(new StringBuilder());
        }
        for (Card card : cards) {
            String textCard = card.getRank() + " " + card.getSuit();
            int numWhiteSpaces = (((textCard.length() - 7)) / 2) + 1;
            listSb.get(0).append(textCard);
            listSb.get(0).append("  -  ");
            int i = 1;
            for (char[] line : card.getCardToPrint()) {
                addNumberWhiteSpace(listSb.get(i), numWhiteSpaces);
                listSb.get(i).append(line);
                addNumberWhiteSpace(listSb.get(i), "  -  ".length() + numWhiteSpaces / 2);
                i++;
            }
        }
        for (int i = 0; i < Card.template[0].length + 1; i++) {
            System.out.println(listSb.get(i).toString());
        }
    }

    /**
     * Adding space
     *
     * @param sb
     * @param num
     */
    private void addNumberWhiteSpace(StringBuilder sb, int num) {
        for (int i = 0; i < num; i++) {
            sb.append(" ");
        }
    }

}
