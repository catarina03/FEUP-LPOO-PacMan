package Model;

import Model.Elements.Ghost;
import Model.Elements.PacMan;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class GameDataTest {

    @Test
    public void GameDataManipulation() {
        GameData data = new GameData();

        Ghost ghost1 = Mockito.mock(Ghost.class);
        Ghost ghost2 = Mockito.mock(Ghost.class);
        Ghost ghost3 = Mockito.mock(Ghost.class);
        ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
        ghosts.add(ghost1);
        ghosts.add(ghost2);
        ghosts.add(ghost3);
        data.setGhosts(ghosts);

        PacMan pacman = Mockito.mock(PacMan.class);
        data.setPacMan(pacman);

        GameStats stats = Mockito.mock(GameStats.class);
        data.setGameStats(stats);

        Map map = Mockito.mock(Map.class);
        data.setMap(map);

        assertEquals(data.getGhosts(), ghosts);
        assertEquals(data.getPacMan(), pacman);
        assertEquals(data.getGameStats(), stats);
        assertEquals(data.getMap(), map);
    }

    @Test
    public void GameDataUpdate() {
        GameData data = new GameData();
        PacMan pacman = Mockito.mock(PacMan.class);

        data.setPacMan(pacman);
        data.update();
        Mockito.verify(pacman, Mockito.times(1)).moveDirection();
    }
}
