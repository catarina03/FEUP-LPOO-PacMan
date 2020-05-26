package g11.controller.ghosts;

import g11.model.GameData;
import g11.model.GhostStateENUM;
import g11.model.OrientationENUM;
import g11.model.Position;

import java.util.ArrayList;

public class GhostStateEnteringHouse extends GhostState {
    public GhostStateEnteringHouse(GhostController ghostController, TargetStrategy targetStrategy, int powerPellets) {
        super(ghostController, targetStrategy, powerPellets);
    }

    @Override
    void update(GameData gameData, int step, long elapsedTime) {
        if (ghostController.getGhost().getPosition().equals(new Position(13, 17))) {
            ghostController.changeState(new GhostStateExitingHouse(ghostController, ghostController.getTargetStrategy(), activePPs));
            ghostController.getGhost().setState(GhostStateENUM.CHASE);
        }
        if (gameData.getMap().getPowerPellets().size() != activePPs)
            activePPs--;
    }

    @Override
    void calculateAndStep(GameData gameData, int step) {
        ArrayList<OrientationENUM> availableOris;
        if (step % 3 == 0) {
            ghostController.getGhost().setTarget(new Position(13, 17));
            availableOris = ghostController.getAvailableOrientations(gameData);
            if (availableOris.size() > 0)
                ghostController.getGhost().setOrientationENUM(ghostController.chooseOrientation(availableOris));
            ghostController.getGhost().moveDirection();
        }
    }
}
