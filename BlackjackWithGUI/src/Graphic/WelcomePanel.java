/*
 * Class represents welcoming panel
 */
package Graphic;

import File.DBOperations;
import java.awt.event.*;
import javax.swing.*;
import blackjack.controller.*;

/**
 *
 * @author Jason Christian - 21136899
 */
public class WelcomePanel extends BasePanel {

    /**
     * The new game button.
     */
    JButton newGameButton;

    /**
     * The load game button.
     */
    JButton loadGameButton;

    /**
     * The quit button.
     */
    JButton quitButton;

    /**
     * Instantiates a new welcome panel.
     *
     * @param imgSrc the img src
     */
    public WelcomePanel(String imgSrc) {
        super(imgSrc);
        setLayout(null);

        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton quitButton = new JButton("Quit");

        add(newGameButton);
        add(loadGameButton);
        add(quitButton);

        newGameButton.setBounds(WIDTH / 2 - 60, HEIGHT / 2 + 30, 120, 42);
        loadGameButton.setBounds(WIDTH / 2 - 60, HEIGHT / 2 + 100, 120, 42);
        quitButton.setBounds(WIDTH / 2 - 60, HEIGHT / 2 + 170, 120, 42);

        DBOperations dboperations = new DBOperations();

        if (dboperations.isDatabaseEmpty()) {
            loadGameButton.setEnabled(false);
        }

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlackjackFrame.cardLayout.show(getParent(), "newgame");
            }
        });

        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlackjackFrame.cardLayout.show(getParent(), "loadgame");
            }
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                loadGameButton.setEnabled(User.isDatabaseEmpty() ? false : true);
            }
        });
    }
}
