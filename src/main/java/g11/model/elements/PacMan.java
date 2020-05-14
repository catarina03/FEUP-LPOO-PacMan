package g11.model.elements;

import g11.model.Orientation;
import g11.model.Position;

public class PacMan extends Moveable{
    public PacMan(int x, int y) {
        super(x,y);
        this.orientation = Orientation.LEFT;
    }

    public PacMan(Position position) {
        super(position);
        this.orientation = Orientation.LEFT;
    }

}
