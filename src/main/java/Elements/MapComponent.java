package Elements;

import Others.Position;

public abstract class MapComponent implements Drawable {
    private Position position;

    public MapComponent(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
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
