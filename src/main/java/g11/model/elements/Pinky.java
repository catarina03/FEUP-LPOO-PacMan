package g11.model.elements;

import g11.model.Orientation;
import g11.model.Position;

/**
 * Pink Elements.Ghost
 * Personality : Try to position themselves in front of PacMan
 */
public class Pinky extends Ghost{

    public Pinky(int x, int y, Position scatterTarget) {
        super(x,y, scatterTarget, Orientation.UP);
    }
}