package Model.Elements;

import Model.Orientation;
import com.googlecode.lanterna.input.KeyStroke;

public class PacMan extends Moveable{
    Orientation orientation;

    public PacMan(int x, int y) {
        super(x,y);
        this.orientation = Orientation.LEFT;
    }

    public Orientation getDirection() {
        return orientation;
    }

    public void setDirection(Orientation or) {
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

    public void processKey(KeyStroke key){
        switch (key.getKeyType()){
            case ArrowUp:
                this.setDirection(Orientation.UP);
                break;
            case ArrowDown:
                this.setDirection(Orientation.DOWN);
                break;
            case ArrowLeft:
                this.setDirection(Orientation.LEFT);
                break;
            case ArrowRight:
                this.setDirection(Orientation.RIGHT);
                break;
            default:
                break;
        }
    }

}
