package g11.model;

import java.util.ArrayList;
import java.util.Arrays;

public enum OrientationENUM {
    UP, DOWN, LEFT, RIGHT;

    public OrientationENUM getOpposite() {
        if (this == OrientationENUM.UP)
            return OrientationENUM.DOWN;
        if (this == OrientationENUM.DOWN)
            return OrientationENUM.UP;
        if (this == OrientationENUM.LEFT)
            return OrientationENUM.RIGHT;
        if (this == OrientationENUM.RIGHT)
            return OrientationENUM.LEFT;
        return null;
    }

    public static ArrayList<OrientationENUM> allOptions() {
        return new ArrayList<>(Arrays.asList(UP, DOWN, LEFT, RIGHT));
    }
}

