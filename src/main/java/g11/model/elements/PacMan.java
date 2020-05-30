package g11.model.elements;

import g11.model.OrientationEnumeration;
import g11.model.Position;

public class PacMan extends Moveable{
    public PacMan(int x, int y) {
        super(x, y);
        this.orientationEnumeration = OrientationEnumeration.LEFT;
    }

    public PacMan(Position position) {
        super(position);
        this.orientationEnumeration = OrientationEnumeration.LEFT;
    }
}
