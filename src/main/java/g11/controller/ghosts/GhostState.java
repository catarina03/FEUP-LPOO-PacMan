package g11.controller.ghosts;

import g11.model.GameData;

public abstract class GhostState {
    protected GhostController ghostController;
    protected TargetStrategy targetStrategy;
    protected int activePPs;

    public GhostState(GhostController ghostController, TargetStrategy targetStrategy, int powerPellets) {
        this.ghostController = ghostController;
        this.targetStrategy = targetStrategy;
        this.activePPs = powerPellets;
    }

    public int getActivePPs() {
        return activePPs;
    }

    abstract void update(GameData gameData, int step, long elapsedTime);

    abstract void calculateAndStep(GameData gameData, int step);

}
