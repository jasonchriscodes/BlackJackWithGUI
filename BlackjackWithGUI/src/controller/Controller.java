/**
 * Class represents a controller class in MVC pattern.
 */
package Controller;

import Cards.Card;
import File.RetrieveAll;
import Model.BasicStrategy;
import Model.Model;
import Model.Payout;
import View.Message;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jason Christian - 21136899
 */
public class Controller {

    private final Model model;
    private final View view;
    RetrieveAll retrieve = new RetrieveAll();

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        initController();
        initView();
        JOptionPane.showMessageDialog(null, retrieve.getMessageByTitle("'Rule'"), "Display Message", JOptionPane.INFORMATION_MESSAGE); // Remember 'NAME'
        JOptionPane.showMessageDialog(null, Message.rule(), "Display Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public void run() {
        view.displayFrame();
    }

    public class NewGameAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.displaySettings();
        }

    }

    public class LoadGameAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.displaySettings();
        }

    }

    public class ExitGameAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    public class PlayAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (model.nameisWrong(view.getSettings()) == true) {
                JOptionPane.showMessageDialog(null, "Enter the right name",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
            } else if ((model.nameisExist(view.getSettings()) == true)) {
                if ((view.promptStartGame(Message.startNewGame(), "New Game"))) {
                    model.loadSettings(view.getSettings());
                    view.displayTable();
                    view.resetHandValue();

                    view.clearCards();
                    view.updateStats(model.playerChips(), model.playerBet());
                    model.updatePlayer(view.getSettings(), model.playerChips());
                    view.updateTrueCount(model.getTrueCount());
                    view.updateDeckCount(model.deckCount());
                    view.displayMessage(Message.welcome());

                    view.enableAllChips();
                    view.updateChips(model.playerChips(), Model.chips());
                    view.disableAllChoices();

                    if (model.betIsSufficient()) {
                        view.enableButton("Deal");
                    } else {
                        view.disableButton("Deal");
                    }
                    view.disableButton("Next Hand", "Hint");
                } else {
                    model.loadDataTable(view.getSettings());
                    view.displayTable();
                    view.resetHandValue();

                    view.clearCards();
                    view.updateStats(model.playerChips(), model.playerBet());
                    model.updatePlayer(view.getSettings(), model.playerChips());
                    view.updateTrueCount(model.getTrueCount());
                    view.updateDeckCount(model.deckCount());
                    view.displayMessage(Message.welcome());

                    view.enableAllChips();
                    view.updateChips(model.playerChips(), Model.chips());
                    view.disableAllChoices();

                    if (model.betIsSufficient()) {
                        view.enableButton("Deal");
                    } else {
                        view.disableButton("Deal");
                    }
                    view.disableButton("Next Hand", "Hint");
                }
            } else if ((model.nameisExist(view.getSettings()) == false)) {
                model.loadSettings(view.getSettings());
                view.displayTable();
                view.resetHandValue();

                view.clearCards();
                view.updateStats(model.playerChips(), model.playerBet());
                model.updatePlayer(view.getSettings(), model.playerChips());
                view.updateTrueCount(model.getTrueCount());
                view.updateDeckCount(model.deckCount());
                view.displayMessage(Message.welcome());

                view.enableAllChips();
                view.updateChips(model.playerChips(), Model.chips());
                view.disableAllChoices();

                if (model.betIsSufficient()) {
                    view.enableButton("Deal");
                } else {
                    view.disableButton("Deal");
                }
                view.disableButton("Next Hand", "Hint");
            }
        }
    }

    public class BackAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            initView();
            model.restartGame();
        }

    }

    public class BetAction implements ActionListener {

        public BetAction(int value) {
            this.value = value;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            model.bet(value);

            if (model.betIsSufficient()) {
                view.clearMessage();
                view.enableButton("Deal");
            } else {
                view.displayMessage(Message.minimumBet(model.minimumBet()));
            }

            view.updateStats(model.playerChips(), model.playerBet());
            view.updateChips(model.playerChips(), Model.chips());
            model.updatePlayer(view.getSettings(), model.playerChips());
        }

        private final int value;
    }

    public class HitAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Card card = model.drawCard();
            model.playerHit(card);
            model.updateRunningCount(card.getRank());
            view.updateDeckCount(model.deckCount());
            view.updateTrueCount(model.getTrueCount());
            view.displayMessage(Message.hit(card + ""));
            view.updatePlayerHandValue(
                    model.playerName(),
                    model.playerHandValue(),
                    model.playerHasSoftHand()
            );
            view.updatePlayerCards(model.playerCardNames());
            view.disableButton("Surrender");
            if (model.wentOver() || model.shoeIsEmpty()) {
                view.disableButton("Hit", "Double Down", "Hint");
                if (model.shoeIsEmpty()) {
                    view.displayMessage(Message.deckIsEmpty());
                }
            }
        }
    }

    public class StandAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.disableAllChoices();
            view.disableButton("Hint");
            view.revealHoleCard(model.holeCard());

            model.dealerTurn();
            view.updateDeckCount(model.deckCount());
            view.updateTrueCount(model.getTrueCount());

            view.updatePlayerCards(model.playerCardNames());
            view.updateDealerCards(model.dealerCardNames());

            view.updatePlayerHandValue(model.playerName(),
                    model.playerHandValue(),
                    model.playerHasSoftHand());
            view.updateDealerHandValue(model.dealerHandValue(),
                    model.dealerHasSoftHand());

            String message;
            if (model.isTie()) {
                view.displayMessage(Message.tie());
                model.returnBet();
            } else if (model.playerWon()) {
                if (model.playerHasBlackjack()) {
                    message = Message.playerBlackjack(model.playerBet());
                    model.givePayout(Payout.BLACKJACK);
                } else {
                    message = Message.playerWon(model.playerBet());
                    model.givePayout(Payout.REGULAR);
                }
                view.displayMessage(message);
            } else if (model.playerLost()) {
                message = (model.dealerHasBlackjack())
                        ? Message.dealerBlackjack(model.playerBet())
                        : Message.playerLost(model.playerBet());
                view.displayMessage(message);
                model.resetBet();
            } else {
                view.displayMessage(Message.bothOver());
            }

            if (model.outOfChips()) {
                view.displayMessage(Message.outOfChips());
            } else {
                view.enableButton("Next Hand");
            }
            view.updateStats(model.playerChips(), model.playerBet());
            model.updatePlayer(view.getSettings(), model.playerChips());
        }
    }

    public class DoubleDownAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            model.doubleBet();
            Card card = model.drawCard();
            model.playerHit(card);
            model.updateRunningCount(card.getRank());
            view.updateTrueCount(model.getTrueCount());

            view.displayMessage(Message.doubleDown(
                    model.playerBet(), card + ""
            ));
            view.updatePlayerHandValue(
                    model.playerName(),
                    model.playerHandValue(),
                    model.playerHasSoftHand()
            );

            view.updatePlayerCards(model.playerCardNames());

            view.updateStats(model.playerChips(), model.playerBet());
            view.updateDeckCount(model.deckCount());
            view.disableButton("Hit", "Double Down", "Surrender", "Hint");
            model.updatePlayer(view.getSettings(), model.playerChips());
        }
    }

    public class SurrenderAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.displayMessage(Message.surrender(model.playerBet()));
            model.givePayout(Payout.HALF);
            model.resetBet();
            view.revealHoleCard(model.holeCard());
            model.updateRunningCount(model.holeCard().getRank());
            view.updateTrueCount(model.getTrueCount());
            view.updateDealerHandValue(model.dealerHandValue(),
                    model.dealerHasSoftHand());
            view.updateStats(model.playerChips(), model.playerBet());
            model.updatePlayer(view.getSettings(), model.playerChips());
            view.disableAllChoices();
            view.enableButton("Next Hand");
            view.disableButton("Hint");
            System.out.println();
        }
    }

    public class DealAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.disableAllChips();
            view.enableAllChoices();
            view.enableButton("Hint");
            view.disableButton("Deal");

            if (model.betIsEmpty() || !model.canDoubleDown()) {
                view.disableButton("Double Down");
            }

            for (int i = 0; i < 2; i++) {
                Card playerCard = model.drawCard();
                Card dealerCard = model.drawCard();
                model.playerHit(playerCard);
                model.updateRunningCount(playerCard.getRank());
                model.dealerHit(dealerCard);
                if (i != 0) {
                    model.updateRunningCount(dealerCard.getRank());
                }
            }
            view.updateTrueCount(model.getTrueCount());

            view.displayMessage(Message.deal(model.initialCards()));

            view.updatePlayerHandValue(
                    model.playerName(),
                    model.playerHandValue(),
                    model.playerHasSoftHand()
            );
            view.updateDealerHandValue(model.dealerFrontCard());

            view.updatePlayerCards(model.playerCardNames());
            view.updateDealerCards(model.dealerCardNames());
            view.hideHoleCard();
            view.updateDeckCount(model.deckCount());
        }
    }

    public class HintAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BasicStrategy.Action action = model.basicStrategy();
            String hint = null;
            switch (action) {
                case DH:
                    if (!view.isChoiceEnabled("Double Down")) {
                        hint = BasicStrategy.Action.H.toString();
                    }
                    break;
                case DS:
                    if (!view.isChoiceEnabled("Double Down")) {
                        hint = BasicStrategy.Action.S.toString();
                    }
                    break;
                case RH:
                    if (!view.isChoiceEnabled("Surrender")) {
                        hint = BasicStrategy.Action.H.toString();
                    }
                    break;
                case RS:
                    if (!view.isChoiceEnabled("Surrender")) {
                        hint = BasicStrategy.Action.S.toString();
                    }
                    break;
                default:
                    break;
            }
            if (hint == null) {
                hint = action.toString();
            }
            view.displayMessage("HINT", Message.hint(hint), "hint.png");
        }
    }

    public class NextHandAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.displayMessage(Message.nextHand());
            view.clearCards();

            if (!model.shoeIsSufficient()) {
                model.shuffleDeck();
                model.resetRunningCount();
                view.displayMessage(Message.reshuffle());
            }

            view.resetHandValue();
            view.disableButton("Next Hand", "Hint");
            model.resetHand();

            view.disableAllChoices();
            if (model.betIsSufficient()) {
                view.enableButton("Deal");
            } else {
                view.disableButton("Deal");
            }

            view.enableAllChips();
            view.updateChips(model.playerChips(), Model.chips());
            view.updateStats(model.playerChips(), model.playerBet());
            model.updatePlayer(view.getSettings(), model.playerChips());
            view.updateDeckCount(model.deckCount());
        }
    }

    public class MainMenuAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (model.outOfChips()
                    || view.prompt(Message.mainMenu(), "Main Menu")) {
                initView();
                model.restartGame();
            }
        }
    }

    public class QuitGameAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (model.outOfChips()
                    || view.prompt(Message.quit(), "Quit Game")) {
                System.exit(0);
            }
        }
    }

    private void initController() {
        view.initBetOptions(Model.chips());
        view.initWelcomeOptions(Model.welcome());
        view.initPlayOptions(Model.choices());
        view.initHandOptions(Model.options());

        view.initButtonActionListener("New Game", new NewGameAction());
        view.initButtonActionListener("Exit", new ExitGameAction());
        view.initButtonActionListener("Play", new PlayAction());
        view.initButtonActionListener("Back", new BackAction());

        view.initButtonActionListener("Hit", new HitAction());
        view.initButtonActionListener("Stand", new StandAction());
        view.initButtonActionListener("Double Down", new DoubleDownAction());
        view.initButtonActionListener("Surrender", new SurrenderAction());

        view.initButtonActionListener("Deal", new DealAction());
        view.initButtonActionListener("Hint", new HintAction());
        view.initButtonActionListener("Next Hand", new NextHandAction());
        view.initButtonActionListener("Main Menu", new MainMenuAction());
        view.initButtonActionListener("Quit Game", new QuitGameAction());

        view.getBetOptions().forEach((betOption) -> {
            int value = Integer.parseInt(betOption.getText());
            betOption.addActionListener(new BetAction(value));
        });
    }

    private void initView() {
        view.displayMenu();
    }
}
