/*
 * Class represents welcoming panel.
 */
package view;

import File.DBOperations;
import Graphic.DarkPalette;
import View.Table;
import View.View;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Jason Christian - 21136899
 */
public class WelcomeSettingPanel extends JComponent {

    private Image bgImage;
    private JPanel welcomePanel;
    private JButton startGameButton;
    private JButton exitButton;
    private static final int WIDTH = 450;
    private static final int HEIGHT = 600;

    public WelcomeSettingPanel(String imgSrc) {
        initPanel(imgSrc);
    }

    private void initPanel(String imgSrc) {
        loadBgImg(imgSrc);
        welcomePanel = new JPanel();
        setLayout(null);

        startGameButton = new JButton("Start Game");
        exitButton = new JButton("Exit");

        startGameButton.setFont(View.FONT.generate(14, Font.BOLD));
        exitButton.setFont(View.FONT.generate(14, Font.BOLD));

        welcomePanel.setBackground(View.PALETTE.menu());
        startGameButton.setForeground(View.PALETTE.menu());
        exitButton.setForeground(View.PALETTE.menu());

        startGameButton.setBackground(View.PALETTE.button());
        exitButton.setBackground(View.PALETTE.button());

        add(startGameButton);
        add(exitButton);

        startGameButton.setBounds(WIDTH / 2 - 60, HEIGHT / 2 + 80, 120, 42);
        exitButton.setBounds(WIDTH / 2 - 60, HEIGHT / 2 + 170, 120, 42);

    }

    public void initNewGameActionListener(ActionListener l) {
        startGameButton.addActionListener(l);
    }

    public void initExitActionListener(ActionListener l) {
        exitButton.addActionListener(l);
    }

    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    public void paintComponent(Graphics g) {
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, null);
        } else {
            g.setColor(new Color(11, 79, 40, 255));
            g.fillRect(0, 0, WIDTH, HEIGHT);
        }
    }

    private void loadBgImg(String imgSrc) {
        String path = View.IMG_PATH + imgSrc;
        try {
            ImageIcon icon = new ImageIcon(View.class.getResource(path));
            bgImage = icon.getImage();
        } catch (NullPointerException ex) {
            System.out.println("Could not find " + path);
            setBackground(new DarkPalette().table());
        }
    }

    /* (non-Javadoc)
     * @see javax.swing.JComponent#getPreferredSize()
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}
