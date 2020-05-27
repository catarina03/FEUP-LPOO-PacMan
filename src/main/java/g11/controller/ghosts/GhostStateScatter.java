package g11.controller.ghosts;

import g11.model.GameData;
import g11.model.GhostStateENUM;
import g11.model.OrientationENUM;

import java.util.ArrayList;

public class GhostStateScatter extends GhostState {
    public GhostStateScatter(GhostController ghostController, TargetStrategy targetStrategy, int powerPellets) {
        super(ghostController, targetStrategy, powerPellets);
    }

    @Override
    void update(GameData gameData, int step, long elapsedTime) {
        if (gameData.getMap().getPowerPellets().size() != activePPs) {
            activePPs--;
            ghostController.getGhost().setState(GhostStateENUM.FRIGHTENED);
            ghostController.setChangeOrientation(true);
            ghostController.changeState(new GhostStateFrightened(ghostController, targetStrategy, activePPs));
        }
        if (!((elapsedTime > 0 && elapsedTime <= 7000) || (elapsedTime > 27000 && elapsedTime <= 34000) || (elapsedTime > 54000 && elapsedTime <= 59000) || (elapsedTime > 79000 && elapsedTime <= 84000))) {
            ghostController.setChangeOrientation(true);
            ghostController.changeState(new GhostStateChase(ghostController, targetStrategy, activePPs));
        }
    }

    @Override
    void calculateAndStep(GameData gameData, int step) {
        ArrayList<OrientationENUM> availableOris;
        if (step % 4 == 0) {
            ghostController.getGhost().setTarget(ghostController.getGhost().getScatterTarget());
            if (ghostController.isChangeOrientation()) {
                ghostController.getGhost().setOrientationENUM(ghostController.getGhost().getOrientationENUM().getOpposite());
                ghostController.setChangeOrientation(false);
            } else {
                availableOris = ghostController.getAvailableOrientations(gameData);
                for (OrientationENUM ori : availableOris)
                    System.out.println(ori);
                System.out.printf("--");
                if (availableOris.size() > 0) {
                    ghostController.getGhost().setOrientationENUM(ghostController.chooseOrientation(availableOris));
                }
            }
            ghostController.getGhost().moveDirection();
        }
    }
}
