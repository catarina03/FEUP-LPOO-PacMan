package g11.model.Elements;

import g11.model.Position;

public abstract class MapComponent {
    private Position position;

    public MapComponent(int x, int y) {
        this.position = new Position(x, y);
    }

    public MapComponent(Position position) {
        this.position = position;
    }

    public int getX() {
        return position.getX();
    }

    public void setX(int x) { this.position = new Position(x, this.position.getY()); }

    public int getY() {
        return position.getY();
    }

    public void setY(int y) {
        this.position = new Position(this.position.getX(),y );
    }

    public Position getPosition() { return position; }

    public void setPosition(Position position) { this.position = position; }
}
