package g11.controller;

import g11.model.elements.PacMan;
import g11.model.elements.map.Wall;
import g11.model.GameData;
import g11.model.Map;
import g11.model.Position;
import g11.view.MoveEnumeration;
import g11.view.guis.GuiSquare;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void processKeyTest() {
        GuiSquare guiSquare = Mockito.mock(GuiSquare.class);
        Game game = new Game(guiSquare);

        game.setGui(guiSquare);
        CollisionChecker collisionChecker = Mockito.mock(CollisionChecker.class);
        game.setCchecker(collisionChecker);
        GameData gameData = Mockito.mock(GameData.class);

        Position position1 = Mockito.mock(Position.class);
        Mockito.when(position1.getX()).thenReturn(15);
        Mockito.when(position1.getY()).thenReturn(16);
        Position position2 = Mockito.mock(Position.class);
        Mockito.when(position2.getX()).thenReturn(10);
        Mockito.when(position2.getY()).thenReturn(11);
        Wall wall = Mockito.mock(Wall.class);
        Mockito.when(wall.getPosition()).thenReturn(position1);
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(wall);
        Map map = Mockito.mock(Map.class);
        Mockito.when(gameData.getMap()).thenReturn(map);
        Mockito.when(map.getWalls()).thenReturn(walls);
        PacMan pacman = Mockito.mock(PacMan.class);
        Mockito.when(pacman.getPosition()).thenReturn(position2);
        Mockito.when(gameData.getPacMan()).thenReturn(pacman);

        game.setGameData(gameData);

        assertFalse(game.processKey(MoveEnumeration.UP));
        Mockito.verify(collisionChecker, Mockito.times(1)).checkWallCollision(gameData, MoveEnumeration.UP);
        assertFalse(collisionChecker.checkWallCollision(gameData, MoveEnumeration.UP));

        assertFalse(game.processKey(MoveEnumeration.RIGHT));
        Mockito.verify(collisionChecker, Mockito.times(1)).checkWallCollision(gameData, MoveEnumeration.RIGHT);
        assertFalse(collisionChecker.checkWallCollision(gameData, MoveEnumeration.RIGHT));

        assertFalse(game.processKey(MoveEnumeration.DOWN));
        Mockito.verify(collisionChecker, Mockito.times(1)).checkWallCollision(gameData, MoveEnumeration.DOWN);
        assertFalse(collisionChecker.checkWallCollision(gameData, MoveEnumeration.DOWN));

        assertFalse(game.processKey(MoveEnumeration.LEFT));
        Mockito.verify(collisionChecker, Mockito.times(1)).checkWallCollision(gameData, MoveEnumeration.LEFT);
        assertFalse(collisionChecker.checkWallCollision(gameData, MoveEnumeration.LEFT));

        assertTrue(game.processKey(MoveEnumeration.ESC));
    }
}
