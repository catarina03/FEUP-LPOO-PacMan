package g11.controller.ghosts.states;

import g11.controller.ghosts.GhostController;
import g11.controller.ghosts.GhostState;
import g11.controller.ghosts.TargetStrategy;
import g11.model.GameData;
import g11.model.OrientationEnumeration;

import java.util.ArrayList;

public class GhostStateEaten extends GhostState {
    public GhostStateEaten(GhostController ghostController, TargetStrategy targetStrategy, int powerPellets) {
        super(ghostController, targetStrategy, powerPellets);
    }

    @Override
    public void update(GameData gameData, int step, long elapsedTime) {
        if (ghostController.getGhost().getPosition().equals(gameData.getMap().getGates().get(0).getPosition().up()) || ghostController.getGhost().getPosition().equals(gameData.getMap().getGates().get(1).getPosition().up())) {
            ghostController.changeState(new GhostStateEnteringHouse(ghostController, ghostController.getTargetStrategy(), activePPs));
            ghostController.setAccessingHouse(true);
        }

        if (gameData.getMap().getPowerPellets().size() != activePPs)
            activePPs--;

        if (ghostController.isChangeOrientation())
            ghostController.setChangeOrientation(false);
    }

    @Override
    public void calculateAndStep(GameData gameData, int step) {
        ArrayList<OrientationEnumeration> availableOris;
        if (step % 3 == 0) {
            ghostController.getGhost().setTarget(gameData.getMap().getGates().get(0).getPosition().up());
            availableOris = ghostController.getAvailableOrientations(gameData);
            if (availableOris.size() > 0) {
                ghostController.getGhost().setOrientationEnumeration(ghostController.chooseOrientation(availableOris));
            }
            ghostController.getGhost().moveDirection();
        }
    }
}
