package g11.model.elements.ghosts;

import g11.model.OrientationEnumeration;
import g11.model.Position;
import g11.model.elements.Ghost;

/**
 * Orange Elements.Ghost
 * Personality : Switching between direct Chase to Elements.PacMan and fleeing
 */
public class Clyde extends Ghost {
    public Clyde(Position ghostPosition, Position scatterTarget) {
        super(ghostPosition, scatterTarget, OrientationEnumeration.UP);
    }

}