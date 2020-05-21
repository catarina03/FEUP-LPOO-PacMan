package g11.model.elements;

import g11.model.GhostStateENUM;
import g11.model.Orientation;
import g11.model.Position;

public abstract class Ghost extends Moveable {
    private Position target;
    private GhostStateENUM state;
    private Position scatterTarget;


    public Ghost(int x, int y, Position scatterTarget, Orientation orientation) {
        super(x, y);
        this.scatterTarget = scatterTarget;
        this.orientation = orientation;
        this.target = new Position(0, 0);
        this.state = GhostStateENUM.CHASE;
    }

    public Position getTarget() {
        return target;
    }

    public void setTarget(Position target) {
        this.target = target;
    }

    public Position getScatterTarget() { return scatterTarget; }

    public GhostStateENUM getState() {
        return state;
    }

    public void setState(GhostStateENUM state) {
        this.state = state;
    }
}
