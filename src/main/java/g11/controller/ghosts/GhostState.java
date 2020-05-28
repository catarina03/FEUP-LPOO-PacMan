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

    public Boolean isScatterTime(long elapsedTime) {
        return (elapsedTime > 0 && elapsedTime <= 7000) || (elapsedTime > 27000 && elapsedTime <= 34000) || (elapsedTime > 54000 && elapsedTime <= 59000) || (elapsedTime > 79000 && elapsedTime <= 84000);
    }
}
