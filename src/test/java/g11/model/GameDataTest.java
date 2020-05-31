package g11.model;

import g11.model.elements.Ghost;
import g11.model.elements.PacMan;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GameDataTest {

    @Test
    public void GameDataCreation() {
        Ghost ghost1 = Mockito.mock(Ghost.class);
        Ghost ghost2 = Mockito.mock(Ghost.class);
        Ghost ghost3 = Mockito.mock(Ghost.class);
        ArrayList<Ghost> ghosts = new ArrayList<>();
        ghosts.add(ghost1);
        ghosts.add(ghost2);
        ghosts.add(ghost3);
        PacMan pacman = Mockito.mock(PacMan.class);
        GameStats stats = Mockito.mock(GameStats.class);
        Map map = Mockito.mock(Map.class);

        GameData data = new GameData(stats, pacman, ghosts, map);

        assertEquals(data.getGameStats(), stats);
        assertEquals(data.getPacMan(), pacman);
        assertEquals(data.getGhosts(), ghosts);
        assertEquals(data.getMap(), map);
    }

    @Test
    public void GameDataChange() {
        Ghost ghost1 = Mockito.mock(Ghost.class);
        Ghost ghost2 = Mockito.mock(Ghost.class);
        Ghost ghost3 = Mockito.mock(Ghost.class);
        ArrayList<Ghost> ghosts = new ArrayList<>();
        ghosts.add(ghost1);
        ghosts.add(ghost2);
        ghosts.add(ghost3);
        PacMan pacman = Mockito.mock(PacMan.class);
        GameStats stats = Mockito.mock(GameStats.class);
        Map map = Mockito.mock(Map.class);

        GameData data = new GameData(stats, pacman, ghosts, map);

        Ghost ghost4 = Mockito.mock(Ghost.class);
        Ghost ghost5 = Mockito.mock(Ghost.class);
        Ghost ghost6 = Mockito.mock(Ghost.class);
        ArrayList<Ghost> new_ghosts = new ArrayList<>();
        ghosts.add(ghost4);
        ghosts.add(ghost5);
        ghosts.add(ghost6);
        PacMan new_pacman = Mockito.mock(PacMan.class);
        GameStats new_stats = Mockito.mock(GameStats.class);
        Map new_map = Mockito.mock(Map.class);

        data.setMap(new_map);
        data.setGameStats(new_stats);
        data.setPacMan(new_pacman);
        data.setGhosts(new_ghosts);

        assertEquals(data.getGhosts(), new_ghosts);
        assertEquals(data.getPacMan(), new_pacman);
        assertEquals(data.getGameStats(), new_stats);
        assertEquals(data.getMap(), new_map);
    }
}
