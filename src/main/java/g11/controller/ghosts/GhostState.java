package g11.controller.ghosts;

import g11.model.GameData;
import g11.model.Position;

public abstract class GhostState {
    protected GhostController ghostController;
    protected TargetStrategy targetStrategy;
    protected int activePPs;

    public GhostState(GhostController ghostController, TargetStrategy targetStrategy) {
        this.ghostController = ghostController;
        this.targetStrategy = targetStrategy;
    }

    abstract void update(GameData gameData, int step, long elapsedTime);

    abstract void calculateAndStep(GameData gameData, int step);

    public Position getTarget(GameData gameData) {
        return targetStrategy.getTarget(gameData);
    }
}
