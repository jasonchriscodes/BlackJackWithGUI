/*
 * Class represents BasePanel that extends JComponent
 */
package Graphic;

/**
 *
 * @author Jason Christian - 21136899
 */
import java.awt.*;
import javax.swing.*;

public class BasePanel extends JComponent {

    /**
     * The background image.
     */
    private Image bgImage;

    /**
     * The Constant WIDTH.
     */
    public static final int WIDTH = 450;

    /**
     * The Constant HEIGHT.
     */
    public static final int HEIGHT = 600;

    /**
     * Instantiates a new base panel.
     */
    public BasePanel() {
        bgImage = null;
    }

    /**
     * Instantiates a new base panel.
     *
     * @param imgSrc the img src
     */
    public BasePanel(String imgSrc) {
        bgImage = new ImageIcon(imgSrc).getImage();
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

    /* (non-Javadoc)
     * @see javax.swing.JComponent#getPreferredSize()
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}
