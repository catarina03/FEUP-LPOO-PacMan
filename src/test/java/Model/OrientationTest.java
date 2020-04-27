package Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrientationTest {
    @Test
    public void OrientationValuesTest() {
        Orientation o1 = Orientation.UP;
        Orientation o2 = Orientation.RIGHT;
        Orientation o3 = Orientation.LEFT;
        Orientation o4 = Orientation.DOWN;

        assertEquals(o1, Orientation.UP);
        assertEquals(o2, Orientation.RIGHT);
        assertEquals(o3, Orientation.LEFT);
        assertEquals(o4, Orientation.DOWN);
    }
}
