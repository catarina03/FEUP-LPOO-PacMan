package g11.model.elements;

import g11.model.OrientationENUM;
import g11.model.Position;

/**
 * Pink Elements.Ghost
 * Personality : Try to position themselves in front of PacMan
 */
public class Pinky extends Ghost {

    public Pinky(Position ghostPosition, Position scatterTarget) {
        super(ghostPosition, scatterTarget, OrientationENUM.UP);
    }
}