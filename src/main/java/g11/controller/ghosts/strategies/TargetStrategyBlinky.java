package g11.controller.ghosts.strategies;

import g11.controller.ghosts.TargetStrategy;
import g11.model.GameData;
import g11.model.Position;

public class TargetStrategyBlinky implements TargetStrategy {
    @Override
    public Position getTarget(GameData gameData) {
        return gameData.getPacMan().getPosition();
    }
}
