package g11.model;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void PositionValuesCreation() {
        Random r = new Random();
        int x = r.nextInt((20) + 1);
        int y = r.nextInt((20) + 1);

        Position position = new Position(x,y);
        assertEquals(position.getX(), x);
        assertEquals(position.getY(), y);
    }

    @Test
    public void PositionChange() {
        Random r = new Random();
        int x = r.nextInt((20) + 1);
        int y = r.nextInt((20) + 1);

        Position position = new Position(x,y);
        Position positionup = new Position(x,y-1);
        Position positiondown = new Position(x,y+1);
        Position positionleft = new Position(x-1,y);
        Position positionright = new Position(x+1,y);
        Position positionChange = new Position(x, y);

        int new_x = r.nextInt((20) + 1);
        int new_y = r.nextInt((20) + 1);

        positionChange.setX(new_x);
        positionChange.setY(new_y);

        assertEquals(positionup, position.up());
        assertEquals(positiondown, position.down());
        assertEquals(positionleft, position.left());
        assertEquals(positionright, position.right());
        assertEquals(positionChange.getX(), new_x);
        assertEquals(positionChange.getY(), new_y);

        Orientation orientation = Orientation.UP;
        assertEquals(position.nextPositionWithOrientation(orientation), positionup);
        orientation = Orientation.DOWN;
        assertEquals(position.nextPositionWithOrientation(orientation), positiondown);
        orientation = Orientation.RIGHT;
        assertEquals(position.nextPositionWithOrientation(orientation), positionright);
        orientation = Orientation.LEFT;
        assertEquals(position.nextPositionWithOrientation(orientation), positionleft);
    }

    @Test
    public void PositionHash() {
        Random r = new Random();
        int x = r.nextInt((20) + 1);
        int y = r.nextInt((20) + 1);

        Position position1 = new Position(x,y);
        Position position2 = new Position(x,y);

        assertEquals(position1, position2);

        assertEquals(position1.hashCode(), position2.hashCode());
    }

    @Test
    public void PositionEquals() {
        Random r = new Random();
        int x = r.nextInt((20) + 1);
        int y = r.nextInt((20) + 1);
        int xx = r.nextInt((40-21) + 1) + 21;
        int yy = r.nextInt((40-21) + 1) + 21;

        Position position1 = new Position(x,y);
        Position position2 = new Position(xx,yy);
        Position position3 = new Position(x,y);

        assertNotEquals(position1, position2);
        assertEquals(position1, position3);
        assertEquals(position1.distance(position3), position3.distance(position1), 0.0);
    }
}
