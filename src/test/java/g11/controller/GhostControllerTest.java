package g11.controller;

import g11.controller.ghosts.GhostController;
import g11.controller.ghosts.states.GhostStateChase;
import g11.controller.ghosts.strategies.TargetStrategyBlinky;
import g11.controller.ghosts.strategies.TargetStrategyInky;
import g11.model.GameData;
import g11.model.Map;
import g11.model.OrientationEnumeration;
import g11.model.Position;
import g11.model.elements.Ghost;
import g11.model.elements.ghosts.Blinky;
import g11.model.elements.ghosts.Inky;
import g11.model.elements.map.Coin;
import g11.model.elements.map.EmptySpace;
import g11.model.elements.map.Gate;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GhostControllerTest {
    @Test
    public void update(){
        Blinky blinky = Mockito.mock(Blinky.class);
        TargetStrategyBlinky strategyBlinky = Mockito.mock(TargetStrategyBlinky.class);
        GameData gameData = Mockito.mock(GameData.class);
        GhostStateChase ghostStateChase = Mockito.mock(GhostStateChase.class);
        GhostController ghostController = new GhostController(false, blinky, strategyBlinky, 10);

        ghostController.setGhostState(ghostStateChase);

        ghostController.update(gameData, 4, 20);

        Mockito.verify(ghostStateChase, Mockito.times(1)).update(gameData, 4, 20);
        Mockito.verify(ghostStateChase, Mockito.times(1)).calculateAndStep(gameData, 4);
    }

    @Test
    public void ChooseOrientation(){
        Blinky blinky = Mockito.mock(Blinky.class);
        TargetStrategyBlinky strategyBlinky = Mockito.mock(TargetStrategyBlinky.class);
        GameData gameData = Mockito.mock(GameData.class);
        Position position = Mockito.mock(Position.class);
        Position position2 = Mockito.mock(Position.class);
        GhostController ghostController = new GhostController(false, blinky, strategyBlinky, 10);

        OrientationEnumeration o1 = OrientationEnumeration.UP;
        OrientationEnumeration o2 = OrientationEnumeration.DOWN;

        ArrayList<OrientationEnumeration> orientationEnumerations = new ArrayList<>();
        orientationEnumerations.add(o1);

        ArrayList<Ghost> ghosts = new ArrayList<>();
        ghosts.add(blinky);

        Mockito.when(blinky.getPosition()).thenReturn(position);
        Mockito.when(blinky.getTarget()).thenReturn(position);
        Mockito.when(position.getX()).thenReturn(10);
        Mockito.when(position.getY()).thenReturn(10);
        Mockito.when(position2.getX()).thenReturn(10);
        Mockito.when(position2.getY()).thenReturn(9);
        Mockito.when(gameData.getGhosts()).thenReturn(ghosts);
        Mockito.when(position.nextPositionWithOrientation(OrientationEnumeration.UP)).thenReturn(position2);
        Mockito.when(position.nextPositionWithOrientation(OrientationEnumeration.DOWN)).thenReturn(position);
        Mockito.when(position.distance(position)).thenReturn(100.0);
        Mockito.when(position2.distance(position)).thenReturn(100.0);

        assertEquals(ghostController.chooseOrientation(orientationEnumerations), OrientationEnumeration.UP);

        orientationEnumerations.add(o2);

        assertEquals(ghostController.chooseOrientation(orientationEnumerations), OrientationEnumeration.UP);

        Mockito.when(position.distance(position)).thenReturn(1000.0);
        Mockito.when(position2.distance(position)).thenReturn(100.0);

        assertEquals(ghostController.chooseOrientation(orientationEnumerations), OrientationEnumeration.UP);
    }

    @Test
    public void getAvailableOrientations(){
        GameData gameData = Mockito.mock(GameData.class);
        Map map = Mockito.mock(Map.class);
        Inky inky = Mockito.mock(Inky.class);
        TargetStrategyInky targetStrategyInky = Mockito.mock(TargetStrategyInky.class);
        Position position = Mockito.mock(Position.class);
        EmptySpace emptySpace = Mockito.mock(EmptySpace.class);
        Coin coin = Mockito.mock(Coin.class);
        Gate gate = Mockito.mock(Gate.class);
        GhostController ghostController = new GhostController(true, inky, targetStrategyInky, 10);

        OrientationEnumeration o1 = OrientationEnumeration.UP;
        OrientationEnumeration o2 = OrientationEnumeration.DOWN;
        OrientationEnumeration o3 = OrientationEnumeration.LEFT;
        OrientationEnumeration o4 = OrientationEnumeration.RIGHT;

        ArrayList<OrientationEnumeration> orientationEnumerations = new ArrayList<>();
        orientationEnumerations.add(o3);
        orientationEnumerations.add(o4);

        ArrayList<Position> unturnable = new ArrayList<>();
        unturnable.add(position);

        ArrayList<EmptySpace> emptySpaces = new ArrayList<>();
        emptySpaces.add(emptySpace);

        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(coin);

        ArrayList<Gate> gates = new ArrayList<>();
        gates.add(gate);

        Mockito.when(gameData.getMap()).thenReturn(map);
        Mockito.when(position.getX()).thenReturn(10);
        Mockito.when(position.getY()).thenReturn(11);
        Mockito.when(map.getUnturnable()).thenReturn(unturnable);
        Mockito.when(inky.getPosition()).thenReturn(position);
        Mockito.when(inky.getOrientationEnumeration()).thenReturn(o3);
        Mockito.when(emptySpace.getPosition()).thenReturn(position);
        Mockito.when(map.getEmptySpaces()).thenReturn(emptySpaces);;
        Mockito.when(map.getCoins()).thenReturn(coins);
        Mockito.when(coin.getPosition()).thenReturn(position);
        Mockito.when(map.getGates()).thenReturn(gates);
        Mockito.when(inky.getPosition(OrientationEnumeration.LEFT)).thenReturn(position);
        Mockito.when(inky.getPosition(OrientationEnumeration.RIGHT)).thenReturn(position);
        Mockito.when(position.up()).thenReturn(position);
        Mockito.when(position.down()).thenReturn(position);
        Mockito.when(gate.getPosition()).thenReturn(position);

        ArrayList<OrientationEnumeration> result = ghostController.getAvailableOrientations(gameData);
        assertEquals(result.get(0), OrientationEnumeration.LEFT);

        Mockito.when(inky.getOrientationEnumeration()).thenReturn(o4);

        result = ghostController.getAvailableOrientations(gameData);
        assertEquals(result.get(0), OrientationEnumeration.RIGHT);

        unturnable.clear();
        Mockito.when(inky.getOrientationEnumeration()).thenReturn(o1);

        result = ghostController.getAvailableOrientations(gameData);
        assertEquals(result.get(0), OrientationEnumeration.LEFT);
        assertEquals(result.get(1), OrientationEnumeration.RIGHT);
        assertEquals(result.get(2), OrientationEnumeration.LEFT);
        assertEquals(result.get(3), OrientationEnumeration.RIGHT);
        assertEquals(result.get(4), OrientationEnumeration.UP);

        Mockito.when(inky.getOrientationEnumeration()).thenReturn(o2);

        result = ghostController.getAvailableOrientations(gameData);
        assertEquals(result.get(0), OrientationEnumeration.LEFT);
        assertEquals(result.get(1), OrientationEnumeration.RIGHT);
        assertEquals(result.get(2), OrientationEnumeration.LEFT);
        assertEquals(result.get(3), OrientationEnumeration.RIGHT);
        assertEquals(result.get(4), OrientationEnumeration.DOWN);
    }
}
