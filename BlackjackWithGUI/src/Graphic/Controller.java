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
        view.initButtonActionListener("Stand", new StandAction());
        view.initButtonActionListener("Double Down", new DoubleDownAction());
        view.initButtonActionListener("Surrender", new SurrenderAction());

        view.initButtonActionListener("Deal", new DealAction());
        view.initButtonActionListener("Hint", new HintAction());
        view.initButtonActionListener("Next Hand", new NextHandAction());
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
                view.disableButton("Hit"); //changes
                if (model.shoeIsEmpty()) {
                    view.displayMessage(Message.deckIsEmpty());
                }
            }
        }
    }
    s

}
