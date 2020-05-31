package g11.model.elements.ghosts;

import g11.model.OrientationEnumeration;
import g11.model.Position;
import g11.model.elements.Ghost;

/**
 * Pink Elements.Ghost
 * Personality : Try to position themselves in front of PacMan
 */
public class Pinky extends Ghost {
    public Pinky(Position ghostPosition, Position scatterTarget) {
        super(ghostPosition, scatterTarget, OrientationEnumeration.UP);
    }
}