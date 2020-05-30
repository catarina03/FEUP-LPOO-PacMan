package g11.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class OrientationEnumerationTest {
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

        assertEquals(o1.getOpposite(), OrientationEnumeration.DOWN);
        assertEquals(o2.getOpposite(), OrientationEnumeration.LEFT);
        assertEquals(o3.getOpposite(), OrientationEnumeration.RIGHT);
        assertEquals(o4.getOpposite(), OrientationEnumeration.UP);

        ArrayList<OrientationEnumeration> all = new ArrayList<>();
        all.add(OrientationEnumeration.UP);
        all.add(OrientationEnumeration.DOWN);
        all.add(OrientationEnumeration.LEFT);
        all.add(OrientationEnumeration.RIGHT);

        assertEquals(OrientationEnumeration.allOptions(), all);
    }
}
