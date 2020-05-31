package g11.controller;

import g11.controller.ghosts.TargetStrategy;
import g11.controller.ghosts.strategies.TargetStrategyBlinky;
import g11.controller.ghosts.strategies.TargetStrategyClyde;
import g11.controller.ghosts.strategies.TargetStrategyInky;
import g11.controller.ghosts.strategies.TargetStrategyPinky;
import g11.model.GameData;
import g11.model.OrientationEnumeration;
import g11.model.Position;
import g11.model.elements.Ghost;
import g11.model.elements.PacMan;
import g11.model.elements.ghosts.Blinky;
import g11.model.elements.ghosts.Clyde;
import g11.model.elements.ghosts.Inky;
import g11.model.elements.ghosts.Pinky;
import javafx.geometry.Pos;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
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
        Position position = Mockito.mock(Position.class);
        Position position2 = Mockito.mock(Position.class);
        Inky inky = Mockito.mock(Inky.class);
        Blinky blinky = Mockito.mock(Blinky.class);
        Clyde clyde = Mockito.mock(Clyde.class);
        Pinky pinky = Mockito.mock(Pinky.class);
        TargetStrategyClyde clydeStrategy = new TargetStrategyClyde();

        ArrayList<Ghost> ghosts = new ArrayList<>();
        ghosts.add(inky);
        ghosts.add(blinky);
        ghosts.add(clyde);
        ghosts.add(pinky);

        Mockito.when(position.getX()).thenReturn(0);
        Mockito.when(position.getX()).thenReturn(0);
        Mockito.when(position2.getX()).thenReturn(9);
        Mockito.when(position2.getX()).thenReturn(9);
        Mockito.when(position.distance(position2)).thenReturn(9.0);
        Mockito.when(gameData.getGhosts()).thenReturn(ghosts);
        Mockito.when(gameData.getPacMan()).thenReturn(pacMan);
        Mockito.when(pinky.getPosition()).thenReturn(position2);
        Mockito.when(pinky.getScatterTarget()).thenReturn(position);
        Mockito.when(pacMan.getPosition()).thenReturn(position);

        assertEquals(clydeStrategy.getTarget(gameData), pacMan.getPosition());

        Mockito.when(position.distance(position2)).thenReturn(7.0);

        assertEquals(clydeStrategy.getTarget(gameData), pinky.getScatterTarget());
    }

    @Test
    public void Inky(){
        GameData gameData = Mockito.mock(GameData.class);
        PacMan pacMan = Mockito.mock(PacMan.class);
        Position position = Mockito.mock(Position.class);
        Position result = Mockito.mock(Position.class);
        Blinky blinky = Mockito.mock(Blinky.class);
        TargetStrategyInky targetStrategyInky = new TargetStrategyInky();

        ArrayList<Ghost> ghosts = new ArrayList<>();
        ghosts.add(blinky);

        Mockito.when(position.getX()).thenReturn(5);
        Mockito.when(position.getY()).thenReturn(6);
        Mockito.when(pacMan.getPosition()).thenReturn(position);
        Mockito.when(blinky.getPosition()).thenReturn(position);
        Mockito.when(gameData.getPacMan()).thenReturn(pacMan);
        Mockito.when(gameData.getGhosts()).thenReturn(ghosts);
        Mockito.when(pacMan.getOrientationEnumeration()).thenReturn(OrientationEnumeration.UP);

        result = targetStrategyInky.getTarget(gameData);

        assertEquals(result.getX(), 2 * (pacMan.getPosition().getX() - 2) - blinky.getPosition().getX());
        assertEquals(result.getY(), 2 * (pacMan.getPosition().getY() - 2) - blinky.getPosition().getY());

        Mockito.when(pacMan.getOrientationEnumeration()).thenReturn(OrientationEnumeration.DOWN);

        result = targetStrategyInky.getTarget(gameData);

        assertEquals(result.getX(), 2 * (pacMan.getPosition().getX()) - blinky.getPosition().getX());
        assertEquals(result.getY(), 2 * (pacMan.getPosition().getY() + 2) - blinky.getPosition().getY());

        Mockito.when(pacMan.getOrientationEnumeration()).thenReturn(OrientationEnumeration.LEFT);

        result = targetStrategyInky.getTarget(gameData);

        assertEquals(result.getX(), 2 * (pacMan.getPosition().getX() - 2) - blinky.getPosition().getX());
        assertEquals(result.getY(), 2 * (pacMan.getPosition().getY()) - blinky.getPosition().getY());

        Mockito.when(pacMan.getOrientationEnumeration()).thenReturn(OrientationEnumeration.RIGHT);

        result = targetStrategyInky.getTarget(gameData);

        assertEquals(result.getX(), 2 * (pacMan.getPosition().getX() + 2) - blinky.getPosition().getX());
        assertEquals(result.getY(), 2 * (pacMan.getPosition().getY()) - blinky.getPosition().getY());
    }

    @Test
    public void Pinky(){
        GameData gameData = Mockito.mock(GameData.class);
        PacMan pacMan = Mockito.mock(PacMan.class);
        Position position = Mockito.mock(Position.class);
        Position result = Mockito.mock(Position.class);
        TargetStrategyPinky targetStrategyPinky = new TargetStrategyPinky();

        Mockito.when(position.getX()).thenReturn(5);
        Mockito.when(position.getY()).thenReturn(6);
        Mockito.when(pacMan.getPosition()).thenReturn(position);
        Mockito.when(gameData.getPacMan()).thenReturn(pacMan);
        Mockito.when(pacMan.getOrientationEnumeration()).thenReturn(OrientationEnumeration.UP);

        result = targetStrategyPinky.getTarget(gameData);

        assertEquals(result.getX(), pacMan.getPosition().getX() - 4);
        assertEquals(result.getY(), pacMan.getPosition().getY() - 4);

        Mockito.when(pacMan.getOrientationEnumeration()).thenReturn(OrientationEnumeration.DOWN);

        result = targetStrategyPinky.getTarget(gameData);

        assertEquals(result.getX(), pacMan.getPosition().getX());
        assertEquals(result.getY(), pacMan.getPosition().getY() + 4);

        Mockito.when(pacMan.getOrientationEnumeration()).thenReturn(OrientationEnumeration.LEFT);

        result = targetStrategyPinky.getTarget(gameData);

        assertEquals(result.getX(), pacMan.getPosition().getX() - 4);
        assertEquals(result.getY(), pacMan.getPosition().getY());

        Mockito.when(pacMan.getOrientationEnumeration()).thenReturn(OrientationEnumeration.RIGHT);

        result = targetStrategyPinky.getTarget(gameData);

        assertEquals(result.getX(), pacMan.getPosition().getX() + 4);
        assertEquals(result.getY(), pacMan.getPosition().getY());
    }
}
