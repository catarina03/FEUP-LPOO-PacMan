package g11.model.elements;

import g11.model.OrientationEnumeration;
import g11.model.Position;

public abstract class Moveable extends MapComponent {
    public static int xValueTP;
    OrientationEnumeration orientationEnumeration;

    public Moveable(int x, int y) {
        super(x, y);
        this.orientationEnumeration = OrientationEnumeration.LEFT;
    }

    public Moveable(Position position) {
        super(position);
        this.orientationEnumeration = OrientationEnumeration.LEFT;
    }

    public OrientationEnumeration getOrientationEnumeration() {
        return orientationEnumeration;
    }

    public void setOrientationEnumeration(OrientationEnumeration or) {
        this.orientationEnumeration = or;
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
        switch (orientationEnumeration) {
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
