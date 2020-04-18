package Model;

import Model.Elements.Ghost;
import Model.Elements.PacMan;

import java.util.ArrayList;
import java.util.List;

public class GameData {
    private GameStats gameStats;
    private PacMan pacMan;
    private List<Ghost> ghosts;
    private Map map;

    public GameData() {
        pacMan = new PacMan(10,10);
        map = new Map();
        gameStats = new GameStats(4);
        ghosts = new ArrayList<>();
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

}
