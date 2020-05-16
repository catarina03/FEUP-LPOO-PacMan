package g11.model.elements;

import g11.model.Orientation;
import g11.model.Position;
/**
 * Orange Elements.Ghost
 * Personality : Switching between direct Chase to Elements.PacMan and fleeing
 */
public class Clyde extends Ghost{
    public Clyde(int x, int y, Position scatterTarget) {
        super(x,y, scatterTarget, Orientation.UP);
    }

}