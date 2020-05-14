package g11.model.elements;

import g11.model.Position;

public class Gate extends MapComponent{
    public Gate(int x, int y) {
        super(x, y);
    }

    public Gate(Position position) {
        super(position);
    }
}
