/**
 * Class represents a table template.
 */
package View;

import Graphic.DarkPalette;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Jason Christian - 21136899
 */
public class Table extends JPanel {

    private Image texture;

    public Table() {
        initTable();
    }

    private void initTable() {
        loadTexture();

        int w = (texture != null) ? texture.getWidth(this) : 1920; //1600
        int h = (texture != null) ? texture.getHeight(this) : 1080; //900
        setPreferredSize(new Dimension(w, h));
    }

    private void loadTexture() {
        String path = View.IMG_PATH + "table.png";
        try {
            ImageIcon icon = new ImageIcon(View.class.getResource(path));
            texture = icon.getImage();
        } catch (NullPointerException ex) {
            System.out.println("Could not find " + path);
            setBackground(new DarkPalette().table());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(texture, 0, 0, this);
    }
}
