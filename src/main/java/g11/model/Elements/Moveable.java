package g11.model.Elements;

import g11.model.Orientation;
import g11.model.Position;

public abstract class Moveable extends MapComponent {
    Orientation orientation;

    public Moveable(int x, int y) {
        super(x, y);
    }

    public Moveable(Position position) { super(position); }
}
