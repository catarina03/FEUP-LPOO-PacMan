package Model;

import Others.Position;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

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

        assertTrue(position.up().equals(positionup));
        assertTrue(position.down().equals(positiondown));
        assertTrue(position.left().equals(positionleft));
        assertTrue(position.right().equals(positionright));
    }

    @Test
    public void PositionHash() {
        Random r = new Random();
        int x = r.nextInt((20) + 1);
        int y = r.nextInt((20) + 1);

        Position position1 = new Position(x,y);
        Position position2 = new Position(x,y);

        assertEquals(position1, position2);
        assertTrue(position1.equals(position2));

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


        assertFalse(position1.equals(position2));
        assertNotEquals(position1, position2);

        assertTrue(position1.equals(position3));
        assertEquals(position1, position3);
    }
}
