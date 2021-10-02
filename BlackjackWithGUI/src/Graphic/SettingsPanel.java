/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

import Graphic.ImageResizer;
import Graphic.View;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Jason Christian - 21136899
 */
public class SettingsPanel extends JPanel {

    private JPanel menuItemsPanel;
    private JLabel logoLabel;
    private JLabel playerLabel;
    private JLabel nameLabel;
    private JLabel gameSettingLabel;
    private JLabel noOfBotLabel;
    private JLabel minimumBetLabel;
    private JTextField nameTextField;
    private JSpinner botSpinner;
    private JSpinner betSpinner;
    private JCheckBox displayHandValue;
    private JButton playOptionsPanel;
    private final static int HIT = 0;
    private final static int HOLD = 1;

    public SettingsPanel(ImageIcon logo) {
        initPanel(logo);
    }

    private void initPanel(ImageIcon logo) {
        menuItemsPanel = new JPanel();
        logoLabel = new JLabel();
        playerLabel = new JLabel("PLAYER");
        nameLabel = new JLabel("Name");
        gameSettingLabel = new JLabel("GAME SETTING");
        noOfBotLabel = new JLabel("Number of bots");
        minimumBetLabel = new JLabel("Minimum bet");
        nameTextField = new JTextField();
        botSpinner = new JSpinner();
        betSpinner = new JSpinner();
        displayHandValue = new JCheckBox("Show hand value");
        playOptionsPanel = new JButton("PLAY");

        logoLabel.setIcon(ImageResizer.getScaledImage(logo, 150));

        displayHandValue.setSelected(true);

        botSpinner.setModel(new SpinnerNumberModel(1, 1, 3, 1));
        betSpinner.setModel(new SpinnerNumberModel(25, 0, 100, 5));
        int len = botSpinner.getEditor().getComponentCount();
        for (int i = 0; i < len; i++) {
            Component dc = botSpinner.getEditor().getComponent(i);
            dc.setBackground(View.PALETTE.menu());
            dc.setForeground(View.PALETTE.text());

            Component bc = betSpinner.getEditor().getComponent(i);
            bc.setBackground(View.PALETTE.menu());
            bc.setForeground(View.PALETTE.text());
        }

        playerLabel.setFont(View.FONT.generate(12, Font.BOLD));
        nameLabel.setFont(View.FONT.generate(14));
        gameSettingLabel.setFont(View.FONT.generate(12, Font.BOLD));
        nameTextField.setFont(View.FONT.generate(14));
        botSpinner.setFont(View.FONT.generate(14));
        betSpinner.setFont(View.FONT.generate(14));
        displayHandValue.setFont(View.FONT.generate(14));
        playOptionsPanel.setFont(View.FONT.generate(14, Font.BOLD));

        playerLabel.setForeground(View.PALETTE.heading());
        nameLabel.setForeground(View.PALETTE.text());
        gameSettingLabel.setForeground(View.PALETTE.heading());
        noOfBotLabel.setForeground(View.PALETTE.text());
        minimumBetLabel.setForeground(View.PALETTE.text());
        nameTextField.setForeground(View.PALETTE.text());
        displayHandValue.setForeground(View.PALETTE.text());
        playOptionsPanel.setForeground(View.PALETTE.menu());

        EmptyBorder eb = new EmptyBorder(1, 1, 1, 1);
        LineBorder lb = new LineBorder(View.PALETTE.separator());
        nameTextField.setBorder(new CompoundBorder(lb, eb));
        botSpinner.setBorder(eb);
        betSpinner.setBorder(eb);

        nameTextField.setBackground(View.PALETTE.menu());
        botSpinner.setBackground(View.PALETTE.separator());
        betSpinner.setBackground(View.PALETTE.separator());
        menuItemsPanel.setBackground(View.PALETTE.menu());
        displayHandValue.setBackground(View.PALETTE.menu());
        playOptionsPanel.setBackground(View.PALETTE.button());
        menuItemsPanel.setBorder(new LineBorder(View.PALETTE.separator()));
        setOpaque(false);

        menuItemsPanel.setLayout(new GridBagLayout());
        setLayout(new GridBagLayout());

        GridBagConstraints outerGBC = new GridBagConstraints();

        outerGBC.gridx = 0;
        outerGBC.gridy = 0;
        outerGBC.insets = new Insets(10, 10, 10, 10);
        add(logoLabel, outerGBC);

        outerGBC.gridy++;
        outerGBC.insets = new Insets(0, 0, 0, 0);
        add(menuItemsPanel, outerGBC);

        GridBagConstraints innerGBC = new GridBagConstraints();

        innerGBC.gridx = 0;
        innerGBC.gridy = 0;
        innerGBC.gridwidth = 2;
        innerGBC.insets = new Insets(10, 10, 2, 10);
        innerGBC.fill = GridBagConstraints.HORIZONTAL;
        menuItemsPanel.add(playerLabel, innerGBC);

        innerGBC.gridy++;
        innerGBC.insets = new Insets(0, 10, 10, 10);
        menuItemsPanel.add(createSeparator(View.PALETTE.separator()), innerGBC);

        innerGBC.gridy++;
        innerGBC.gridwidth = 1;
        innerGBC.insets = new Insets(0, 10, 10, 10);
        menuItemsPanel.add(nameLabel, innerGBC);

        innerGBC.gridx++;
        innerGBC.insets = new Insets(0, 0, 10, 10);
        menuItemsPanel.add(nameTextField, innerGBC);

        innerGBC.gridx = 0;
        innerGBC.gridy++;
        innerGBC.gridwidth = 2;
        innerGBC.insets = new Insets(10, 10, 2, 10);
        menuItemsPanel.add(gameSettingLabel, innerGBC);

        innerGBC.gridy++;
        innerGBC.insets = new Insets(0, 10, 10, 10);
        menuItemsPanel.add(createSeparator(View.PALETTE.separator()), innerGBC);

        innerGBC.gridy++;
        innerGBC.gridwidth = 1;
        innerGBC.insets = new Insets(0, 10, 10, 10);
        menuItemsPanel.add(noOfBotLabel, innerGBC);

        innerGBC.gridx++;
        innerGBC.insets = new Insets(10, 0, 10, 10);
        menuItemsPanel.add(botSpinner, innerGBC);

        innerGBC.gridx = 0;
        innerGBC.gridy++;
        innerGBC.insets = new Insets(0, 10, 10, 10);
        menuItemsPanel.add(minimumBetLabel, innerGBC);

        innerGBC.gridx++;
        innerGBC.insets = new Insets(0, 0, 10, 10);
        menuItemsPanel.add(betSpinner, innerGBC);

        innerGBC.gridy++;
        innerGBC.insets = new Insets(0, 10, 10, 10);
        menuItemsPanel.add(createSeparator(View.PALETTE.separator()), innerGBC);

        innerGBC.gridy++;
        innerGBC.fill = GridBagConstraints.NONE;
        innerGBC.insets = new Insets(10, 10, 0, 10);
        menuItemsPanel.add(playOptionsPanel, innerGBC);

    }

    private JSeparator createSeparator(Color fg) {
        JSeparator separator = new JSeparator();
        separator.setForeground(fg);
        separator.setBackground(fg);
        return separator;
    }

    public void initPlayActionListener(ActionListener l) {
        playOptionsPanel.addActionListener(l);
    }
}
