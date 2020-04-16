package Model;

import Others.Position;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PositionTest {

    /*
    * Random r = new Random();
	* return r.nextInt((max - min) + 1) + min;
    */
    @Test
    public void PositionValuesCreation() {
        Random r = new Random();
        int x = r.nextInt((20) + 1);
        int y = r.nextInt((20) + 1);

        Position position = new Position(x,y);
        assertEquals(x, position.getX());
        assertEquals(y, position.getY());
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

        assertEquals(positionup, position.up());
        assertEquals(positiondown, position.down());
        assertEquals(positionleft, position.left());
        assertEquals(positionright, position.right());

        assertEquals(true, position.up().equals(positionup));
        assertEquals(true, position.down().equals(positiondown));
        assertEquals(true, position.left().equals(positionleft));
        assertEquals(true, position.right().equals(positionright));
    }

    @Test
    public void PositionHash() {
        Random r = new Random();
        int x = r.nextInt((20) + 1);
        int y = r.nextInt((20) + 1);

        Position position1 = new Position(x,y);
        Position position2 = new Position(x,y);

        assertEquals(position1, position2);
        assertEquals(true, position1.equals(position2));

        assertEquals(position1.hashCode(), position2.hashCode());
    }
}
