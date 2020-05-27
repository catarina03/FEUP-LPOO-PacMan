package g11.controller;

import g11.controller.ghosts.TargetStrategy;
import g11.controller.ghosts.TargetStrategyBlinky;
import g11.controller.ghosts.TargetStrategyClyde;
import g11.model.GameData;
import g11.model.Position;
import g11.model.elements.PacMan;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TargetStrategyTest {

    @Test
    public void Blinky(){
        GameData gameData = Mockito.mock(GameData.class);
        PacMan pacMan = Mockito.mock(PacMan.class);
        TargetStrategyBlinky blinky = new TargetStrategyBlinky();

        Random r = new Random();
        int x = r.nextInt((20) + 1);
        int y = r.nextInt((20) + 1);

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(x);
        Mockito.when(position.getY()).thenReturn(y);

        Mockito.when(pacMan.getPosition()).thenReturn(position);
        Mockito.when(pacMan.getPosition()).thenReturn(position);

        Mockito.when(gameData.getPacMan()).thenReturn(pacMan);

        assertEquals(blinky.getTarget(gameData).getX(), x);
        assertEquals(blinky.getTarget(gameData).getY(), y);
    }

    @Test
    public void Clyde(){
        GameData gameData = Mockito.mock(GameData.class);
        PacMan pacMan = Mockito.mock(PacMan.class);
        TargetStrategyClyde clyde = new TargetStrategyClyde();

        //return gameData.getPacMan().getPosition().distance(gameData.getGhosts().get(3).getPosition()) >= 8 ?
        //        gameData.getPacMan().getPosition() :
        //        gameData.getGhosts().get(3).getScatterTarget();

    }
}
