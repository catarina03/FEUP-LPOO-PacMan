package g11.controller;

import g11.model.*;
import g11.model.elements.*;
import g11.model.elements.map.Coin;
import g11.model.elements.map.EmptySpace;
import g11.model.elements.map.Wall;
import g11.model.elements.map.Gate;
import g11.model.elements.map.PowerPellet;

import g11.view.MoveEnumeration;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static g11.model.OrientationEnumeration.*;
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
        Mockito.when(pacman.getOrientationEnumeration()).thenReturn(UP);

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

        assertFalse(collisionChecker.checkWallCollision(gameData, MoveEnumeration.ESC));
        assertFalse(collisionChecker.checkWallCollision(gameData, MoveEnumeration.UP));
        assertTrue(collisionChecker.checkWallCollision(gameData, MoveEnumeration.RIGHT));
        assertTrue(collisionChecker.checkWallCollision(gameData, MoveEnumeration.DOWN));
        assertTrue(collisionChecker.checkWallCollision(gameData, MoveEnumeration.LEFT));
    }

    @Test
    public void orientationToMoveTest() {
        CollisionChecker collisionChecker = new CollisionChecker();

        OrientationEnumeration orientationEnumeration = UP;
        assertEquals(MoveEnumeration.UP, collisionChecker.orientationToMove(orientationEnumeration));
        orientationEnumeration = RIGHT;
        assertEquals(MoveEnumeration.RIGHT, collisionChecker.orientationToMove(orientationEnumeration));
        orientationEnumeration = DOWN;
        assertEquals(MoveEnumeration.DOWN, collisionChecker.orientationToMove(orientationEnumeration));
        orientationEnumeration = LEFT;
        assertEquals(MoveEnumeration.LEFT, collisionChecker.orientationToMove(orientationEnumeration));

    }

    @Test
    public void updateFoodCollisionTest(){
        CollisionChecker collisionChecker = new CollisionChecker();

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(10);
        Mockito.when(position.getY()).thenReturn(11);

        Position position2 = Mockito.mock(Position.class);
        Mockito.when(position2.getX()).thenReturn(12);
        Mockito.when(position2.getY()).thenReturn(12);

        GameData gameData = Mockito.mock(GameData.class);
        Game game = Mockito.mock(Game.class);

        Map map = Mockito.mock(Map.class);
        Mockito.when(gameData.getMap()).thenReturn(map);

        PacMan pacman = Mockito.mock(PacMan.class);
        Mockito.when(pacman.getPosition()).thenReturn(position);
        Mockito.when(gameData.getPacMan()).thenReturn(pacman);

        GameStats gameStats = Mockito.mock(GameStats.class);
        Mockito.when(gameData.getGameStats()).thenReturn(gameStats);

        ArrayList<MapComponent> mapComponents = new ArrayList<>();

        Coin coin = Mockito.mock(Coin.class);
        Mockito.when(coin.getPosition()).thenReturn(position);
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(coin);
        mapComponents.add(coin);
        Mockito.when(map.getCoins()).thenReturn(coins);

        PowerPellet powerPellet = Mockito.mock(PowerPellet.class);
        Mockito.when(powerPellet.getPosition()).thenReturn(position2);
        ArrayList<PowerPellet> powerPellets = new ArrayList<>();
        powerPellets.add(powerPellet);
        mapComponents.add(powerPellet);
        Mockito.when(map.getPowerPellets()).thenReturn(powerPellets);

        ArrayList<EmptySpace> emptySpaces = new ArrayList<>();
        Mockito.when(map.getEmptySpaces()).thenReturn(emptySpaces);
        Mockito.when(map.getMapComponents()).thenReturn(mapComponents);

        collisionChecker.updateFoodCollison(gameData, game);

        Mockito.verify(map, Mockito.times(1)).setCoins(coins);
        Mockito.verify(gameStats, Mockito.times(1)).incrementEatenCoins();

        Mockito.when(pacman.getPosition()).thenReturn(position2);

        collisionChecker.updateFoodCollison(gameData, game);

        Mockito.verify(map, Mockito.times(1)).setPowerPellets(powerPellets);
        Mockito.verify(gameStats, Mockito.times(1)).incrementEatenPP();

        Mockito.verify(map, Mockito.times(2)).setEmptySpaces(emptySpaces);
        Mockito.verify(map, Mockito.times(2)).setMapComponents(mapComponents);
        Mockito.verify(gameData, Mockito.times(2)).setGameStats(gameStats);
    }
}
