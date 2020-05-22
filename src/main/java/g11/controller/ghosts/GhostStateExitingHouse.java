package g11.controller.ghosts;

import g11.model.GameData;
import g11.model.Orientation;
import g11.model.Position;

import java.util.ArrayList;

public class GhostStateExitingHouse extends GhostState {
    public GhostStateExitingHouse(GhostController ghostController, TargetStrategy targetStrategy, int powerPellets) {
        super(ghostController, targetStrategy, powerPellets);
    }

    @Override
    void update(GameData gameData, int step, long elapsedTime) {
        if (ghostController.getGhost().getPosition().equals(new Position(13, 14)) || ghostController.getGhost().getPosition().equals(new Position(14, 14))) {
            ghostController.setAccessingHouse(false);
            ghostController.changeState(new GhostStateChase(ghostController, ghostController.getTargetStrategy(), activePPs));
        }
    }

    @Override
    void calculateAndStep(GameData gameData, int step) {
        ArrayList<Orientation> availableOris;
        if (step % 4 == 0) {
            ghostController.getGhost().setTarget(new Position(13, 14));
            availableOris = ghostController.getAvailableOrientations(gameData);
            if (availableOris.size() > 0) {
                ghostController.getGhost().setOrientation(ghostController.chooseOrientation(availableOris));
            }
            ghostController.getGhost().moveDirection();
        }
    }
}
