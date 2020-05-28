package g11.controller.ghosts;

import g11.model.GameData;
import g11.model.OrientationENUM;
import g11.model.Position;

import java.util.ArrayList;

public class GhostStateEaten extends GhostState {
    public GhostStateEaten(GhostController ghostController, TargetStrategy targetStrategy, int powerPellets) {
        super(ghostController, targetStrategy, powerPellets);
    }

    @Override
    void update(GameData gameData, int step, long elapsedTime) {
        if (ghostController.getGhost().getPosition().equals(gameData.getMap().getGates().get(0).getPosition().up()) ||
                ghostController.getGhost().getPosition().equals(gameData.getMap().getGates().get(1).getPosition().up())) {
            ghostController.changeState(new GhostStateEnteringHouse(ghostController, ghostController.getTargetStrategy(), activePPs));
            ghostController.setAccessingHouse(true);
        }

        if (gameData.getMap().getPowerPellets().size() != activePPs)
            activePPs--;

        if (ghostController.isChangeOrientation())
            ghostController.setChangeOrientation(false);
    }

    @Override
    void calculateAndStep(GameData gameData, int step) {
        ArrayList<OrientationENUM> availableOris;
        if (step % 3 == 0) {
            ghostController.getGhost().setTarget(gameData.getMap().getGates().get(0).getPosition().up());
            availableOris = ghostController.getAvailableOrientations(gameData);
            if (availableOris.size() > 0) {
                ghostController.getGhost().setOrientationENUM(ghostController.chooseOrientation(availableOris));
            }
            ghostController.getGhost().moveDirection();
        }
    }
}
