package g11.model.Elements;

import g11.model.Position;

/**
 * Pink Elements.Ghost
 * Personality : Try to position themselves in front of PacMan
 */
public class Pinky extends Ghost{

    public Pinky(int x, int y) {
        super(x,y, new Position(3,2));
    }

}