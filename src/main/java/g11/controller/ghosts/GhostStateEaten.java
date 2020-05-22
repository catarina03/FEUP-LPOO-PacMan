package g11.controller.ghosts;

import g11.model.GameData;
import g11.model.Orientation;

import java.util.ArrayList;

public class GhostStateEaten extends GhostState {
    public GhostStateEaten(GhostController ghostController, TargetStrategy targetStrategy) {
        super(ghostController, targetStrategy);
    }

    @Override
    void update(GameData gameData, int step, long elapsedTime) {

    }

    @Override
    void calculateAndStep(GameData gameData, int step) {
        ArrayList<Orientation> availableOris;
        if (step % 3 == 0) {
            availableOris = ghostController.getAvailableOrientations(gameData);
            if (availableOris.size() > 0) {
                ghostController.getGhost().setOrientation(ghostController.chooseOrientation(availableOris));
            }
            ghostController.getGhost().moveDirection();
        }
    }
}
