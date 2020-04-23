package Model;

import Model.Elements.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MapTest {

    @Test
    public void MapCreation() {
        ArrayList<String> map = new ArrayList<String>();
        String s1 = "#ece#";
        String s2 = "#M$e#";
        map.add(s1);
        map.add(s2);

        ArrayList<Wall> walls = new ArrayList<Wall>();
        Wall w1 = Mockito.mock(Wall.class);
        Wall w2 = Mockito.mock(Wall.class);
        walls.add(w1);
        walls.add(w2);

        ArrayList<EmptySpace> emptySpaces = new ArrayList<EmptySpace>();
        EmptySpace es1 = Mockito.mock(EmptySpace.class);
        EmptySpace es2 = Mockito.mock(EmptySpace.class);
        emptySpaces.add(es1);
        emptySpaces.add(es2);

        ArrayList<Coin> coins = new ArrayList<Coin>();
        Coin c1  = Mockito.mock(Coin.class);
        Coin c2  = Mockito.mock(Coin.class);
        coins.add(c1);
        coins.add(c2);

        ArrayList<PowerPellet> powerPellet = new ArrayList<PowerPellet>();
        PowerPellet pp1 = Mockito.mock(PowerPellet.class);
        PowerPellet pp2 = Mockito.mock(PowerPellet.class);
        powerPellet.add(pp1);
        powerPellet.add(pp2);

        ArrayList<MapComponent> components = new ArrayList<MapComponent>();
        components.add(w1);
        components.add(w1);
        components.add(es1);
        components.add(es2);
        components.add(c1);
        components.add(c2);
        components.add(pp1);
        components.add(pp2);

        Map gameMap = new Map(map, walls, emptySpaces, coins, powerPellet, components);

        assertEquals(gameMap.getMap(), map);
        assertEquals(gameMap.getWalls(), walls);
        assertEquals(gameMap.getEmptySpaces(), emptySpaces);
        assertEquals(gameMap.getCoins(), coins);
        assertEquals(gameMap.getPowerPellets(), powerPellet);
        assertEquals(gameMap.getMapComponents(), components);
    }

    @Test
    public void MapChange() {
        ArrayList<String> map = new ArrayList<String>();
        String s1 = "#ece#";
        String s2 = "#M$e#";
        map.add(s1);
        map.add(s2);

        ArrayList<Wall> walls = new ArrayList<Wall>();
        Wall w1 = Mockito.mock(Wall.class);
        Wall w2 = Mockito.mock(Wall.class);
        walls.add(w1);
        walls.add(w2);

        ArrayList<EmptySpace> emptySpaces = new ArrayList<EmptySpace>();
        EmptySpace es1 = Mockito.mock(EmptySpace.class);
        EmptySpace es2 = Mockito.mock(EmptySpace.class);
        emptySpaces.add(es1);
        emptySpaces.add(es2);

        ArrayList<Coin> coins = new ArrayList<Coin>();
        Coin c1  = Mockito.mock(Coin.class);
        Coin c2  = Mockito.mock(Coin.class);
        coins.add(c1);
        coins.add(c2);

        ArrayList<PowerPellet> powerPellet = new ArrayList<PowerPellet>();
        PowerPellet pp1 = Mockito.mock(PowerPellet.class);
        PowerPellet pp2 = Mockito.mock(PowerPellet.class);
        powerPellet.add(pp1);
        powerPellet.add(pp2);

        ArrayList<MapComponent> components = new ArrayList<MapComponent>();
        components.add(w1);
        components.add(w1);
        components.add(es1);
        components.add(es2);
        components.add(c1);
        components.add(c2);
        components.add(pp1);
        components.add(pp2);

        Map gameMap = new Map(map, walls, emptySpaces, coins, powerPellet, components);

        ArrayList<String> new_map = new ArrayList<String>();
        String s3 = "#Mce#";
        String s4 = "#e$e#";
        map.add(s3);
        map.add(s4);

        ArrayList<Wall> new_walls = new ArrayList<Wall>();
        Wall w3 = Mockito.mock(Wall.class);
        Wall w4 = Mockito.mock(Wall.class);
        walls.add(w1);
        walls.add(w2);

        ArrayList<EmptySpace> new_emptySpaces = new ArrayList<EmptySpace>();
        EmptySpace es3 = Mockito.mock(EmptySpace.class);
        EmptySpace es4 = Mockito.mock(EmptySpace.class);
        emptySpaces.add(es3);
        emptySpaces.add(es4);

        ArrayList<Coin> new_coins = new ArrayList<Coin>();
        Coin c3  = Mockito.mock(Coin.class);
        Coin c4  = Mockito.mock(Coin.class);
        coins.add(c3);
        coins.add(c4);

        ArrayList<PowerPellet> new_powerPellet = new ArrayList<PowerPellet>();
        PowerPellet pp3 = Mockito.mock(PowerPellet.class);
        PowerPellet pp4 = Mockito.mock(PowerPellet.class);
        powerPellet.add(pp3);
        powerPellet.add(pp4);

        ArrayList<MapComponent> new_components = new ArrayList<MapComponent>();
        components.add(w3);
        components.add(w4);
        components.add(es3);
        components.add(es4);
        components.add(c3);
        components.add(c4);
        components.add(pp3);
        components.add(pp4);

        gameMap.setMapComponents(new_components);
        gameMap.setMap(new_map);
        gameMap.setWalls(new_walls);
        gameMap.setEmptySpaces(new_emptySpaces);
        gameMap.setCoins(new_coins);
        gameMap.setPowerPellets(new_powerPellet);

        assertEquals(gameMap.getMap(), new_map);
        assertEquals(gameMap.getWalls(), new_walls);
        assertEquals(gameMap.getEmptySpaces(), new_emptySpaces);
        assertEquals(gameMap.getCoins(), new_coins);
        assertEquals(gameMap.getPowerPellets(), new_powerPellet);
        assertEquals(gameMap.getMapComponents(), new_components);
    }
}
