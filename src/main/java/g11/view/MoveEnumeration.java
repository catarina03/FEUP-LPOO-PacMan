package g11.view;

import java.util.ArrayList;
import java.util.Arrays;

public enum MoveEnumeration {
    UP, DOWN, LEFT, RIGHT, ESC;

    public ArrayList<MoveEnumeration> allOptions() {
        return new ArrayList<>(Arrays.asList(UP, DOWN, LEFT, RIGHT, ESC));
    }
}