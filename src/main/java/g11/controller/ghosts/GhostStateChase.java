package g11.controller.ghosts;

import g11.model.GameData;
import g11.model.Orientation;
import g11.model.Position;

import java.util.ArrayList;

public class GhostStateChase extends GhostState {
    public GhostStateChase(GhostController2 ghostController, TargetStrategy targetStrategy) {
        super(ghostController, targetStrategy);
    }

    @Override
    public void update(GameData gameData, int step) {
        if (ghostController.isExitingHouse() && ghostController.getGhost().getPosition().equals(new Position(13, 14))) // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
            ghostController.setExitingHouse(false);

        if (ghostController.isExitingHouse())
            ghostController.getGhost().setTarget(new Position(13, 14));  // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
        else ghostController.getGhost().setTarget(getTarget(gameData));

        calculateAndStep(gameData, step);
    }

    @Override
    public void calculateAndStep(GameData gameData, int step) {
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
