package g11.model.elements;

import g11.model.OrientationENUM;
import g11.model.Position;

/**
 * Red Elements.Ghost
 * Personality : Direct Chase to Elements.PacMan
 */
public class Blinky extends Ghost {
    public Blinky(Position ghostPosition, Position scatterTarget) {
        super(ghostPosition, scatterTarget, OrientationENUM.LEFT);
    }
}