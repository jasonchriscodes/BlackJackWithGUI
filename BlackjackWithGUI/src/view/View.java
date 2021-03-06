/**
 * Class represents a view class in MVC pattern.
 */
package View;

import Graphic.*;
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
import view.WelcomeSettingPanel;

/**
 *
 * @author Jason Christian - 21136899
 */
public class View {

    public static final DefaultFont FONT;
    public static final Palette PALETTE = new DarkPalette();
    public static final String CARD_PATH = "/image/Cards/";
    public static final String IMG_PATH = "/image/";
    public static final String FNT_PATH = "/font/";

    private final JFrame frame;
    private final JPanel welcomePanel;
    private final JPanel startPanel;
    private final JPanel backgroundPanel;
    private final JPanel topPanel;
    private final JLabel welcomeLabel;
    private final JLabel titleLabel;
    private final JPanel messagePanel;
    private final JLabel messageHeader;
    private final JLabel messageLabel;
    private final JPanel totalChipsPanel;
    private final JLabel totalChipsTitle;
    private final JLabel chipsLabel;
    private final JPanel tablePanel;
    private final SettingsPanel settingsPanel;
    private final WelcomeSettingPanel welcomeSettingPanel;
    private final JLabel dealerHandValueLabel;
    private final JPanel dealerPanel;
    private final JLabel[] dealerHand;
    private final JLabel playerHandValueLabel;
    private final JPanel playerPanel;
    private final JLabel[] playerHand;
    private final JPanel optionsPanel;
    private final JPanel currentBetPanel;
    private final JLabel deckCountLabel;
    private final JLabel deckCountTitle;
    private final JLabel trueCountLabel;
    private final JLabel trueCountTitle;
    private final JLabel currentBetValueLabel;
    private final JLabel currentBetValueTitle;
    private final JPanel betOptionsPanel;
    private final Map<String, JButton> welcomeOptions;
    private final JPanel welcomeOptionsPanel;
    private final Map<String, JButton> betOptions;
    private final JPanel playOptionsPanel;
    private final Map<String, JButton> playOptions;
    private final JPanel handOptionsPanel;
    private final Map<String, JButton> handOptions;

    private static final String CARD_STYLE = "classic";
    private static final int CARD_SIZE = 115;
    private Image texture;

    static {
        loadFont();
        FONT = new DefaultFont("IBM Plex Sans");
    }

