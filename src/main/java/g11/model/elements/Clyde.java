package g11.model.elements;

import g11.model.OrientationENUM;
import g11.model.Position;
/**
 * Orange Elements.Ghost
 * Personality : Switching between direct Chase to Elements.PacMan and fleeing
 */
public class Clyde extends Ghost {
    public Clyde(Position ghostPosition, Position scatterTarget) {
        super(ghostPosition, scatterTarget, OrientationENUM.UP);
    }

}