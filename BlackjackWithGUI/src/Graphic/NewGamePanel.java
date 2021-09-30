/*
 * Class represents new game panel
 */
package Graphic;

import File.DBOperations;
import File.FileManagement;
import Players.BotDealer;
import Players.BotPlayer;
import Players.HumanPlayer;
import Players.Participant;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Jason Christian - 21136899
 */
public class NewGamePanel extends BasePanel {

    private List<Participant> players;
    private int[] botArr = {1, 2, 3};
    private int noOfBots = 0;
    private JOptionPane Popup;
    private Participant dealer;

    /**
     * The name label.
     */
    private JLabel nameLabel;
    private JLabel botLabel;

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
     * The combo
     */
    private JComboBox botBox;

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
        botLabel = new JLabel("No of bots", JLabel.CENTER);
        nameField = new JTextField();
        playButton = new JButton("Play");
        backButton = new JButton("Back");
        botBox = new JComboBox();

        add(nameLabel);
        add(nameField);
        add(playButton);
        add(backButton);
        add(botLabel);
        add(botBox);

        nameLabel.setFont(new Font("", Font.PLAIN, 16));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(WIDTH / 2 - 60, HEIGHT / 2, 120, 20);

        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setFont(new Font("", Font.BOLD, 16));
        nameField.setBorder(null);
        nameField.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 30, 200, 40);

        botLabel.setFont(new Font("", Font.PLAIN, 16));
        botLabel.setForeground(Color.WHITE);
        botLabel.setBounds(WIDTH / 2 - 60, HEIGHT / 2 + 80, 120, 20);
        botBox.setBounds(WIDTH / 2 - 20, HEIGHT / 2 + 110, 40, 40);

        playButton.setBounds(WIDTH / 2 + 65, HEIGHT / 2 + 200, 120, 42);
        backButton.setBounds(WIDTH / 2 - 200, HEIGHT / 2 + 200, 120, 42);

        // Bot components
        // Add choices to comboBox via for loop
        for (int i = 0; i < botArr.length; i++) {
            botBox.addItem(botArr[i]);
        }

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
                    noOfBots = (Integer) botBox.getSelectedItem();
                    playButton.setEnabled(false);
                    if (nameField.getText().isEmpty()) {
                        Popup.showMessageDialog(null, "Empty name!",
                                "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else if (!dboperations.checkName(nameField.getText())) {
                        Popup.showMessageDialog(null, "Characters only!",
                                "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else if (dboperations.hasUser(nameField.getText())) {
                        Popup.showMessageDialog(null, "User already exists!",
                                "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (dboperations.addUser(nameField.getText())) {
                            players = new ArrayList<>();
                            String name = nameField.getText();
                            players.add(new HumanPlayer(name, 100));
                            dealer = new BotDealer("Dealer", players);
                            addPlayerBot(noOfBots);
                            BlackjackFrame.cardLayout.show(getParent(), "game");
                            file.createNewFile();
                            file.addHumanToMap(new HumanPlayer(name, 100));
                            Popup.showMessageDialog(null, "You do not have "
                                    + "saved data named: " + players.get(0).getName() + " and you start "
                                    + "with: " + players.get(0).getTotalGain() + " chips \nYou also will play with "
                                    + noOfBots + " bot", "Message", JOptionPane.INFORMATION_MESSAGE
                            );

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
}
