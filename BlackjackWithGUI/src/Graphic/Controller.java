/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

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
}