package g11.controller;

import g11.model.Position;
import g11.model.elements.Ghost;
import g11.model.GameData;
import g11.model.GhostStateENUM;

public class GhostControllerBlinky extends GhostController {
    public GhostControllerBlinky(Ghost ghost) {
        super(false, ghost);
    }

    public void update(GameData gameData, long elapsedtime, int step, GhostStateENUM ghostStateENUM) {
        determineState(elapsedtime, ghostStateENUM);
        if (elapsedtime > 0) {
            stateSwitch(gameData, step);
        }
    }

    @Override
    public Position getTarget(GameData gameData) {
        return gameData.getPacMan().getPosition();
    }

}
