/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author Jason Christian - 21136899
 */
public class View {

    public static final DefaultFont FONT;
    public static final String IMG_PATH = "font/IBMPlexSans-Regular.ttf";

    private final Map<String, JButton> betOptions;
    private final JPanel betOptionsPanel;

    static {
        loadFont();
        FONT = new DefaultFont("IBM Plex Sans");
    }

    private static void loadFont() {
        String path = IMG_PATH + "IBMPlexSans-Regular.ttf";
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

}
