package g11.model;

import java.util.ArrayList;
import java.util.Arrays;

public enum GhostStateENUM {
    CHASE, SCATTER, FRIGHTENED, EATEN, ENTERINGHOUSE;

    public ArrayList<GhostStateENUM> allOptions() {
        return new ArrayList<>(Arrays.asList(CHASE, SCATTER, FRIGHTENED, EATEN, ENTERINGHOUSE));
    }
}
