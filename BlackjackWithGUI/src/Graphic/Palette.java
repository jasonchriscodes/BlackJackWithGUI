/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

import java.awt.Color;

/**
 *
 * @author Jason Christian - 21136899
 */
public interface Palette {

    Color background();

    Color menu();

    Color separator();

    Color heading();

    Color text();

    Color button();

    Color altButton();

    default Color table() {
        return new Color(0, 102, 51);
    }

    default Color tableDark() {
        return table().darker();
    }

    default Color tableDarker() {
        return tableDark().darker();
    }

    default Color tableDarkest() {
        return tableDarker().darker();
    }

    default Color tableLight() {
        return table().brighter();
    }

    default Color green() {
        return new Color(42, 193, 88);
    }

    default Color red() {
        return new Color(201, 63, 63);
    }
}
