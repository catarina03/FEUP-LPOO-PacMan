package g11.view;

import java.util.ArrayList;
import java.util.Arrays;

public enum MoveENUM {
    UP, DOWN, LEFT, RIGHT, ESC;

    public ArrayList<MoveENUM> allOptions() {
        return new ArrayList<>(Arrays.asList(UP, DOWN, LEFT, RIGHT, ESC));
    }
}