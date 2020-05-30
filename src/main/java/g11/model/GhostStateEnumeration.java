package g11.model;

import java.util.ArrayList;
import java.util.Arrays;

public enum GhostStateEnumeration {
    CHASE, SCATTER, FRIGHTENED, EATEN, ENTERINGHOUSE;

    public ArrayList<GhostStateEnumeration> allOptions() {
        return new ArrayList<>(Arrays.asList(CHASE, SCATTER, FRIGHTENED, EATEN, ENTERINGHOUSE));
    }
}
