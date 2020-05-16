package g11.model;

import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.geometry.Pos;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position left() {
        return new Position(x - 1, y);
    }

    public Position right() {
        return new Position(x + 1, y);
    }

    public Position up() {
        return new Position(x, y - 1);
    }

    public Position down() {
        return new Position(x, y + 1);
    }

    public double distance(Position a){
        return Math.sqrt( Math.pow(this.x - a.getX(),2) + Math.pow(this.y - a.getY(),2) );
    }

    public Position nextPositionWithOrientation(Orientation orientation){
        switch (orientation){
            case UP:
                return new Position(this.up());
            case DOWN:
                return new Position(this.down());
            case RIGHT:
                return new Position(this.right());
            case LEFT:
                return new Position(this.left());
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
