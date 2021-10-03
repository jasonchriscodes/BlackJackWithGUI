/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author Jason Christian - 21136899
 */
public class View {

    public static final DefaultFont FONT;
    public static final Palette PALETTE = new DarkPalette();
    public static final String FNT_PATH = "/font/";
    public static final String IMG_PATH = "/image/";
    public static final String CARD_PATH = "/image/Cards/";

    private static final String CARD_STYLE = "classic";
    private static final int CARD_SIZE = 115;

    private final Map<String, JButton> betOptions;
    private final Map<String, JButton> playOptions;
    private final Map<String, JButton> handOptions;

    private final JFrame frame;
    private final JLabel titleLabel;
    private final JLabel playerHandValueLabel;
    private final JLabel dealerHandValueLabel;
    private final JLabel[] playerHand;
    private final JLabel[] dealerHand;
    private final JLabel chipsLabel;
    private final JLabel currentBetValueLabel;
    private final JLabel trueCountLabel;
    private final JLabel deckCountLabel;
    private final JLabel messageHeader;
    private final JLabel messageLabel;

    private final JPanel betOptionsPanel;
    private final JPanel playOptionsPanel;
    private final JPanel handOptionsPanel;
    private final JPanel backgroundPanel;
    private final JPanel startPanel;
    private final JPanel topPanel;
    private final JPanel tablePanel;
    private final JPanel optionsPanel;
    private final JPanel messagePanel;

    private final SettingsPanel settingsPanel;

    static {
        loadFont();
        FONT = new DefaultFont("IBM Plex Sans");
    }

    public View() {
        frame = new JFrame("Blackjack");
        backgroundPanel = new Table();
        betOptions = new HashMap<>();
        playOptions = new HashMap<>();
        handOptions = new HashMap<>();
        betOptionsPanel = new JPanel();
        playOptionsPanel = new JPanel();
        handOptionsPanel = new JPanel();
        topPanel = new JPanel();
        tablePanel = new JPanel();
        optionsPanel = new JPanel();
        startPanel = new JPanel();
        messagePanel = new JPanel();
        messageHeader = new JLabel("MESSAGE");
        messageLabel = new JLabel();

        titleLabel = new JLabel();
        playerHandValueLabel = new JLabel();
        dealerHandValueLabel = new JLabel();
        playerHand = new JLabel[10];
        dealerHand = new JLabel[10];
        chipsLabel = new JLabel();
        currentBetValueLabel = new JLabel();
        trueCountLabel = new JLabel();
        deckCountLabel = new JLabel();

        settingsPanel = new SettingsPanel((ImageIcon) titleLabel.getIcon());

        messageHeader.setForeground(PALETTE.heading());
        messageHeader.setFont(FONT.generate(12, Font.BOLD));
    }