    public View() {
        frame = new JFrame("Blackjack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1366, 725); //1366, 725
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        backgroundPanel = new Table();

        // Styling for the option pane
        UIManager.put("OptionPane.messageFont", FONT.generate(14));
        UIManager.put("OptionPane.background", PALETTE.menu());
        UIManager.put("OptionPane.messageForeground", PALETTE.text());
        UIManager.put("Panel.background", PALETTE.menu());
        UIManager.put("OptionPane.buttonFont", FONT.generate(12));
        UIManager.put("Button.foreground", PALETTE.menu());
        UIManager.put("Button.background", PALETTE.button());

        welcomePanel = new JPanel();
        startPanel = new JPanel();
        topPanel = new JPanel();
        welcomeLabel = new JLabel();
        titleLabel = new JLabel();

        setIcon(titleLabel, "blackjacklogo.png", 150);

        messagePanel = new JPanel();
        messageHeader = new JLabel("MESSAGE");
        messageLabel = new JLabel();

        messageHeader.setForeground(PALETTE.heading());
        messageHeader.setFont(FONT.generate(12, Font.BOLD));

        messagePanel.setBorder(new LineBorder(PALETTE.separator()));
        messagePanel.setBackground(PALETTE.menu());
        messagePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 2, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        messagePanel.add(messageHeader, gbc);

        JSeparator separator = new JSeparator();
        separator.setForeground(PALETTE.separator());
        separator.setBackground(PALETTE.separator());

        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 10, 10);
        messagePanel.add(separator, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 10, 10);
        messagePanel.add(messageLabel, gbc);

        chipsLabel = new JLabel();

        welcomeSettingPanel = new WelcomeSettingPanel("background.png");
        settingsPanel = new SettingsPanel((ImageIcon) titleLabel.getIcon());

        tablePanel = new JPanel();
        dealerHandValueLabel = new JLabel();
        dealerPanel = new JPanel();
        dealerHand = new JLabel[10];
        playerHandValueLabel = new JLabel();
        playerPanel = new JPanel();
        playerHand = new JLabel[10];

        optionsPanel = new JPanel();
        currentBetPanel = new JPanel();
        deckCountLabel = new JLabel();
        deckCountTitle = new JLabel("Remaining Deck: ");
        trueCountLabel = new JLabel();
        trueCountTitle = new JLabel("True Count: ");
        currentBetValueLabel = new JLabel();
        currentBetValueTitle = new JLabel("Current Bet: ");
        welcomeOptionsPanel = new JPanel();
        welcomeOptions = new HashMap<>();
        betOptionsPanel = new JPanel();
        betOptions = new HashMap<>();
        playOptionsPanel = new JPanel();
        playOptions = new HashMap<>();
        handOptionsPanel = new JPanel();
        handOptions = new HashMap<>();

        topPanel.setBackground(PALETTE.table());
        messageLabel.setForeground(PALETTE.text());
        messageLabel.setFont(FONT.generate(18));

        totalChipsPanel = new JPanel();
        totalChipsTitle = new JLabel("Total Chips: ");
        chipsLabel.setForeground(Color.WHITE);
        chipsLabel.setFont(FONT.generate(30));

        setIcon(chipsLabel, "chip.png", 30);
        String mode = (PALETTE instanceof LightPalette) ? "light" : "dark";
        setIcon(deckCountLabel, mode + "/deck.png", 30);
        setIcon(trueCountLabel, mode + "/card_count.png", 30);
        setIcon(currentBetValueLabel, mode + "/bet.png", 30);

        tablePanel.setBackground(PALETTE.table());
        dealerHandValueLabel.setForeground(Color.WHITE);
        dealerPanel.setBackground(PALETTE.table());
        playerHandValueLabel.setForeground(Color.WHITE);
        playerPanel.setBackground(PALETTE.table());
        dealerHandValueLabel.setFont(FONT.generate(16));
        playerHandValueLabel.setFont(FONT.generate(16));

        welcomePanel.setBackground(PALETTE.table());
        startPanel.setBackground(PALETTE.table());
        optionsPanel.setBackground(PALETTE.table());
        currentBetPanel.setBackground(PALETTE.table());
        betOptionsPanel.setBackground(PALETTE.menu());
        playOptionsPanel.setBackground(PALETTE.menu());
        handOptionsPanel.setBackground(PALETTE.menu());
        totalChipsTitle.setForeground(PALETTE.text());
        deckCountLabel.setForeground(PALETTE.text());
        deckCountTitle.setForeground(PALETTE.text());
        trueCountLabel.setForeground(PALETTE.text());
        trueCountTitle.setForeground(PALETTE.text());
        currentBetValueLabel.setForeground(PALETTE.text());
        currentBetValueTitle.setForeground(PALETTE.text());
        totalChipsPanel.setBackground(PALETTE.table());
        totalChipsTitle.setFont(FONT.generate(30));
        deckCountLabel.setFont(FONT.generate(30));
        deckCountTitle.setFont(FONT.generate(30));
        trueCountLabel.setFont(FONT.generate(30));
        trueCountTitle.setFont(FONT.generate(30));
        currentBetValueLabel.setFont(FONT.generate(30));
        currentBetValueTitle.setFont(FONT.generate(30));

        frame.add(backgroundPanel, BorderLayout.CENTER);

        backgroundPanel.setLayout(new BorderLayout());

        layoutWelcomePanel();
        layoutStartPanel();
        layoutTopPanel();
        layoutTablePanel();

        dealerPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        playerPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));

        /*
        Images are displayed using ten labels each for the player and dealer.
        Ten labels are persistently on the screen instead of being dynamically
        added. Unless the player or dealer gets ten aces straight, ten labels
        should suffice.
         */
        for (int i = 0; i < dealerHand.length; i++) {
            JLabel dealerCard = new JLabel();
            JLabel playerCard = new JLabel();
            dealerHand[i] = dealerCard;
            playerHand[i] = playerCard;
            dealerPanel.add(dealerCard);
            playerPanel.add(playerCard);
        }

        layoutOptionsPanel();

        // Remove background from every panel so texture can be seen
        if (backgroundPanel.getBackground() != PALETTE.table()) {
            welcomePanel.setOpaque(false);
            startPanel.setOpaque(false);
            playerPanel.setOpaque(false);
            dealerPanel.setOpaque(false);
            topPanel.setOpaque(false);
            tablePanel.setOpaque(false);
            optionsPanel.setBackground(PALETTE.background());
            currentBetPanel.setOpaque(false);
        }
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

    /**
     * Removes any message displayed on the screen.
     */
    public void clearMessage() {
        messageLabel.setText("");
        messageLabel.setIcon(null);
        messagePanel.setVisible(false);
    }

    /**
     * Displays the frame.
     */
    public void displayFrame() {
        frame.setVisible(true);
        SwingUtilities.invokeLater(View::new);
    }

    public void displayMenu() {
        backgroundPanel.removeAll();
        backgroundPanel.add(welcomePanel);
        backgroundPanel.repaint();
    }

    public void displaySettings() {
        backgroundPanel.removeAll();
        backgroundPanel.add(startPanel);
        backgroundPanel.validate();
        backgroundPanel.repaint();
    }

    public void displayTable() {
        backgroundPanel.removeAll();
        backgroundPanel.add(topPanel, BorderLayout.NORTH);
        backgroundPanel.add(tablePanel, BorderLayout.CENTER);
        backgroundPanel.add(optionsPanel, BorderLayout.SOUTH);
        backgroundPanel.repaint();
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

    public boolean prompt(String message, String title) {
        int option = JOptionPane.showConfirmDialog(
                frame, message, title, JOptionPane.YES_NO_OPTION
        );
        return option == 0;
    }

    public boolean promptStartGame(String message, String title) {
        JFrame frame = new JFrame();
        String[] options = new String[2];
        options[0] = "Start a new game";
        options[1] = "Load the game";
        int option = JOptionPane.showOptionDialog(frame.getContentPane(), message, title, 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        return option == 0;
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

    public boolean isChoiceEnabled(String choice) {
        return playOptions.get(choice).isEnabled();
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
    public void revealHoleCard(String holeCardName) {
        String[] comp = holeCardName.split(" ");
        String value = CARD_STYLE + "_" + comp[0].toLowerCase();
        String suit = comp[2].toLowerCase();
        String path = suit + "/" + value + ".png";
        setIcon(dealerHand[0], path, CARD_SIZE);
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
     * Updates the card images on the dealer's side of the screen.
     *
     * @param cardNames the names of the cards
     */
    public void updateDealerCards(String[] cardNames) {
        updateImages(cardNames, dealerHand);
    }

    /**
     * Updates the maximum value of the dealer's hand.
     *
     * @param dealerHandValue the dealer's hand value
     */
    public void updateDealerHandValue(int dealerHandValue) {
        dealerHandValueLabel.setText(dealerHandValue + " ??? Dealer");

        String path = "question.png";
        int size = dealerHandValueLabel.getFont().getSize();
        setIcon(dealerHandValueLabel, path, size);
    }

    public void updateDealerHandValue(int dealerHandValue, boolean isSoft) {
        dealerHandValueLabel.setText(dealerHandValue + " ??? Dealer");

        String fileName = (isSoft) ? "soft.png" : "hard.png";
        int size = dealerHandValueLabel.getFont().getSize();
        setIcon(dealerHandValueLabel, fileName, size);
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
     * Updates the card images on the player's side of the screen.
     *
     * @param cardNames the names of the cards
     */
    public void updatePlayerCards(String[] cardNames) {
        updateImages(cardNames, playerHand);
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
     * Clears the player and dealer's hand value.
     */
    public void resetHandValue() {
        playerHandValueLabel.setText(" ");
        playerHandValueLabel.setIcon(null);
        dealerHandValueLabel.setText(" ");
        dealerHandValueLabel.setIcon(null);
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
        playerHandValueLabel.setText(value + " ??? " + name);

        String fileName = (soft) ? "soft.png" : "hard.png";
        int size = playerHandValueLabel.getFont().getSize();
        setIcon(playerHandValueLabel, fileName, size);
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
     * Returns a collection of bet options.
     *
     * <p>
     * This method exists so that each button can have an action event without
     * having to write code every time a new button is added.
     *
     * @return the bet options
     */
    public Collection<JButton> getBetOptions() {
        return betOptions.values();
    }

    public Object[] getSettings() {
        return settingsPanel.getSettings();
    }

    public void initBetOptions(int[] options) {
        String[] stringOptions = new String[options.length];
        for (int i = 0; i < stringOptions.length; i++) {
            stringOptions[i] = String.valueOf(options[i]);
        }
        initOptions("CHIPS", stringOptions, betOptions, betOptionsPanel, true);
    }

    public void initPlayOptions(String[] options) {
        initOptions("CHOICES", // Name of the panel, i.e. the header
                options, // The text on the buttons
                this.playOptions, // The buttons
                playOptionsPanel, // The panel containing the buttons
                false);             // If the text has an icon next to it
    }

    public void initWelcomeOptions(String[] options) {
        initOptions("WELCOME", // Name of the panel, i.e. the header
                options, // The text on the buttons
                this.welcomeOptions, // The buttons
                welcomeOptionsPanel, // The panel containing the buttons
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
     * Options include: {@code Deal}, {@code Next Hand}, {@code Hint},
     * {@code New Game} and {@code Quit Game}. Choices include: {@code Hit},
     * {@code Double Down}, {@code Surrender} and {@code Stand}.
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
        } else if (key.equals("Back")) {
            settingsPanel.initBackActionListener(l);
        } else if (key.equals("New Game")) {
            welcomeSettingPanel.initNewGameActionListener(l);
        } else if (key.equals("Exit")) {
            welcomeSettingPanel.initExitActionListener(l);
        } else {
            System.err.println("Invalid key: " + key);
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
     * Enables and displays a button to the screen.
     *
     * <p>
     * This methods checks if the key is an option button or a choice button.
     * Options include: {@code Deal}, {@code Next Hand}, {@code Hint},
     * {@code New Game} and {@code Quit Game}. Choices include: {@code Hit},
     * {@code Double Down}, {@code Surrender} and {@code Stand}.
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
        gbc.insets = new Insets(10, 10, 2, 10);// 10,10,2,10 padding
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

    private void layoutWelcomePanel() {
        welcomePanel.setLayout(new GridBagLayout());
        GridBagConstraints placeholderConstraints = new GridBagConstraints();
        placeholderConstraints.gridx = 0;
        placeholderConstraints.gridy = 0;
        placeholderConstraints.anchor = GridBagConstraints.CENTER;
        welcomePanel.add(welcomeSettingPanel, placeholderConstraints);
    }

    private void layoutStartPanel() {
        startPanel.setLayout(new GridBagLayout());
        GridBagConstraints placeholderConstraints = new GridBagConstraints();
        placeholderConstraints.gridx = 0;
        placeholderConstraints.gridy = 0;
        placeholderConstraints.anchor = GridBagConstraints.CENTER;
        startPanel.add(settingsPanel, placeholderConstraints);
    }

    private void layoutTopPanel() {
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 20, 10, 0);
        topPanel.add(titleLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        topPanel.add(messagePanel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 0, 0, 20);
        topPanel.add(totalChipsPanel, gbc);

        totalChipsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 0));
        totalChipsPanel.add(totalChipsTitle);
        totalChipsPanel.add(chipsLabel);
    }

    private void layoutTablePanel() {
        tablePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 20, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        tablePanel.add(dealerHandValueLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.ipadx = 100;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        tablePanel.add(dealerPanel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 20, 0, 0);
        tablePanel.add(playerHandValueLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.ipadx = 100;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 10, 0);
        tablePanel.add(playerPanel, gbc);
    }

    private void layoutOptionsPanel() {
        optionsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 0, 10, 0);
        optionsPanel.add(currentBetPanel, gbc);

        gbc.gridx++;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        optionsPanel.add(betOptionsPanel, gbc);
        optionsPanel.add(playOptionsPanel, gbc);

        gbc.gridx++;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(10, 0, 10, 20);
        optionsPanel.add(handOptionsPanel, gbc);

        currentBetPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 0));
        currentBetPanel.add(deckCountTitle);
        currentBetPanel.add(deckCountLabel);
        currentBetPanel.add(trueCountTitle);
        currentBetPanel.add(trueCountLabel);
        currentBetPanel.add(currentBetValueTitle);
        currentBetPanel.add(currentBetValueLabel);
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

    private void resetImages(ImageIcon image, JLabel[] playerHand) {
        Arrays.asList(playerHand).forEach((label) -> {
            label.setIcon(image);
        });
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

    private void updateImages(String[] cardNames, JLabel[] labels) {
        for (int i = 0; i < cardNames.length; i++) {
            String[] comp = cardNames[i].split(" ");
            String value = CARD_STYLE + "_" + comp[0].toLowerCase();
            String suit = comp[2].toLowerCase();
            String path = suit + "/" + value + ".png";
            setIcon(labels[i], path, CARD_SIZE);
        }
    }
}
