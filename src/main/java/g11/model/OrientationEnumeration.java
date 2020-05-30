package g11.model;

import java.util.ArrayList;
import java.util.Arrays;

public enum OrientationEnumeration {
    UP, DOWN, LEFT, RIGHT;

    public OrientationEnumeration getOpposite() {
        if (this == OrientationEnumeration.UP)
            return OrientationEnumeration.DOWN;
        if (this == OrientationEnumeration.DOWN)
            return OrientationEnumeration.UP;
        if (this == OrientationEnumeration.LEFT)
            return OrientationEnumeration.RIGHT;
        if (this == OrientationEnumeration.RIGHT)
            return OrientationEnumeration.LEFT;
        return null;
    }

    public static ArrayList<OrientationEnumeration> allOptions() {
        return new ArrayList<>(Arrays.asList(UP, DOWN, LEFT, RIGHT));
    }
}

