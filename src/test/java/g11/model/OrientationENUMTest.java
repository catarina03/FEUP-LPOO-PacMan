package g11.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrientationENUMTest {
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
    }
}
