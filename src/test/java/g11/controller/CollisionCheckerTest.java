package g11.controller;

import g11.model.*;
import g11.model.elements.*;
import g11.view.GuiSquare;

import g11.view.MoveENUM;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static g11.model.OrientationENUM.*;
import static org.junit.Assert.*;


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

        assertTrue(collisionChecker.collide(pacman.getPosition(), wall.getPosition()));
        assertFalse(collisionChecker.collide(pacman.getPosition(), emptySpace.getPosition()));
    }

    @Test
    public void checkWallCollisionTest(){
        CollisionChecker collisionChecker = new CollisionChecker();
        GameData gameData = Mockito.mock(GameData.class);
        Map map = Mockito.mock(Map.class);
        PacMan pacman = Mockito.mock(PacMan.class);
        Mockito.when(pacman.getPosition()).thenReturn(new Position(10, 10));
        Mockito.when(pacman.getOrientationENUM()).thenReturn(UP);

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

        Gate gate = Mockito.mock(Gate.class);
        Mockito.when(gate.getPosition()).thenReturn(new Position(12, 12));
        ArrayList<Gate> gates = new ArrayList<>();
        gates.add(gate);

        Mockito.when(map.getWalls()).thenReturn(walls);
        Mockito.when(gameData.getPacMan()).thenReturn(pacman);
        Mockito.when(gameData.getMap()).thenReturn(map);
        Mockito.when(gameData.getMap().getGates()).thenReturn(gates);

        assertFalse(collisionChecker.checkWallCollision(gameData, MoveENUM.ESC));
        assertFalse(collisionChecker.checkWallCollision(gameData, MoveENUM.UP));
        assertTrue(collisionChecker.checkWallCollision(gameData, MoveENUM.RIGHT));
        assertTrue(collisionChecker.checkWallCollision(gameData, MoveENUM.DOWN));
        assertTrue(collisionChecker.checkWallCollision(gameData, MoveENUM.LEFT));
    }

    @Test
    public void orientationToMoveTest() {
        CollisionChecker collisionChecker = new CollisionChecker();
        OrientationENUM orientationENUM = UP;
        assertEquals(MoveENUM.UP, collisionChecker.orientationToMove(orientationENUM));
        orientationENUM = RIGHT;
        assertEquals(MoveENUM.RIGHT, collisionChecker.orientationToMove(orientationENUM));
        orientationENUM = DOWN;
        assertEquals(MoveENUM.DOWN, collisionChecker.orientationToMove(orientationENUM));
        orientationENUM = LEFT;
        assertEquals(MoveENUM.LEFT, collisionChecker.orientationToMove(orientationENUM));
    }


    /*
    @Test
    public void updateFoodCollisionTest(){
        CollisionChecker collisionChecker = new CollisionChecker();

        GameData gameData = Mockito.mock(GameData.class);
        Map map = Mockito.mock(Map.class);
        PacMan pacman = Mockito.mock(PacMan.class);
        GameStats gameStats = Mockito.mock(GameStats.class);
        ArrayList<MapComponent> mapComponents = new ArrayList<>();

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(10);
        Mockito.when(position.getY()).thenReturn(11);

        Position position2 = Mockito.mock(Position.class);
        Mockito.when(position2.getX()).thenReturn(12);
        Mockito.when(position2.getY()).thenReturn(12);

        Coin coin = Mockito.mock(Coin.class);
        Mockito.when(coin.getPosition()).thenReturn(position);
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(coin);
        mapComponents.add(coin);

        PowerPellet powerPellet = Mockito.mock(PowerPellet.class);
        Mockito.when(powerPellet.getPosition()).thenReturn(position2);
        ArrayList<PowerPellet> powerPellets = new ArrayList<>();
        powerPellets.add(powerPellet);
        mapComponents.add(powerPellet);

        ArrayList<EmptySpace> emptySpaces = new ArrayList<>();

        int score = 0;
        gameStats.setScore(score);

        Mockito.when(pacman.getPosition()).thenReturn(position);
        Mockito.when(map.getCoins()).thenReturn(coins);
        Mockito.when(map.getEmptySpaces()).thenReturn(emptySpaces);
        Mockito.when(map.getMapComponents()).thenReturn(mapComponents);
        Mockito.when(map.getPowerPellets()).thenReturn(powerPellets);
        Mockito.when(gameData.getPacMan()).thenReturn(pacman);
        Mockito.when(gameData.getMap()).thenReturn(map);
        Mockito.when(gameData.getGameStats()).thenReturn(gameStats);

        collisionChecker.updateFoodCollison(gameData);
        assertEquals(5, score);
        //Mockito.verify(collisionChecker, Mockito.times(1)).collide(coin.getPosition(), pacman.getPosition());
        //Mockito.verify(collisionChecker, Mockito.times(1)).collide(powerPellet.getPosition(), pacman.getPosition());
        //Mockito.verify(coins, Mockito.times(1)).remove(coin);
        //Mockito.verify(mapComponents, Mockito.times(1)).remove(coin);

        //GameData result = collisionChecker.updateCoinCollison(gameData);

        //assertEquals(0, result.getMap().getCoins().size());
        //assertEquals(1, result.getMap().getEmptySpaces().size());
        //assertEquals(1, result.getMap().getMapComponents().size());
        //Mockito.verify(gameStats, Mockito.times(1)).setScore(gameStats.getScore() + 1);
    }

     */
}