    private static void loadFont() {
        String path = FNT_PATH + "IBMPlexSans-Regular.ttf";
        try {
            GraphicsEnvironment ge = GraphicsEnvironment
                    .getLocalGraphicsEnvironment();
            InputStream in = View.class.getResourceAsStream(path);
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, in));
        } catch (IOException | FontFormatException e) {
            System.err.println("Could not register font " + path);
        }
    }

    public void initBetOptions(int[] options) {
        String[] stringOptions = new String[options.length];
        for (int i = 0; i < stringOptions.length; i++) {
            stringOptions[i] = String.valueOf(options[i]);
        }
        initOptions("CHIPS", stringOptions, betOptions, betOptionsPanel, true);
    }

    private void initOptions(String name,
            String[] options,
            Map<String, JButton> map,
            JPanel panel,
            boolean hasIcon) {
        JLabel label = new JLabel(name);
        label.setFont(FONT.generate(12, Font.BOLD));
        label.setForeground(PALETTE.heading());

        panel.setBorder(new LineBorder(PALETTE.separator()));

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = options.length;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 2, 10);
        panel.add(label, gbc);

        JSeparator separator = new JSeparator();
        separator.setForeground(PALETTE.separator());
        separator.setBackground(PALETTE.separator());

        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 10, 10);
        panel.add(separator, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        for (int i = 0; i < options.length; i++) {
            gbc.gridx = i;
            int right = (i == options.length - 1) ? 10 : 0;
            gbc.insets = new Insets(0, 10, 10, right);
            JButton option = new JButton(options[i]);
            option.setForeground(PALETTE.text());
            option.setBackground(PALETTE.altButton());
            option.setFont(FONT.generate(16));

            if (hasIcon) {
                String path = options[i] + ".png";
                setIcon(option, path, 16);
            }

            map.put(options[i], option);
            panel.add(option, gbc);
        }
    }

    private void setIcon(AbstractButton label, String fileName, int size) {
        String path = IMG_PATH + fileName;
        try {
            ImageIcon icon = new ImageIcon(View.class.getResource(path));
            label.setIcon(ImageResizer.getScaledImage(icon, size));
        } catch (NullPointerException ex) {
            System.err.println("Could not find " + path);
        }
    }

    private void setIcon(JLabel label, String fileName, int size) {
        String path1 = IMG_PATH + fileName;
        try {
            ImageIcon icon = new ImageIcon(View.class.getResource(path1));
            label.setIcon(ImageResizer.getScaledImage(icon, size));
        } catch (NullPointerException e) {
            String path2 = CARD_PATH + fileName;
            try {
                ImageIcon icon = new ImageIcon(View.class.getResource(path2));
                label.setIcon(ImageResizer.getScaledImage(icon, size));
            } catch (NullPointerException ex) {
                System.err.println("Could not find " + path1);
                System.err.println("Could not find " + path2);
            }
        }
    }

    /**
     * Displays the frame.
     */
    public void displayFrame() {
        frame.setVisible(true);
        SwingUtilities.invokeLater(View::new);
    }

    public void initPlayOptions(String[] options) {
        initOptions("CHOICES", // Name of the panel, i.e. the header
                options, // The text on the buttons
                this.playOptions, // The buttons
                playOptionsPanel, // The panel containing the buttons
                false);             // If the text has an icon next to it
    }

    public void initHandOptions(String[] options) {
        initOptions("OPTIONS", options, this.handOptions, handOptionsPanel, false);
    }

    /**
     * Adds an ActionListener to a button.
     *
     * <p>
     * This methods checks if the key is an option button or a choice button.
     * Options include: {@code Deal}, {@code Next Round}, {@code Quit Game}.
     * Choices include: {@code Hit}, {@code Hold}.
     *
     * @param key the name of the button
     * @param l the ActionListener
     */
    public void initButtonActionListener(String key, ActionListener l) {
        if (handOptions.containsKey(key)) {
            handOptions.get(key).addActionListener(l);
        } else if (playOptions.containsKey(key)) {
            playOptions.get(key).addActionListener(l);
        } else if (key.equals("Play")) {
            settingsPanel.initPlayActionListener(l);
        } else {
            System.err.println("Invalid key: " + key);
        }
    }

    public void displaySettings() {
        backgroundPanel.removeAll();
        backgroundPanel.add(startPanel);
        backgroundPanel.repaint();
    }

    public Object[] getSettings() {
        return settingsPanel.getSettings();
    }

    public void displayTable() {
        backgroundPanel.removeAll();
        backgroundPanel.add(topPanel, BorderLayout.NORTH);
        backgroundPanel.add(tablePanel, BorderLayout.CENTER);
        backgroundPanel.add(optionsPanel, BorderLayout.SOUTH);
        backgroundPanel.repaint();
    }

    /**
     * Clears the player and dealer's hand value.
     */
    public void resetHandValue() {
        playerHandValueLabel.setText(" ");
        playerHandValueLabel.setIcon(null);
        dealerHandValueLabel.setText(" ");
        dealerHandValueLabel.setIcon(null);
    }

    /**
     * Removes all card images on the screen.
     *
     * <p>
     * This method <b>doesn't</b> actually remove every image on the screen, but
     * rather it replaces the images with a blank image that has the same size
     * as the card images. This is to keep the table border stretched along the
     * length of the screen even if no cards are displayed.
     */
    public void clearCards() {
        String path = IMG_PATH + "blank.png";
        try {
            ImageIcon icon = new ImageIcon(View.class.getResource(path));
            resetImages(ImageResizer.getScaledImage(icon, CARD_SIZE),
                    playerHand);
            resetImages(ImageResizer.getScaledImage(icon, CARD_SIZE),
                    dealerHand);
        } catch (NullPointerException ex) {
            resetImages(null, playerHand);
            resetImages(null, dealerHand);
            System.err.println("Could not find " + path);
        }
    }

    private void resetImages(ImageIcon image, JLabel[] playerHand) {
        Arrays.asList(playerHand).forEach((label) -> {
            label.setIcon(image);
        });
    }

    /**
     * Updates the amount of chips and the current bet of the player.
     *
     * @param chips the player's chips
     * @param bet the player's bet
     */
    public void updateStats(double chips, double bet) {
        chipsLabel.setText(Format.currency(chips));
        currentBetValueLabel.setText(Format.currency(bet));
    }

    /**
     * Updates the true count of cards displayed on the screen.
     *
     * @param count the true count
     */
    public void updateTrueCount(int count) {
        trueCountLabel.setText(count + "");
    }

    /**
     * Updates the number of decks displayed on screen.
     *
     * @param count the number of decks
     */
    public void updateDeckCount(int count) {
        deckCountLabel.setText(count + "");
    }

    /**
     * Displays a message on the screen with a message bubble icon.
     *
     * @param message the message
     */
    public void displayMessage(String message) {
        displayMessage("MESSAGE", message, "message.png");
    }

    /**
     * Displays a message on the screen with the specified icon.
     *
     * @param header the type of message
     * @param message the message
     * @param filename the filename of the icon
     */
    public void displayMessage(String header, String message, String filename) {
        messagePanel.setVisible(true);
        messageHeader.setText(header);
        messageLabel.setText(message);
        int size = messageHeader.getFont().getSize();
        String mode = (PALETTE instanceof LightPalette) ? "light/" : "dark/";
        setIcon(messageHeader, mode + filename, size);
    }

    /**
     * Displays all bet options.
     */
    public void enableAllChips() {
        betOptionsPanel.setVisible(true);
        betOptions.values().forEach((betOption) -> {
            betOption.setEnabled(true);
            betOption.setVisible(true);
        });
    }

    /**
     * Updates the bet options according to the player's remaining chips.
     *
     * @param chips the player's remaining chips
     * @param betValues the possible bet options
     */
    public void updateChips(double chips, int[] betValues) {
        for (int i = 0, len = betValues.length; i < len; i++) {
            String betValue = String.valueOf(betValues[i]);
            if (chips < betValues[i]) {
                betOptions.get(betValue).setEnabled(false);
                betOptions.get(betValue).setVisible(false);
            } else {
                betOptions.get(betValue).setEnabled(true);
                betOptions.get(betValue).setVisible(true);
            }
        }
    }

    /**
     * Removes all play options from the display.
     */
    public void disableAllChoices() {
        playOptionsPanel.setVisible(false);
        playOptions.values().forEach((playOption) -> {
            playOption.setEnabled(false);
            playOption.setVisible(false);
        });
    }

    /**
     * Enables and displays a button to the screen.
     *
     * <p>
     * This methods checks if the key is an option button or a choice button.
     * Options include: {@code Deal}, {@code Next Round}, and {@code Quit Game}.
     * Choices include: {@code Hit} and {@code Stand}.
     *
     * @param keys the names of the buttons
     */
    public void enableButton(String... keys) {
        for (String key : keys) {
            if (handOptions.containsKey(key)) {
                handOptions.get(key).setEnabled(true);
                handOptions.get(key).setVisible(true);
            } else if (playOptions.containsKey(key)) {
                playOptions.get(key).setEnabled(true);
                playOptions.get(key).setVisible(true);
            } else {
                System.err.println("Invalid key: " + key);
            }
        }
    }

    /**
     * Disables and removes a button from the screen.
     *
     * <p>
     * This methods checks if the key is an option button or a choice button.
     * Options include: {@code Deal}, {@code Next Hand}, {@code Hint},
     * {@code New Game} and {@code Quit Game}. Choices include: {@code Hit},
     * {@code Double Down}, {@code Surrender} and {@code Stand}.
     *
     * @param keys the names of the buttons
     */
    public void disableButton(String... keys) {
        for (String key : keys) {
            if (handOptions.containsKey(key)) {
                handOptions.get(key).setEnabled(false);
                handOptions.get(key).setVisible(false);
            } else if (playOptions.containsKey(key)) {
                playOptions.get(key).setEnabled(false);
                playOptions.get(key).setVisible(false);
            } else {
                System.err.println("Invalid key: " + key);
            }
        }
    }

    /**
     * Updates the maximum value and the type of the player's hand.
     *
     * @param name the player's name
     * @param value the player's hand value
     * @param soft if true, an S indicating a soft hand will be displayed next
     * to the value, else an H for a hard hand
     */
    public void updatePlayerHandValue(String name, int value, boolean soft) {
        playerHandValueLabel.setText(value + " â€” " + name);

        String fileName = (soft) ? "soft.png" : "hard.png";
        int size = playerHandValueLabel.getFont().getSize();
        setIcon(playerHandValueLabel, fileName, size);
    }

    /**
     * Updates the card images on the player's side of the screen.
     *
     * @param cardNames the names of the cards
     */
    public void updatePlayerCards(String[] cardNames) {
        updateImages(cardNames, playerHand);
    }

    private void updateImages(String[] cardNames, JLabel[] labels) {
        for (int i = 0; i < cardNames.length; i++) {
            String[] comp = cardNames[i].split(" ");
            String value = CARD_STYLE + "_" + comp[0].toLowerCase();
            String suit = comp[2].toLowerCase();
            String path = suit + "/" + value + ".png";
            setIcon(labels[i], path, CARD_SIZE);
        }
    }

    /**
     * Flips over the dealer's hole card.
     *
     * <p>
     * The dealer's hole card is usually revealed after the player has finished
     * their turn.
     *
     * @param holeCardName the name of the hole card
     */
    public void revealHoleCard(Object holeCardName) {
        revealHoleCard(holeCardName.toString());
    }

    /**
     * Updates the card images on the dealer's side of the screen.
     *
     * @param cardNames the names of the cards
     */
    public void updateDealerCards(String[] cardNames) {
        updateImages(cardNames, dealerHand);
    }

    public void updateDealerHandValue(int dealerHandValue, boolean isSoft) {
        dealerHandValueLabel.setText(dealerHandValue + " â€” Dealer");

        String fileName = (isSoft) ? "soft.png" : "hard.png";
        int size = dealerHandValueLabel.getFont().getSize();
        setIcon(dealerHandValueLabel, fileName, size);
    }

    /**
     * Removes all bet options from display.
     */
    public void disableAllChips() {
        betOptionsPanel.setVisible(false);
        betOptions.values().forEach((betOption) -> {
            betOption.setEnabled(false);
            betOption.setVisible(false);
        });
    }

    /**
     * Displays all play options.
     */
    public void enableAllChoices() {
        playOptionsPanel.setVisible(true);
        playOptions.values().forEach((playOption) -> {
            playOption.setEnabled(true);
            playOption.setVisible(true);
        });
    }

    /**
     * Updates the maximum value of the dealer's hand.
     *
     * @param dealerHandValue the dealer's hand value
     */
    public void updateDealerHandValue(int dealerHandValue) {
        dealerHandValueLabel.setText(dealerHandValue + "Dealer");

        String path = "question.png";
        int size = dealerHandValueLabel.getFont().getSize();
        setIcon(dealerHandValueLabel, path, size);
    }

    /**
     * Places the front side of the dealer's hole card down.
     *
     * <p>
     * In blackjack, the dealer will hide one of his two cards after the initial
     * deal. This hidden card is called the hole card.
     */
    public void hideHoleCard() {
        String path = IMG_PATH + CARD_STYLE + "_back.png";
        try {
            ImageIcon icon = new ImageIcon(View.class.getResource(path));
            dealerHand[0].setIcon(ImageResizer.getScaledImage(icon, CARD_SIZE));
        } catch (NullPointerException ex) {
            System.err.println("Could not find " + path);
        }
    }
}
