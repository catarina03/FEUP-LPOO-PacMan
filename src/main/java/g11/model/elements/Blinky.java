package g11.model.elements;

import g11.model.OrientationENUM;
import g11.model.Position;

/**
 * Red Elements.Ghost
 * Personality : Direct Chase to Elements.PacMan
 */
public class Blinky extends Ghost {
    public Blinky(int x, int y, Position scatterTarget) {
        super(x, y, scatterTarget, OrientationENUM.LEFT);
    }
}