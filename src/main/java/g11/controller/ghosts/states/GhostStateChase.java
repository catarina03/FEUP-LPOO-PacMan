package g11.controller.ghosts.states;

import g11.controller.ghosts.GhostController;
import g11.controller.ghosts.GhostState;
import g11.controller.ghosts.TargetStrategy;
import g11.model.GameData;
import g11.model.GhostStateEnumeration;
import g11.model.OrientationEnumeration;

import java.util.ArrayList;

public class GhostStateChase extends GhostState {
    public GhostStateChase(GhostController ghostController, TargetStrategy targetStrategy, int powerPellets) {
        super(ghostController, targetStrategy, powerPellets);
    }

    @Override
    public void update(GameData gameData, int step, long elapsedTime) {
        if (gameData.getMap().getPowerPellets().size() != activePPs) {
            activePPs--;
            ghostController.getGhost().setState(GhostStateEnumeration.FRIGHTENED);
            ghostController.setChangeOrientation(true);
            ghostController.changeState(new GhostStateFrightened(ghostController, targetStrategy, activePPs));
        }
        if (isScatterTime(elapsedTime)) {
            ghostController.setChangeOrientation(true);
            ghostController.changeState(new GhostStateScatter(ghostController, targetStrategy, activePPs));
        }
    }


    @Override
    public void calculateAndStep(GameData gameData, int step) {
        ArrayList<OrientationEnumeration> availableOris;
        if (step % 4 == 0) {
            ghostController.getGhost().setTarget(targetStrategy.getTarget(gameData));
            if (ghostController.isChangeOrientation()) {
                ghostController.getGhost().setOrientationEnumeration(ghostController.getGhost().getOrientationEnumeration().getOpposite());
                ghostController.setChangeOrientation(false);
            } else {
                availableOris = ghostController.getAvailableOrientations(gameData);
                if (availableOris.size() > 0) {
                    ghostController.getGhost().setOrientationEnumeration(ghostController.chooseOrientation(availableOris));
                }
            }
            ghostController.getGhost().moveDirection();
        }
    }
}
