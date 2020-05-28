package g11.model.elements;

import g11.model.OrientationENUM;
import g11.model.Position;

import java.util.ArrayList;

public abstract class Moveable extends MapComponent {
    public static int xValueTP;
    OrientationENUM orientationENUM;

    public Moveable(int x, int y) {
        super(x, y);
        this.orientationENUM = OrientationENUM.LEFT;
    }

    public Moveable(Position position) {
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
        setX((getX() + 1 > xValueTP) ? 0 : getX() + 1);
    }

    public void moveLeft() {
        setX((getX() - 1 < 0) ? xValueTP : getX() - 1);
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
