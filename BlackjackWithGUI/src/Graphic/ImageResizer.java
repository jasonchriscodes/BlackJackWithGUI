/**
 * Class represents an ImageIcon resize tool.
 */
package Graphic;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Jason Christian - 21136899
 */
public class ImageResizer {

    /**
     * Returns a scaled instance of an ImageIcon.
     *
     * @param icon the ImageIcon
     * @param scale the desired scale
     * @return a scaled instance of an ImageIcon
     */
    public static ImageIcon getScaledImage(ImageIcon icon, int scale) {
        Image image = icon.getImage();
        Image scaledImg = image.getScaledInstance(
                scale, -scale, Image.SCALE_SMOOTH
        );

        return new ImageIcon(scaledImg);
    }

    // Suppress default constructor for noninstantiability
    private ImageResizer() {
        throw new AssertionError();
    }
}
