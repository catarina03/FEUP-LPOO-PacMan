package g11.controller.ghosts;

import g11.model.GameData;
import g11.model.Orientation;

import java.util.ArrayList;

public class GhostStateChase extends GhostState {
    public GhostStateChase(GhostController ghostController, TargetStrategy targetStrategy, int powerPellets) {
        super(ghostController, targetStrategy);
        this.activePPs = powerPellets;
    }

    @Override
    public void update(GameData gameData, int step, long elapsedTime) {
        if (gameData.getMap().getPowerPellets().size() != activePPs) {
            activePPs--;
            ghostController.changeState(new GhostStateFrightened(ghostController, targetStrategy));
        }

        if ((elapsedTime > 0 && elapsedTime <= 7000) || (elapsedTime > 27000 && elapsedTime <= 34000) || (elapsedTime > 54000 && elapsedTime <= 59000) || (elapsedTime > 79000 && elapsedTime <= 84000)) {
            ghostController.changeState(new GhostStateScatter(ghostController, targetStrategy, activePPs));
            ghostController.setChangeOrientation(true);
        }
    }


    @Override
    public void calculateAndStep(GameData gameData, int step) {
        ArrayList<Orientation> availableOris;
        if (step % 4 == 0) {
            ghostController.getGhost().setTarget(getTarget(gameData));
            if (ghostController.isChangeOrientation()) {
                ghostController.getGhost().setOrientation(ghostController.getGhost().getOrientation().getOpposite());
                ghostController.setChangeOrientation(false);
            } else {
                availableOris = ghostController.getAvailableOrientations(gameData);
                if (availableOris.size() > 0) {
                    ghostController.getGhost().setOrientation(ghostController.chooseOrientation(availableOris));
                }
            }
            ghostController.getGhost().moveDirection();
        }
    }
}
