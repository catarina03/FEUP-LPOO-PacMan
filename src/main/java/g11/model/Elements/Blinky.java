package g11.model.Elements;

import g11.model.GhostState;
import g11.model.Position;

/**
 * Red Elements.Ghost
 * Personality : Direct Chase to Elements.PacMan
 */
public class Blinky extends Ghost {
    public Blinky(int x, int y) {
        super(x,y, new Position(45, 2));
    }
}