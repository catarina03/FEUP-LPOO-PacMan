package g11.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static g11.model.OrientationENUM.RIGHT;
import static g11.model.OrientationENUM.UP;
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

        assertEquals(o1.getOpposite(), OrientationENUM.DOWN);
        assertEquals(o2.getOpposite(), OrientationENUM.LEFT);
        assertEquals(o3.getOpposite(), OrientationENUM.RIGHT);
        assertEquals(o4.getOpposite(), OrientationENUM.UP);

        ArrayList<OrientationENUM> all = new ArrayList<>();
        all.add(OrientationENUM.UP);
        all.add(OrientationENUM.DOWN);
        all.add(OrientationENUM.LEFT);
        all.add(OrientationENUM.RIGHT);

        assertEquals(OrientationENUM.allOptions(), all);
    }
}
