/*
 * Class represents new game panel
 */
package Graphic;

import File.DBOperations;
import File.FileManagement;
import Players.HumanPlayer;
import Players.Participant;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.util.List;

/**
 *
 * @author Jason Christian - 21136899
 */
public class NewGamePanel extends BasePanel {

    /**
     * The name label.
     */
    private JLabel nameLabel;

    /**
     * The name field.
     */
    private JTextField nameField;

    /**
     * The play button.
     */
    private JButton playButton;

    /**
     * The back button.
     */
    private JButton backButton;

    /**
     * The msg box.
     */
    private JDialog msgBox;

    /**
     * Empty constructor
     */
    public NewGamePanel() {

    }

    /**
     * Instantiates a new new game panel.
     *
     * @param imgSrc the img src
     */
    public NewGamePanel(String imgSrc) {
        super(imgSrc);
        setLayout(null);

        nameLabel = new JLabel("Player Name", JLabel.CENTER);
        nameField = new JTextField();
        playButton = new JButton("Play");
        backButton = new JButton("Back");

        add(nameLabel);
        add(nameField);
        add(playButton);
        add(backButton);

        nameLabel.setFont(new Font("", Font.PLAIN, 16));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(WIDTH / 2 - 60, HEIGHT / 2 + 30, 120, 20);

        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setFont(new Font("", Font.BOLD, 16));
        nameField.setBorder(null);
        nameField.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 60, 200, 40);

        playButton.setBounds(WIDTH / 2 - 60, HEIGHT / 2 + 110, 120, 42);
        backButton.setBounds(WIDTH / 2 - 60, HEIGHT / 2 + 180, 120, 42);

        DBOperations dboperations = new DBOperations();

        FileManagement file;

        try {
            file = new FileManagement();

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nameField.setText("");
                    playButton.setEnabled(true);
                    BlackjackFrame.cardLayout.show(getParent(), "welcome");
                }
            });

            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playButton.setEnabled(false);
                    if (nameField.getText().isEmpty()) {
                        nameField.setText("Empty name!");
                    } else if (!dboperations.checkName(nameField.getText())) {
                        nameField.setText("Characters only!");
                    } else if (dboperations.hasUser(nameField.getText())) {
                        nameField.setText("User already exists!");
                    } else {
                        if (dboperations.addUser(nameField.getText())) {
                            BlackjackFrame.cardLayout.show(getParent(), "game");
                            String name = nameField.getText();
                        } else {
                            nameField.setText("Failed creating player!");
                        }
                    }
                }
            });

            nameField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    nameField.setText("");
                    playButton.setEnabled(true);
                }

                @Override
                public void focusLost(FocusEvent e) {

                }
            });
        } catch (IOException ex) {
            Logger.getLogger(NewGamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JButton getPlayButton() {
        return playButton;
    }

}
