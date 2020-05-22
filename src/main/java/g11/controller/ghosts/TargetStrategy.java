package g11.controller.ghosts;

import g11.model.GameData;
import g11.model.Position;

public interface TargetStrategy {
    Position getTarget(GameData gameData);
}
