/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

import Cards.Card;
import Game.Message;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jason Christian - 21136899
 */
public class Controller {

    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        initController();
        initView();
    }

    public void run() {
        view.displayFrame();
    }

    private void initController() {
        view.initBetOptions(Model.chips());
        view.initPlayOptions(Model.choices());
        view.initHandOptions(Model.options());

        view.initButtonActionListener("Play", new PlayAction());

        view.initButtonActionListener("Hit", new HitAction());
        view.initButtonActionListener("Stand", new holdAction());

        view.initButtonActionListener("Deal", new DealAction());
        view.initButtonActionListener("Hint", new HintAction());
        view.initButtonActionListener("Next Round", new NextRoundAction());
        view.initButtonActionListener("New Game", new NewGameAction());
        view.initButtonActionListener("Quit Game", new QuitGameAction());

        view.getBetOptions().forEach((betOption) -> {
            int value = Integer.parseInt(betOption.getText());
            betOption.addActionListener(new BetAction(value));
        });
    }

    private void initView() {
        view.displaySettings();
    }

    public class PlayAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            model.loadSettings(view.getSettings());
            view.displayTable();
            view.resetHandValue();
            view.clearCards();
            view.updateStats(model.playerChips(), model.playerBet());
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

    public class HitAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Card card = model.drawCard();
            model.playerHit(card);
            model.updateRunningCount(card.getRanks());
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
                view.disableButton("Hit"); //changes
                if (model.shoeIsEmpty()) {
                    view.displayMessage(Message.deckIsEmpty());
                }
            }
        }
    }

    public class holdAction implements ActionListener {

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
        }
    }

    public class DealAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.disableAllChips();
            view.enableAllChoices();
            view.enableButton("Hint");
            view.disableButton("Deal");

            for (int i = 0; i < 2; i++) {
                Card playerCard = model.drawCard();
                Card dealerCard = model.drawCard();
                model.playerHit(playerCard);
                model.updateRunningCount(playerCard.getRanks());
                model.dealerHit(dealerCard);
                if (i != 0) {
                    model.updateRunningCount(dealerCard.getRanks());
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
                        hint = BasicStrategy.Action.HL.toString();
                    }
                    break;
                case RH:
                    if (!view.isChoiceEnabled("Surrender")) {
                        hint = BasicStrategy.Action.H.toString();
                    }
                    break;
                case RS:
                    if (!view.isChoiceEnabled("Surrender")) {
                        hint = BasicStrategy.Action.HL.toString();
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
    s

}
