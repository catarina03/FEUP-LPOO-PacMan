package g11.controller.ghosts;

import g11.model.GameData;
import g11.model.OrientationENUM;
import g11.model.Position;

import java.util.ArrayList;

public class GhostStateExitingHouse extends GhostState {
    public GhostStateExitingHouse(GhostController ghostController, TargetStrategy targetStrategy, int powerPellets) {
        super(ghostController, targetStrategy, powerPellets);
    }

    @Override
    void update(GameData gameData, int step, long elapsedTime) {
        if (ghostController.getGhost().getPosition().equals(gameData.getMap().getGates().get(0).getPosition().up())) {
            ghostController.setAccessingHouse(false);
            if (isScatterTime(elapsedTime))
                ghostController.changeState(new GhostStateScatter(ghostController, ghostController.getTargetStrategy(), activePPs));
            else
                ghostController.changeState(new GhostStateChase(ghostController, ghostController.getTargetStrategy(), activePPs));
        }
        if (gameData.getMap().getPowerPellets().size() != activePPs)
            activePPs--;
    }

    @Override
    void calculateAndStep(GameData gameData, int step) {
        ArrayList<OrientationENUM> availableOris;
        if (step % 4 == 0) {
            ghostController.getGhost().setTarget(gameData.getMap().getGates().get(0).getPosition().up());
            availableOris = ghostController.getAvailableOrientations(gameData);
            if (availableOris.size() > 0) {
                ghostController.getGhost().setOrientationENUM(ghostController.chooseOrientation(availableOris));
            }
            ghostController.getGhost().moveDirection();
        }
    }
}
