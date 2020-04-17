package Elements;

import Others.Position;

public abstract class MapComponent {
    private Position position;

    public MapComponent(int x, int y) {
        Position position = new Position(x, y);
        this.position = position;
    }

    public MapComponent(Position position) {
        this.position = position;
    }

    public int getX() {
        return position.getX();
    }

    public void setX(int x) { this.position.setX(x); }

    public int getY() {
        return position.getY();
    }

    public void setY(int y) {
        this.position.setY(y);
    }

    public Position getPosition() { return position; }

    public void setPosition(Position position) { this.position = position; }
}
