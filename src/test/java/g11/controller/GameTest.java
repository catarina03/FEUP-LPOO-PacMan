package g11.controller;

import g11.model.elements.PacMan;
import g11.model.elements.Wall;
import g11.model.GameData;
import g11.model.Map;
import g11.model.Position;
import g11.view.Gui;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static g11.view.Gui.MOVE.*;
import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void updateTest(){
        Game game = new Game(); //Cria uma tela no GUI, dar fix

        GameData gameData = Mockito.mock(GameData.class);
        CollisionChecker collisionChecker = Mockito.mock(CollisionChecker.class);
        game.setCchecker(collisionChecker);

        PacMan pacman = Mockito.mock(PacMan.class);
        Position position1 = Mockito.mock(Position.class);
        Mockito.when(position1.getX()).thenReturn(10);
        Mockito.when(position1.getY()).thenReturn(10);
        Mockito.when(pacman.getPosition()).thenReturn(position1);
        Mockito.when(gameData.getPacMan()).thenReturn(pacman);

        Wall wall = Mockito.mock(Wall.class);
        Position position2 = Mockito.mock(Position.class);
        Mockito.when(position2.getX()).thenReturn(10);
        Mockito.when(position2.getY()).thenReturn(11);
        Mockito.when(wall.getPosition()).thenReturn(position2);
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(wall);

        Map map = Mockito.mock(Map.class);
        Mockito.when(map.getWalls()).thenReturn(walls);
        Mockito.when(gameData.getMap()).thenReturn(map);

        game.update(gameData);
        Mockito.verify(collisionChecker, Mockito.times(1)).updateCoinCollison(gameData);
        Mockito.verify(collisionChecker, Mockito.times(1)).checkWallCollision(gameData, Gui.MOVE.ESC);
        Mockito.verify(gameData, Mockito.times(1)).update();
    }

    @Test
    public void processKeyTest() throws Throwable {
        Game game = new Game();

        Gui gui = Mockito.mock(Gui.class);
        game.setGui(gui);
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

        game.processKey(Gui.MOVE.UP);
        Mockito.verify(collisionChecker, Mockito.times(1)).checkWallCollision(gameData, Gui.MOVE.UP);
        assertEquals(false, collisionChecker.checkWallCollision(gameData, Gui.MOVE.UP));

        game.processKey(Gui.MOVE.RIGHT);
        Mockito.verify(collisionChecker, Mockito.times(1)).checkWallCollision(gameData, Gui.MOVE.RIGHT);
        assertEquals(false, collisionChecker.checkWallCollision(gameData, RIGHT));

        game.processKey(DOWN);
        Mockito.verify(collisionChecker, Mockito.times(1)).checkWallCollision(gameData, DOWN);
        assertEquals(false, collisionChecker.checkWallCollision(gameData, Gui.MOVE.DOWN));

        game.processKey(LEFT);
        Mockito.verify(collisionChecker, Mockito.times(1)).checkWallCollision(gameData, LEFT);
        assertEquals(false, collisionChecker.checkWallCollision(gameData, Gui.MOVE.LEFT));

        game.processKey(Gui.MOVE.ESC);
        assertEquals(false, game.getRunning());
        Mockito.verify(gui, Mockito.times(1)).close();
    }
}
