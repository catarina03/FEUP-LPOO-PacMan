package g11.controller.ghosts.strategies;

import g11.controller.ghosts.TargetStrategy;
import g11.model.GameData;
import g11.model.Position;

public class TargetStrategyClyde implements TargetStrategy {
    @Override
    public Position getTarget(GameData gameData) {
        return gameData.getPacMan().getPosition().distance(gameData.getGhosts().get(3).getPosition()) >= 8 ?
                gameData.getPacMan().getPosition() :
                gameData.getGhosts().get(3).getScatterTarget();
    }
}
