/*
 * Class represents face
 */
package Cards;

/**
 *
 * @author Jason Christian - 21136899
 */
public enum Face {

    KING("King"),
    JACK("Jack"),
    QUEEN("Queen");

    private final String face;

    Face(String face) {
        this.face = face;
    }

    @Override
    public String toString() {
        return face;
    }
}
