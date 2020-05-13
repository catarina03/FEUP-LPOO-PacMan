package g11.model.Elements;

import g11.model.Position;

/**
 * Blue Elements.Ghost
 * Personality : Try to position themselves in front of PacMan
 */
public class Inky extends Ghost{
    public Inky(int x, int y) {
        super(x,y, new Position(49, 34));
    }

}