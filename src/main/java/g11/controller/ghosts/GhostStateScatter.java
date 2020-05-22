package g11.controller.ghosts;

import g11.model.GameData;
import g11.model.Orientation;
import g11.model.Position;

import java.util.ArrayList;

public class GhostStateScatter extends GhostState {
    public GhostStateScatter(GhostController2 ghostController, TargetStrategy targetStrategy) {
        super(ghostController, targetStrategy);
    }

    @Override
    void update(GameData gameData, int step) {
        ghostController.getGhost().setTarget(ghostController.getGhost().getScatterTarget());
        calculateAndStep(gameData, step);
    }

    @Override
    void calculateAndStep(GameData gameData, int step) {
        ArrayList<Orientation> availableOris;
        if (step % 4 == 0) {
            if (ghostController.isChangeOrientation()) {
                ghostController.getGhost().setOrientation(ghostController.getGhost().getOrientation().getOpposite());
                ghostController.setChangeOrientation(false);
            } else {
                availableOris = ghostController.getAvailableOrientations(gameData);
                if (availableOris.size() > 0) {
                    ghostController.getGhost().setOrientation(ghostController.chooseOrientation(availableOris));
                }
            }
        }
        ghostController.getGhost().moveDirection();
    }
}
