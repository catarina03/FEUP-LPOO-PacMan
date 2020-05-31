package g11.controller;

import g11.controller.ghosts.GhostController;
import g11.model.elements.PacMan;
import g11.model.elements.ghosts.Blinky;
import g11.model.elements.map.Coin;
import g11.model.elements.map.Wall;
import g11.model.GameData;
import g11.model.Map;
import g11.model.Position;
import g11.view.MoveEnumeration;
import g11.view.guis.GuiSquare;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void updateTest() {
        GuiSquare guiSquare = Mockito.mock(GuiSquare.class);
        Game game = new Game(guiSquare);

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
        Mockito.when(position2.getX()).thenReturn(11);
        Mockito.when(position2.getY()).thenReturn(11);
        Mockito.when(wall.getPosition()).thenReturn(position2);
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(wall);

        Coin coin = Mockito.mock(Coin.class);
        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(12);
        Mockito.when(position.getY()).thenReturn(12);
        Mockito.when(coin.getPosition()).thenReturn(position);
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(coin);

        Map map = Mockito.mock(Map.class);
        Mockito.when(map.getWalls()).thenReturn(walls);
        Mockito.when(map.getCoins()).thenReturn(coins);
        Mockito.when(gameData.getMap()).thenReturn(map);

        Blinky blinky = Mockito.mock(Blinky.class);
        Position position3 = Mockito.mock(Position.class);
        Mockito.when(position3.getX()).thenReturn(13);
        Mockito.when(position3.getY()).thenReturn(13);
        Mockito.when(blinky.getPosition()).thenReturn(position3);

        GhostController ghostController1 = Mockito.mock(GhostController.class);
        ArrayList<GhostController> ghostControllers = new ArrayList<>();
        ghostControllers.add(ghostController1);
        game.setGhostControllers(ghostControllers);
        Mockito.when(ghostController1.getGhost()).thenReturn(blinky);

        //Mockito.when(collisionChecker.collide(position3, position1)).thenReturn(false);
        //game.update(gameData, 12, 500);

        //Mockito.verify(collisionChecker, Mockito.times(1)).updateFoodCollison(gameData, game);
        //Mockito.verify(gameData, Mockito.times(1)).getMap().getCoins().isEmpty();
        //Mockito.verify(collisionChecker, Mockito.times(1)).checkWallCollision(gameData, MoveEnumeration.ESC);
        //Mockito.verify(gameData, Mockito.times(1)).getPacMan().moveDirection();
    }

    @Test
    public void processKeyTest() {
        GuiSquare guiSquare = Mockito.mock(GuiSquare.class);
        Game game = new Game(guiSquare);

        game.setGuiSquare(guiSquare);
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
