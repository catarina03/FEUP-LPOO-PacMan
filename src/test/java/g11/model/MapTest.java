package g11.model;

import g11.model.elements.*;
import g11.model.elements.map.Coin;
import g11.model.elements.map.EmptySpace;
import g11.model.elements.map.PowerPellet;
import g11.model.elements.map.Wall;
import g11.model.elements.map.Gate;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MapTest {

    @Test
    public void MapCreation() {
        ArrayList<String> map = new ArrayList<>();
        String s1 = "#ece#";
        String s2 = "#M$e#";
        map.add(s1);
        map.add(s2);

        ArrayList<Wall> walls = new ArrayList<>();
        Wall w1 = Mockito.mock(Wall.class);
        Wall w2 = Mockito.mock(Wall.class);
        walls.add(w1);
        walls.add(w2);

        ArrayList<EmptySpace> emptySpaces = new ArrayList<>();
        EmptySpace es1 = Mockito.mock(EmptySpace.class);
        EmptySpace es2 = Mockito.mock(EmptySpace.class);
        emptySpaces.add(es1);
        emptySpaces.add(es2);

        ArrayList<Coin> coins = new ArrayList<>();
        Coin c1  = Mockito.mock(Coin.class);
        Coin c2  = Mockito.mock(Coin.class);
        coins.add(c1);
        coins.add(c2);

        ArrayList<PowerPellet> powerPellet = new ArrayList<>();
        PowerPellet pp1 = Mockito.mock(PowerPellet.class);
        PowerPellet pp2 = Mockito.mock(PowerPellet.class);
        powerPellet.add(pp1);
        powerPellet.add(pp2);

        ArrayList<Gate> gates = new ArrayList<>();
        Gate g1 = Mockito.mock(Gate.class);
        Gate g2 = Mockito.mock(Gate.class);
        gates.add(g1);
        gates.add(g2);

        ArrayList<MapComponent> components = new ArrayList<>();
        components.add(w1);
        components.add(w1);
        components.add(es1);
        components.add(es2);
        components.add(c1);
        components.add(c2);
        components.add(pp1);
        components.add(pp2);
        components.add(g1);
        components.add(g2);

        ArrayList<Position> unturnable = new ArrayList<>();

        Map gameMap = new Map(map, walls, emptySpaces, coins, powerPellet, gates, components, unturnable);

        assertEquals(gameMap.getMap(), map);
        assertEquals(gameMap.getWalls(), walls);
        assertEquals(gameMap.getEmptySpaces(), emptySpaces);
        assertEquals(gameMap.getCoins(), coins);
        assertEquals(gameMap.getPowerPellets(), powerPellet);
        assertEquals(gameMap.getGates(), gates);
        assertEquals(gameMap.getMapComponents(), components);
    }

    @Test
    public void MapChange() {
        ArrayList<String> map = new ArrayList<>();
        String s1 = "#ece#";
        String s2 = "#M$e#";
        map.add(s1);
        map.add(s2);

        ArrayList<Wall> walls = new ArrayList<>();
        Wall w1 = Mockito.mock(Wall.class);
        Wall w2 = Mockito.mock(Wall.class);
        walls.add(w1);
        walls.add(w2);

        ArrayList<EmptySpace> emptySpaces = new ArrayList<>();
        EmptySpace es1 = Mockito.mock(EmptySpace.class);
        EmptySpace es2 = Mockito.mock(EmptySpace.class);
        emptySpaces.add(es1);
        emptySpaces.add(es2);

        ArrayList<Coin> coins = new ArrayList<>();
        Coin c1  = Mockito.mock(Coin.class);
        Coin c2  = Mockito.mock(Coin.class);
        coins.add(c1);
        coins.add(c2);

        ArrayList<PowerPellet> powerPellet = new ArrayList<>();
        PowerPellet pp1 = Mockito.mock(PowerPellet.class);
        PowerPellet pp2 = Mockito.mock(PowerPellet.class);
        powerPellet.add(pp1);
        powerPellet.add(pp2);

        ArrayList<Gate> gates = new ArrayList<>();
        Gate g1 = Mockito.mock(Gate.class);
        Gate g2 = Mockito.mock(Gate.class);
        gates.add(g1);
        gates.add(g2);

        ArrayList<MapComponent> components = new ArrayList<>();
        components.add(w1);
        components.add(w1);
        components.add(es1);
        components.add(es2);
        components.add(c1);
        components.add(c2);
        components.add(pp1);
        components.add(pp2);
        components.add(g1);
        components.add(g2);

        ArrayList<Position> unturnable = new ArrayList<>();

        Map gameMap = new Map(map, walls, emptySpaces, coins, powerPellet, gates, components, unturnable);

        ArrayList<String> new_map = new ArrayList<>();
        String s3 = "#Mce#";
        String s4 = "#e$e#";
        map.add(s3);
        map.add(s4);

        ArrayList<Wall> new_walls = new ArrayList<>();
        Wall w3 = Mockito.mock(Wall.class);
        Wall w4 = Mockito.mock(Wall.class);
        walls.add(w1);
        walls.add(w2);

        ArrayList<EmptySpace> new_emptySpaces = new ArrayList<>();
        EmptySpace es3 = Mockito.mock(EmptySpace.class);
        EmptySpace es4 = Mockito.mock(EmptySpace.class);
        emptySpaces.add(es3);
        emptySpaces.add(es4);

        ArrayList<Coin> new_coins = new ArrayList<>();
        Coin c3  = Mockito.mock(Coin.class);
        Coin c4  = Mockito.mock(Coin.class);
        coins.add(c3);
        coins.add(c4);

        ArrayList<PowerPellet> new_powerPellet = new ArrayList<>();
        PowerPellet pp3 = Mockito.mock(PowerPellet.class);
        PowerPellet pp4 = Mockito.mock(PowerPellet.class);
        powerPellet.add(pp3);
        powerPellet.add(pp4);

        ArrayList<Gate> new_gates = new ArrayList<>();
        Gate g3 = Mockito.mock(Gate.class);
        Gate g4 = Mockito.mock(Gate.class);
        gates.add(g3);
        gates.add(g4);

        ArrayList<MapComponent> new_components = new ArrayList<>();
        components.add(w3);
        components.add(w4);
        components.add(es3);
        components.add(es4);
        components.add(c3);
        components.add(c4);
        components.add(pp3);
        components.add(pp4);
        components.add(g3);
        components.add(g4);

        gameMap.setMapComponents(new_components);
        gameMap.setMap(new_map);
        gameMap.setWalls(new_walls);
        gameMap.setEmptySpaces(new_emptySpaces);
        gameMap.setCoins(new_coins);
        gameMap.setPowerPellets(new_powerPellet);
        gameMap.setGates(new_gates);

        assertEquals(gameMap.getMap(), new_map);
        assertEquals(gameMap.getWalls(), new_walls);
        assertEquals(gameMap.getEmptySpaces(), new_emptySpaces);
        assertEquals(gameMap.getCoins(), new_coins);
        assertEquals(gameMap.getPowerPellets(), new_powerPellet);
        assertEquals(gameMap.getGates(), new_gates);
        assertEquals(gameMap.getMapComponents(), new_components);
    }
}
