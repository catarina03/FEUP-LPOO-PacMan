package g11.controller;

import g11.model.GameData;
import g11.model.GhostStateENUM;
import g11.model.Position;
import g11.model.elements.Ghost;

public class GhostControllerClyde extends GhostController {
    public GhostControllerClyde(Ghost ghost) {
        super(true, ghost);
    }

    public void update(GameData gameData, long elapsedtime, int step, GhostStateENUM ghostStateENUM) {
        determineState(elapsedtime, ghostStateENUM);
        if (elapsedtime > 10000) {
            stateSwitch(gameData, step);
        }
    }

    @Override
    public Position getTarget(GameData gameData) {
        return gameData.getPacMan().getPosition().distance(gameData.getGhosts().get(3).getPosition()) >= 8 ? gameData.getPacMan().getPosition() : gameData.getGhosts().get(3).getScatterTarget();
    }
}
