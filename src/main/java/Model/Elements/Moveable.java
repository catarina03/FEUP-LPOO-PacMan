package Model.Elements;

import Model.Position;

public abstract class Moveable extends MapComponent {
    public Moveable(int x, int y) {
        super(x, y);
    }

    public Moveable(Position position) { super(position); }
}
