/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

import java.util.Map;
import javax.swing.*;

/**
 *
 * @author Jason Christian - 21136899
 */
public class View {

    private final Map<String, JButton> betOptions;
    private final JPanel betOptionsPanel;

    public void initBetOptions(int[] options) {
        String[] stringOptions = new String[options.length];
        for (int i = 0; i < stringOptions.length; i++) {
            stringOptions[i] = String.valueOf(options[i]);
        }
        initOptions("CHIPS", stringOptions, betOptions, betOptionsPanel, true);
    }

}
