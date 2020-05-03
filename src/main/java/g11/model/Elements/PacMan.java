package g11.model.Elements;

import g11.model.Orientation;
import g11.model.Position;

public class PacMan extends Moveable{
    public PacMan(int x, int y) {
        super(x,y);
        this.orientation = Orientation.LEFT;
    }

    public PacMan(Position position) {
        super(position);
        this.orientation = Orientation.LEFT;
    }

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
