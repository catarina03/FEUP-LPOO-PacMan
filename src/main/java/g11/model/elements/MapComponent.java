package g11.model.elements;

import g11.model.OrientationENUM;
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

    public void setX(int x) {
        this.position = new Position(x, this.position.getY());
    }

    public int getY() {
        return position.getY();
    }

    public void setY(int y) {
        this.position = new Position(this.position.getX(), y);
    }

    public Position getPosition() {
        return position;
    }

    public Position getPosition(OrientationENUM ori) {
        switch (ori) {
            case UP:
                return position.up();
            case DOWN:
                return position.down();
            case LEFT:
                return position.left();
            case RIGHT:
                return position.right();
            default:
                return position;
        }
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
