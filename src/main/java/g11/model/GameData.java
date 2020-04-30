package g11.model;

import g11.model.Elements.Ghost;
import g11.model.Elements.PacMan;

import java.util.List;

public class GameData {
    private GameStats gameStats;
    private PacMan pacMan;
    private List<Ghost> ghosts;
    private Map map;

    public GameData(GameStats gameStats, PacMan pacMan, List<Ghost> ghosts, Map map) {
        this.gameStats = gameStats;
        this.pacMan = pacMan;
        this.ghosts = ghosts;
        this.map = map;
    }

    public void update() {
        pacMan.moveDirection();
    }

    public GameStats getGameStats() {
        return gameStats;
    }

    public PacMan getPacMan() {
        return pacMan;
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setGameStats(GameStats gameStats) {
        this.gameStats = gameStats;
    }

    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    public void setGhosts(List<Ghost> ghosts) {
        this.ghosts = ghosts;
    }
}
