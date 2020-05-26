package g11.model;

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

        assertEquals(o1.getOpposite(), o4);
        assertEquals(o2.getOpposite(), o3);
        assertEquals(o3.getOpposite(), o2);
        assertEquals(o4.getOpposite(), o1);
    }
}
