package g11.model.Elements;

import g11.model.Position;

public abstract class Ghost extends Moveable {
    Position target;
    Position scatterTarget;

    public Ghost(int x, int y, Position scatterTarget) {
        super(x, y);
        this.scatterTarget = scatterTarget;
        this.target = new Position(0,0);
    }

    public Position getTarget() {
        return target;
    }

    public void setTarget(Position target) {
        this.target = target;
    }

    public Position getScatterTarget() { return scatterTarget; }
}
