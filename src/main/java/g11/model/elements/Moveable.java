package g11.model.elements;

import g11.model.Orientation;
import g11.model.Position;

public abstract class Moveable extends MapComponent {
    Orientation orientation;

    public Moveable(int x, int y) {
        super(x, y);
        this.orientation = Orientation.LEFT;
    }

    public Moveable(int x, int y, Orientation orientation) {
        super(x, y);
        this.orientation = orientation;
    }

    public Moveable(Position position) { super(position); this.orientation = Orientation.LEFT;}

    public Moveable(Position position, Orientation orientation) { super(position); this.orientation = Orientation.LEFT; }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation or) {
        this.orientation = or;
    }

    public void moveUp(){ setY(getY()-1); }

    public void moveDown(){
        setY(getY()+1);
    }

    public void moveRight(){
        setX(getX()+1);
    }

    public void moveLeft(){
        setX(getX()-1);
    }

    public void moveDirection(){
        switch (orientation){
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
