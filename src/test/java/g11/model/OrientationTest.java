package g11.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrientationTest {
    @Test
    public void OrientationValuesTest() {
        OrientationENUM o1 = OrientationENUM.UP;
        OrientationENUM o2 = OrientationENUM.RIGHT;
        OrientationENUM o3 = OrientationENUM.LEFT;
        OrientationENUM o4 = OrientationENUM.DOWN;

        assertEquals(o1, OrientationENUM.UP);
        assertEquals(o2, OrientationENUM.RIGHT);
        assertEquals(o3, OrientationENUM.LEFT);
        assertEquals(o4, OrientationENUM.DOWN);

        assertEquals(o1.getOpposite(), o4);
        assertEquals(o2.getOpposite(), o3);
        assertEquals(o3.getOpposite(), o2);
        assertEquals(o4.getOpposite(), o1);
    }
}
