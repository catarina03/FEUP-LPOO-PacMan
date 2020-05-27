package g11.model.elements;

import g11.model.OrientationENUM;
import g11.model.Position;

/**
 * Blue Elements.Ghost
 * Personality : Try to position themselves in front of PacMan
 */
public class Inky extends Ghost {
    public Inky(Position ghostPosition, Position scatterTarget) {
        super(ghostPosition, scatterTarget, OrientationENUM.UP);
    }
}