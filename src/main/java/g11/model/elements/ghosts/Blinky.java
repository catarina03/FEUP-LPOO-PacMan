package g11.model.elements.ghosts;

import g11.model.OrientationEnumeration;
import g11.model.Position;
import g11.model.elements.Ghost;

/**
 * Red Elements.Ghost
 * Personality : Direct Chase to Elements.PacMan
 */
public class Blinky extends Ghost {
    public Blinky(Position ghostPosition, Position scatterTarget) {
        super(ghostPosition, scatterTarget, OrientationEnumeration.LEFT);
    }
}