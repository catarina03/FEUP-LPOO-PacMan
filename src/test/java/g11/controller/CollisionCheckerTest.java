package g11.controller;

import g11.model.*;
import g11.model.Elements.*;
import g11.view.Gui;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static g11.model.Orientation.*;
import static org.junit.Assert.assertEquals;

public class CollisionCheckerTest {

    @Test
    public void collideTest(){
        CollisionChecker collisionChecker = new CollisionChecker();
        Wall wall = Mockito.mock(Wall.class);
        EmptySpace emptySpace = Mockito.mock(EmptySpace.class);
        PacMan pacman = Mockito.mock(PacMan.class);

        Position pos1 = Mockito.mock(Position.class);
        Mockito.when(pos1.getX()).thenReturn(10);
        Mockito.when(pos1.getY()).thenReturn(20);

        Position pos2 = Mockito.mock(Position.class);
        Mockito.when(pos2.getX()).thenReturn(20);
        Mockito.when(pos2.getY()).thenReturn(30);

        Mockito.when(wall.getPosition()).thenReturn(pos1);
        Mockito.when(pacman.getPosition()).thenReturn(pos1);
        Mockito.when(emptySpace.getPosition()).thenReturn(pos2);

        assertEquals(true, collisionChecker.collide(pacman.getPosition(), wall.getPosition()));
        assertEquals(false, collisionChecker.collide(pacman.getPosition(), emptySpace.getPosition()));
    }

    @Test
    public void checkWallCollisionTest(){
        CollisionChecker collisionChecker = new CollisionChecker();
        GameData gameData = Mockito.mock(GameData.class);
        Map map = Mockito.mock(Map.class);
        PacMan pacman = Mockito.mock(PacMan.class);
        Mockito.when(pacman.getPosition()).thenReturn(new Position(10, 10));
        Mockito.when(pacman.getOrientation()).thenReturn(UP);

        Wall wall1 = Mockito.mock(Wall.class);
        Mockito.when(wall1.getPosition()).thenReturn(new Position(11, 10));
        Wall wall2 = Mockito.mock(Wall.class);
        Mockito.when(wall2.getPosition()).thenReturn(new Position(10, 11));
        Wall wall3 = Mockito.mock(Wall.class);
        Mockito.when(wall3.getPosition()).thenReturn(new Position(9, 10));
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(wall1);
        walls.add(wall2);
        walls.add(wall3);

        Mockito.when(map.getWalls()).thenReturn(walls);
        Mockito.when(gameData.getPacMan()).thenReturn(pacman);
        Mockito.when(gameData.getMap()).thenReturn(map);

        assertEquals(false, collisionChecker.checkWallCollision(gameData, Gui.MOVE.ESC));
        assertEquals(false, collisionChecker.checkWallCollision(gameData, Gui.MOVE.UP));
        assertEquals(true, collisionChecker.checkWallCollision(gameData, Gui.MOVE.RIGHT));
        assertEquals(true, collisionChecker.checkWallCollision(gameData, Gui.MOVE.DOWN));
        assertEquals(true, collisionChecker.checkWallCollision(gameData, Gui.MOVE.LEFT));
    }

    @Test
    public void orientationToMoveTest(){
        CollisionChecker collisionChecker = new CollisionChecker();
        Orientation orientation = UP;
        assertEquals(Gui.MOVE.UP, collisionChecker.orientationToMove(orientation));
        orientation = RIGHT;
        assertEquals(Gui.MOVE.RIGHT, collisionChecker.orientationToMove(orientation));
        orientation = DOWN;
        assertEquals(Gui.MOVE.DOWN, collisionChecker.orientationToMove(orientation));
        orientation = LEFT;
        assertEquals(Gui.MOVE.LEFT, collisionChecker.orientationToMove(orientation));
    }

    @Test
    public void updateCoinCollisionTest(){ //NEEDS FIXING
        CollisionChecker collisionChecker = new CollisionChecker();

        GameData gameData = Mockito.mock(GameData.class);
        Map map = Mockito.mock(Map.class);
        PacMan pacman = Mockito.mock(PacMan.class);
        GameStats gameStats = Mockito.mock(GameStats.class);

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(10);
        Mockito.when(position.getY()).thenReturn(11);

        Coin coin = Mockito.mock(Coin.class);
        Mockito.when(coin.getPosition()).thenReturn(position);
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(coin);
        ArrayList<EmptySpace> emptySpaces = new ArrayList<>();
        emptySpaces.clear();
        ArrayList<MapComponent> mapComponents = new ArrayList<>();
        mapComponents.add(coin);

        /*
        int score = 0;
        gameStats.setScore(score);
        gameData.setGameStats(gameStats);

         */

        Mockito.when(pacman.getPosition()).thenReturn(position);
        Mockito.when(map.getCoins()).thenReturn(coins);
        Mockito.when(map.getEmptySpaces()).thenReturn(emptySpaces);
        Mockito.when(map.getMapComponents()).thenReturn(mapComponents);
        Mockito.when(gameData.getPacMan()).thenReturn(pacman);
        Mockito.when(gameData.getMap()).thenReturn(map);
        //Mockito.when(gameData.getGameStats()).thenReturn(gameStats);

        GameData result = collisionChecker.updateCoinCollison(gameData);

        assertEquals(0, result.getMap().getCoins().size());
        assertEquals(1, result.getMap().getEmptySpaces().size());
        assertEquals(1, result.getMap().getMapComponents().size());
        //assertEquals(1, result.getGameStats().getScore());
    }
}
