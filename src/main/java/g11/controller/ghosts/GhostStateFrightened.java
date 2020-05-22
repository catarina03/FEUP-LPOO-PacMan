package g11.controller.ghosts;

import g11.model.GameData;
import g11.model.Orientation;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GhostStateFrightened extends GhostState {
    public GhostStateFrightened(GhostController ghostController, TargetStrategy targetStrategy) {
        super(ghostController, targetStrategy);
    }

    @Override
    void update(GameData gameData, int step, long elapsedTime) {

    }

    @Override
    void calculateAndStep(GameData gameData, int step) {
        ArrayList<Orientation> availableOris;
        if (step % 5 == 0) {
            if (ghostController.isChangeOrientation()) {
                ghostController.getGhost().setOrientation(ghostController.getGhost().getOrientation().getOpposite());
                ghostController.setChangeOrientation(false);
            } else {
                availableOris = ghostController.getAvailableOrientations(gameData);
                if (availableOris.size() > 0) {
                    // choose random orientation
                    int randomNum = ThreadLocalRandom.current().nextInt(0, availableOris.size());
                    ghostController.getGhost().setOrientation(availableOris.get(randomNum));
                }
            }
            ghostController.getGhost().moveDirection();
        }
    }

}
