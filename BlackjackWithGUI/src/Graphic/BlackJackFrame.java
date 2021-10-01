/*
 * Class represents the basic frame of the game (view class)
 */
package Graphic;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 *
 * @author Jason Christian - 21136899
 */
public class BlackjackFrame extends JFrame {

    /**
     * The Constant DEFAULT_WIDTH.
     */
    public static final int DEFAULT_WIDTH = 450;

    /**
     * The Constant DEFAULT_HEIGHT.
     */
    public static final int DEFAULT_HEIGHT = 600;

    /**
     * The Constant cardLayout.
     */
    public static final CardLayout cardLayout = new CardLayout();

    /**
     * Instantiates a new blackjack frame.
     */
    public BlackjackFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setLayout(cardLayout);
        setTitle("Blackjack");
        setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                JOptionPane.showMessageDialog(null, "Exiting game!\nYour record will be saved!", "Information", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });
    }
}
