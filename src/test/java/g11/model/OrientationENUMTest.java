package g11.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrientationENUMTest {
    @Test
    public void OrientationValuesTest() {
        OrientationEnumeration o1 = OrientationEnumeration.UP;
        OrientationEnumeration o2 = OrientationEnumeration.RIGHT;
        OrientationEnumeration o3 = OrientationEnumeration.LEFT;
        OrientationEnumeration o4 = OrientationEnumeration.DOWN;

        assertEquals(o1, OrientationEnumeration.UP);
        assertEquals(o2, OrientationEnumeration.RIGHT);
        assertEquals(o3, OrientationEnumeration.LEFT);
        assertEquals(o4, OrientationEnumeration.DOWN);
    }
}
