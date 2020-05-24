package g11.model.elements;

import g11.model.OrientationENUM;
import g11.model.Position;

public class PacMan extends Moveable{
    public PacMan(int x, int y) {
        super(x, y);
        this.orientationENUM = OrientationENUM.LEFT;
    }

    public PacMan(Position position) {
        super(position);
        this.orientationENUM = OrientationENUM.LEFT;
    }
}
