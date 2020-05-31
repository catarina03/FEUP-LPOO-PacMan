package g11.controller.ghosts.states;

import g11.controller.ghosts.GhostController;
import g11.controller.ghosts.GhostState;
import g11.controller.ghosts.TargetStrategy;
import g11.model.GameData;
import g11.model.GhostStateEnumeration;
import g11.model.OrientationEnumeration;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GhostStateFrightened extends GhostState {
    int ticksToEnd;

    public void setTicksToEnd(int ticksToEnd) {
        this.ticksToEnd = ticksToEnd;
    }

    public GhostStateFrightened(GhostController ghostController, TargetStrategy targetStrategy, int powerPellets) {
        super(ghostController, targetStrategy, powerPellets);
        this.ticksToEnd = 160;
    }

    @Override
    public void update(GameData gameData, int step, long elapsedTime) {
        ticksToEnd--;
        if (ticksToEnd <= 50) {
            if (ticksToEnd == 50 || ticksToEnd == 30 || ticksToEnd == 20 || ticksToEnd == 10)
                ghostController.getGhost().setState(GhostStateEnumeration.CHASE);
            else if (ticksToEnd == 40 || ticksToEnd == 25 || ticksToEnd == 15 || ticksToEnd == 5)
                ghostController.getGhost().setState(GhostStateEnumeration.FRIGHTENED);
        }
        if (ticksToEnd == 0) {
            ghostController.getGhost().setState(GhostStateEnumeration.CHASE);
            ghostController.changeState(new GhostStateChase(ghostController, ghostController.getTargetStrategy(), activePPs));
        }
        if (gameData.getMap().getPowerPellets().size() != activePPs) {
            activePPs--;
            ticksToEnd = 160;
        }
    }

    @Override
    public void calculateAndStep(GameData gameData, int step) {
        ArrayList<OrientationEnumeration> availableOris;
        if (step % 5 == 0) {
            if (ghostController.isChangeOrientation()) {
                ghostController.getGhost().setOrientationEnumeration(ghostController.getGhost().getOrientationEnumeration().getOpposite());
                ghostController.setChangeOrientation(false);
            } else {
                availableOris = ghostController.getAvailableOrientations(gameData);
                if (availableOris.size() > 0) {
                    // choose random orientation
                    int randomNum = ThreadLocalRandom.current().nextInt(0, availableOris.size());
                    ghostController.getGhost().setOrientationEnumeration(availableOris.get(randomNum));
                }
            }
            ghostController.getGhost().moveDirection();
        }
    }

}
