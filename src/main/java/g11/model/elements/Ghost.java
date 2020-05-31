package g11.model.elements;

import g11.model.GhostStateEnumeration;
import g11.model.OrientationEnumeration;
import g11.model.Position;

public abstract class Ghost extends Moveable {
    private Position target;
    private GhostStateEnumeration state;
    private final Position scatterTarget;

    public Ghost(Position ghostPosition, Position scatterTarget, OrientationEnumeration orientationEnumeration) {
        super(ghostPosition);
        this.scatterTarget = scatterTarget;
        this.orientationEnumeration = orientationEnumeration;
        this.target = new Position(0, 0);
        this.state = GhostStateEnumeration.CHASE;
    }

    public Position getTarget() {
        return target;
    }

    public void setTarget(Position target) {
        this.target = target;
    }

    public Position getScatterTarget() { return scatterTarget; }

    public GhostStateEnumeration getState() {
        return state;
    }

    public void setState(GhostStateEnumeration state) {
        this.state = state;
    }
}
