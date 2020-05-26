package g11.model.elements;

import g11.model.OrientationENUM;
import g11.model.Position;

public abstract class Moveable extends MapComponent {
    OrientationENUM orientationENUM;

    public Moveable(int x, int y) {
        super(x, y);
        this.orientationENUM = OrientationENUM.LEFT;
    }

    public Moveable(int x, int y, OrientationENUM orientationENUM) {
        super(x, y);
        this.orientationENUM = orientationENUM;
    }

    public Moveable(Position position) {
        super(position);
        this.orientationENUM = OrientationENUM.LEFT;
    }

    public Moveable(Position position, OrientationENUM orientationENUM) {
        super(position);
        this.orientationENUM = OrientationENUM.LEFT;
    }

    public OrientationENUM getOrientationENUM() {
        return orientationENUM;
    }

    public void setOrientationENUM(OrientationENUM or) {
        this.orientationENUM = or;
    }

    public void moveUp() {
        setY((getY() - 1 < 3) ? 33 : getY() - 1);
    }

    public void moveDown() {
        setY((getY() + 1 - 3) % 31 + 3);
    }

    public void moveRight() {
        setX((getX() + 1) % 28);
    }

    public void moveLeft() {
        setX((getX() - 1 < 0) ? 27 : getX() - 1);
    }

    public void moveDirection() {
        switch (orientationENUM) {
            case UP:
                moveUp();
                break;
            case RIGHT:
                moveRight();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
        }
    }

}
