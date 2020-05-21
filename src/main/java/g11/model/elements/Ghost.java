package g11.model.elements;

import g11.model.GhostState;
import g11.model.Orientation;
import g11.model.Position;

public abstract class Ghost extends Moveable {
    private Position target;
    private GhostState state;
    private Position scatterTarget;


    public Ghost(int x, int y, Position scatterTarget, Orientation orientation) {
        super(x, y);
        this.scatterTarget = scatterTarget;
        this.orientation = orientation;
        this.target = new Position(0,0);
        this.state = GhostState.CHASE;
    }

    public Position getTarget() {
        return target;
    }

    public void setTarget(Position target) {
        this.target = target;
    }

    public Position getScatterTarget() { return scatterTarget; }

    public GhostState getState() { return state; }

    public void setState(GhostState state) { this.state = state; }
}
