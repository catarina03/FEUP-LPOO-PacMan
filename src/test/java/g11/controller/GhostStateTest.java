package g11.controller;

import g11.controller.ghosts.GhostController;
import g11.controller.ghosts.TargetStrategy;
import g11.controller.ghosts.states.*;
import g11.model.*;
import g11.model.elements.ghosts.Inky;
import g11.model.elements.map.Gate;
import g11.model.elements.map.PowerPellet;
import javafx.geometry.Pos;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class GhostStateTest {

    @Test
    public void GhostStateChaseUpdate(){
        GameData gameData = Mockito.mock(GameData.class);
        Map map = Mockito.mock(Map.class);
        PowerPellet powerPellet = Mockito.mock(PowerPellet.class);
        GhostController ghostController = Mockito.mock(GhostController.class);
        TargetStrategy targetStrategy = Mockito.mock(TargetStrategy.class);
        Inky inky = Mockito.mock(Inky.class);
        GhostStateChase ghostStateChase = new GhostStateChase(ghostController, targetStrategy, 2);

        ArrayList<PowerPellet> powerPellets = new ArrayList<>();
        powerPellets.add(powerPellet);

        Mockito.when(gameData.getMap()).thenReturn(map);
        Mockito.when(map.getPowerPellets()).thenReturn(powerPellets);
        Mockito.when(ghostController.getGhost()).thenReturn(inky);
        ghostStateChase.setActivePPs(2);

        ghostStateChase.update(gameData, 1, 1);

        Mockito.verify(inky, Mockito.times(1)).setState(GhostStateEnumeration.FRIGHTENED);
        Mockito.verify(ghostController, Mockito.times(2)).setChangeOrientation(true);
        Mockito.verify(ghostController, Mockito.times(1)).changeState(any(GhostStateFrightened.class));
        assertTrue(ghostStateChase.isScatterTime(1));
        assertEquals(1, ghostStateChase.getActivePPs());
        Mockito.verify(ghostController, Mockito.times(1)).changeState(any(GhostStateScatter.class));
    }

    @Test
    public void GhostStateChaseCalculateAndStep() {
        GhostController ghostController = Mockito.mock(GhostController.class);
        Inky inky = Mockito.mock(Inky.class);
        TargetStrategy targetStrategy = Mockito.mock(TargetStrategy.class);
        GameData gameData = Mockito.mock(GameData.class);
        Position position = Mockito.mock(Position.class);
        GhostStateChase ghostStateChase = new GhostStateChase(ghostController, targetStrategy, 1);

        Mockito.when(ghostController.getGhost()).thenReturn(inky);
        Mockito.when(ghostController.isChangeOrientation()).thenReturn(true);
        Mockito.when(inky.getOrientationEnumeration()).thenReturn(OrientationEnumeration.LEFT);
        Mockito.when(inky.getOrientationEnumeration().getOpposite()).thenReturn(OrientationEnumeration.RIGHT);
        Mockito.when(position.getX()).thenReturn(5);
        Mockito.when(position.getX()).thenReturn(6);
        Mockito.when(targetStrategy.getTarget(gameData)).thenReturn(position);

        ghostStateChase.calculateAndStep(gameData, 4);

        Mockito.verify(inky, Mockito.times(1)).setTarget(any(Position.class));
        Mockito.verify(targetStrategy, Mockito.times(1)).getTarget(gameData);
        Mockito.verify(ghostController, Mockito.times(1)).isChangeOrientation();
        Mockito.verify(inky, Mockito.times(1)).setOrientationEnumeration(any(OrientationEnumeration.class));
        Mockito.verify(ghostController, Mockito.times(1)).setChangeOrientation(false);

        ArrayList<OrientationEnumeration> orientationEnumerations = new ArrayList<>();
        OrientationEnumeration up = OrientationEnumeration.UP;
        OrientationEnumeration down = OrientationEnumeration.DOWN;
        OrientationEnumeration left = OrientationEnumeration.LEFT;
        OrientationEnumeration right = OrientationEnumeration.RIGHT;
        orientationEnumerations.add(up);
        orientationEnumerations.add(down);
        orientationEnumerations.add(left);
        orientationEnumerations.add(right);

        Mockito.when(ghostController.isChangeOrientation()).thenReturn(false);
        Mockito.when(ghostController.getAvailableOrientations(gameData)).thenReturn(orientationEnumerations);

        ghostStateChase.calculateAndStep(gameData, 4);

        Mockito.verify(ghostController, Mockito.times(1)).getAvailableOrientations(gameData);
        Mockito.verify(ghostController, Mockito.times(1)).chooseOrientation(ghostController.getAvailableOrientations(gameData));
        Mockito.verify(inky, Mockito.times(1)).setOrientationEnumeration(any(OrientationEnumeration.class));
    }

    @Test
    public void GhostStateEatenUpdate(){
        GameData gameData = Mockito.mock(GameData.class);
        Map map = Mockito.mock(Map.class);
        PowerPellet powerPellet = Mockito.mock(PowerPellet.class);
        GhostController ghostController = Mockito.mock(GhostController.class);
        TargetStrategy targetStrategy = Mockito.mock(TargetStrategy.class);
        Inky inky = Mockito.mock(Inky.class);
        Gate gate1 = Mockito.mock(Gate.class);
        Gate gate2 = Mockito.mock(Gate.class);
        Position position = Mockito.mock(Position.class);
        Position position2 = Mockito.mock(Position.class);
        GhostStateEaten ghostStateEaten = new GhostStateEaten(ghostController, targetStrategy, 2);

        ArrayList<PowerPellet> powerPellets = new ArrayList<>();
        powerPellets.add(powerPellet);

        ArrayList<Gate> gates = new ArrayList<>();
        gates.add(gate1);
        gates.add(gate2);

        ArrayList<OrientationEnumeration> orientationEnumerations = new ArrayList<>();
        OrientationEnumeration up = OrientationEnumeration.UP;
        OrientationEnumeration down = OrientationEnumeration.DOWN;
        OrientationEnumeration left = OrientationEnumeration.LEFT;
        OrientationEnumeration right = OrientationEnumeration.RIGHT;
        orientationEnumerations.add(up);
        orientationEnumerations.add(down);
        orientationEnumerations.add(left);
        orientationEnumerations.add(right);

        Mockito.when(ghostController.isChangeOrientation()).thenReturn(true);
        Mockito.when(map.getGates()).thenReturn(gates);
        Mockito.when(position.getX()).thenReturn(5);
        Mockito.when(position.getX()).thenReturn(6);
        Mockito.when(position2.getX()).thenReturn(5);
        Mockito.when(position2.getX()).thenReturn(5);
        Mockito.when(inky.getPosition()).thenReturn(position);
        Mockito.when(gate1.getPosition()).thenReturn(position2);
        Mockito.when(gate2.getPosition()).thenReturn(position2);
        Mockito.when(position2.up()).thenReturn(position);
        Mockito.when(gameData.getMap()).thenReturn(map);
        Mockito.when(map.getPowerPellets()).thenReturn(powerPellets);
        Mockito.when(ghostController.getGhost()).thenReturn(inky);
        ghostStateEaten.setActivePPs(2);

        ghostStateEaten.update(gameData, 1, 1);

        Mockito.verify(ghostController, Mockito.times(1)).changeState(any(GhostStateEnteringHouse.class));
        Mockito.verify(ghostController, Mockito.times(1)).setAccessingHouse(true);
        assertEquals(ghostStateEaten.getActivePPs(), 1);
        Mockito.verify(ghostController, Mockito.times(1)).isChangeOrientation();
        Mockito.verify(ghostController, Mockito.times(1)).setChangeOrientation(false);
    }


    @Test
    public void GhostStateEatenCalculateAndStep() {
        GhostController ghostController = Mockito.mock(GhostController.class);
        Inky inky = Mockito.mock(Inky.class);
        TargetStrategy targetStrategy = Mockito.mock(TargetStrategy.class);
        GameData gameData = Mockito.mock(GameData.class);
        Position position = Mockito.mock(Position.class);
        Position position2 = Mockito.mock(Position.class);
        Gate gate1 = Mockito.mock(Gate.class);
        Gate gate2 = Mockito.mock(Gate.class);
        Map map = Mockito.mock(Map.class);
        GhostStateEaten ghostStateEaten = new GhostStateEaten(ghostController, targetStrategy, 1);

        ArrayList<Gate> gates = new ArrayList<>();
        gates.add(gate1);
        gates.add(gate2);

        ArrayList<OrientationEnumeration> orientationEnumerations = new ArrayList<>();
        OrientationEnumeration up = OrientationEnumeration.UP;
        OrientationEnumeration down = OrientationEnumeration.DOWN;
        OrientationEnumeration left = OrientationEnumeration.LEFT;
        OrientationEnumeration right = OrientationEnumeration.RIGHT;
        orientationEnumerations.add(up);
        orientationEnumerations.add(down);
        orientationEnumerations.add(left);
        orientationEnumerations.add(right);

        Mockito.when(ghostController.getAvailableOrientations(gameData)).thenReturn(orientationEnumerations);
        Mockito.when(map.getGates()).thenReturn(gates);
        Mockito.when(gate1.getPosition()).thenReturn(position2);
        Mockito.when(position2.up()).thenReturn(position);
        Mockito.when(ghostController.getGhost()).thenReturn(inky);
        Mockito.when(ghostController.isChangeOrientation()).thenReturn(true);
        Mockito.when(inky.getOrientationEnumeration()).thenReturn(OrientationEnumeration.LEFT);
        Mockito.when(inky.getOrientationEnumeration().getOpposite()).thenReturn(OrientationEnumeration.RIGHT);
        Mockito.when(position.getX()).thenReturn(5);
        Mockito.when(position.getX()).thenReturn(6);
        Mockito.when(position.getX()).thenReturn(5);
        Mockito.when(position.getX()).thenReturn(5);
        Mockito.when(gameData.getMap()).thenReturn(map);
        Mockito.when(ghostController.chooseOrientation(orientationEnumerations)).thenReturn(OrientationEnumeration.LEFT);
        Mockito.when(targetStrategy.getTarget(gameData)).thenReturn(position);

        ghostStateEaten.calculateAndStep(gameData, 3);

        Mockito.verify(inky, Mockito.times(1)).setTarget(any(Position.class));
        Mockito.verify(position2, Mockito.times(1)).up();
        Mockito.verify(ghostController, Mockito.times(1)).getAvailableOrientations(gameData);
        Mockito.verify(ghostController, Mockito.times(1)).chooseOrientation(ghostController.getAvailableOrientations(gameData));
        Mockito.verify(inky, Mockito.times(1)).setOrientationEnumeration(any(OrientationEnumeration.class));
        Mockito.verify(inky, Mockito.times(1)).moveDirection();
    }

    @Test
    public void GhostStateEnteringHouseUpdate(){
        GameData gameData = Mockito.mock(GameData.class);
        Map map = Mockito.mock(Map.class);
        PowerPellet powerPellet = Mockito.mock(PowerPellet.class);
        GhostController ghostController = Mockito.mock(GhostController.class);
        TargetStrategy targetStrategy = Mockito.mock(TargetStrategy.class);
        Inky inky = Mockito.mock(Inky.class);
        Position position1 = Mockito.mock(Position.class);
        Position position2 = Mockito.mock(Position.class);
        Position position3 = Mockito.mock(Position.class);
        Gate gate1 = Mockito.mock(Gate.class);
        Gate gate2 = Mockito.mock(Gate.class);
        GhostStateEnteringHouse ghostStateEnteringHouse = new GhostStateEnteringHouse(ghostController, targetStrategy, 2);

        ArrayList<PowerPellet> powerPellets = new ArrayList<>();
        powerPellets.add(powerPellet);

        ArrayList<Gate> gates = new ArrayList<>();
        gates.add(gate1);
        gates.add(gate2);

        Mockito.when(position1.getX()).thenReturn(5);
        Mockito.when(position1.getY()).thenReturn(6);
        Mockito.when(position2.getX()).thenReturn(5);
        Mockito.when(position2.getY()).thenReturn(7);
        Mockito.when(position3.getX()).thenReturn(5);
        Mockito.when(position3.getY()).thenReturn(8);
        Mockito.when(position1.down()).thenReturn(position2);
        Mockito.when(position2.down()).thenReturn(position3);
        Mockito.when(gameData.getMap()).thenReturn(map);
        Mockito.when(map.getGates()).thenReturn(gates);
        Mockito.when(gate1.getPosition()).thenReturn(position1);
        Mockito.when(map.getPowerPellets()).thenReturn(powerPellets);
        Mockito.when(ghostController.getGhost()).thenReturn(inky);
        Mockito.when(inky.getPosition()).thenReturn(position3);
        ghostStateEnteringHouse.setActivePPs(2);

        ghostStateEnteringHouse.update(gameData, 1, 1);

        Mockito.verify(ghostController, Mockito.times(1)).changeState(any(GhostStateExitingHouse.class));
        Mockito.verify(inky, Mockito.times(1)).setState(GhostStateEnumeration.CHASE);
        assertEquals(1, ghostStateEnteringHouse.getActivePPs());
    }

    @Test
    public void GhostStateExitingHouseUpdate(){
        GameData gameData = Mockito.mock(GameData.class);
        Map map = Mockito.mock(Map.class);
        PowerPellet powerPellet = Mockito.mock(PowerPellet.class);
        GhostController ghostController = Mockito.mock(GhostController.class);
        TargetStrategy targetStrategy = Mockito.mock(TargetStrategy.class);
        Inky inky = Mockito.mock(Inky.class);
        Position position1 = Mockito.mock(Position.class);
        Position position2 = Mockito.mock(Position.class);
        Gate gate1 = Mockito.mock(Gate.class);
        Gate gate2 = Mockito.mock(Gate.class);
        GhostStateExitingHouse ghostStateExitingHouse = new GhostStateExitingHouse(ghostController, targetStrategy, 2);

        ArrayList<PowerPellet> powerPellets = new ArrayList<>();
        powerPellets.add(powerPellet);

        ArrayList<Gate> gates = new ArrayList<>();
        gates.add(gate1);
        gates.add(gate2);

        Mockito.when(position2.getX()).thenReturn(5);
        Mockito.when(position2.getY()).thenReturn(6);
        Mockito.when(position1.getX()).thenReturn(5);
        Mockito.when(position1.getY()).thenReturn(7);
        Mockito.when(position1.up()).thenReturn(position2);
        Mockito.when(gameData.getMap()).thenReturn(map);
        Mockito.when(map.getGates()).thenReturn(gates);
        Mockito.when(inky.getPosition()).thenReturn(position2);
        Mockito.when(gate1.getPosition()).thenReturn(position1);
        Mockito.when(map.getPowerPellets()).thenReturn(powerPellets);
        Mockito.when(ghostController.getGhost()).thenReturn(inky);
        ghostStateExitingHouse.setActivePPs(2);

        ghostStateExitingHouse.update(gameData, 1, 1);

        Mockito.verify(ghostController, Mockito.times(1)).setAccessingHouse(false);
        Mockito.verify(ghostController, Mockito.times(1)).changeState(any(GhostStateScatter.class));
        assertEquals(1, ghostStateExitingHouse.getActivePPs());

        ghostStateExitingHouse.update(gameData, 1, 0);

        Mockito.verify(ghostController, Mockito.times(1)).changeState(any(GhostStateChase.class));
    }

    @Test
    public void GhostStateFrightenedUpdate(){
        GameData gameData = Mockito.mock(GameData.class);
        Map map = Mockito.mock(Map.class);
        PowerPellet powerPellet = Mockito.mock(PowerPellet.class);
        GhostController ghostController = Mockito.mock(GhostController.class);
        TargetStrategy targetStrategy = Mockito.mock(TargetStrategy.class);
        Inky inky = Mockito.mock(Inky.class);
        Position position1 = Mockito.mock(Position.class);
        Position position2 = Mockito.mock(Position.class);
        Gate gate1 = Mockito.mock(Gate.class);
        Gate gate2 = Mockito.mock(Gate.class);
        GhostStateFrightened ghostStateFrightened = new GhostStateFrightened(ghostController, targetStrategy, 2);

        ArrayList<PowerPellet> powerPellets = new ArrayList<>();
        powerPellets.add(powerPellet);

        ArrayList<Gate> gates = new ArrayList<>();
        gates.add(gate1);
        gates.add(gate2);

        ghostStateFrightened.setTicksToEnd(51);
        Mockito.when(position2.getX()).thenReturn(5);
        Mockito.when(position2.getY()).thenReturn(6);
        Mockito.when(position1.getX()).thenReturn(5);
        Mockito.when(position1.getY()).thenReturn(7);
        Mockito.when(position1.up()).thenReturn(position2);
        Mockito.when(gameData.getMap()).thenReturn(map);
        Mockito.when(map.getGates()).thenReturn(gates);
        Mockito.when(inky.getPosition()).thenReturn(position2);
        Mockito.when(gate1.getPosition()).thenReturn(position1);
        Mockito.when(map.getPowerPellets()).thenReturn(powerPellets);
        Mockito.when(ghostController.getGhost()).thenReturn(inky);
        Mockito.when(ghostController.getTargetStrategy()).thenReturn(targetStrategy);
        ghostStateFrightened.setActivePPs(2);

        ghostStateFrightened.update(gameData, 1, 1);

        Mockito.verify(inky, Mockito.times(1)).setState(GhostStateEnumeration.CHASE);
        assertEquals(ghostStateFrightened.getActivePPs(), 1);

        ghostStateFrightened.setTicksToEnd(41);
        ghostStateFrightened.update(gameData, 1, 1);

        Mockito.verify(inky, Mockito.times(1)).setState(GhostStateEnumeration.FRIGHTENED);

        ghostStateFrightened.setTicksToEnd(1);
        ghostStateFrightened.update(gameData, 1, 1);

        Mockito.verify(inky, Mockito.times(2)).setState(GhostStateEnumeration.CHASE);
        Mockito.verify(ghostController, Mockito.times(1)).changeState(any(GhostStateChase.class));
    }

    @Test
    public void GhostStateScatterUpdate(){
        GameData gameData = Mockito.mock(GameData.class);
        Map map = Mockito.mock(Map.class);
        PowerPellet powerPellet = Mockito.mock(PowerPellet.class);
        GhostController ghostController = Mockito.mock(GhostController.class);
        TargetStrategy targetStrategy = Mockito.mock(TargetStrategy.class);
        Inky inky = Mockito.mock(Inky.class);
        GhostStateScatter ghostStateScatter = new GhostStateScatter(ghostController, targetStrategy, 2);

        ArrayList<PowerPellet> powerPellets = new ArrayList<>();
        powerPellets.add(powerPellet);

        Mockito.when(gameData.getMap()).thenReturn(map);
        Mockito.when(map.getPowerPellets()).thenReturn(powerPellets);
        Mockito.when(ghostController.getGhost()).thenReturn(inky);
        ghostStateScatter.setActivePPs(2);

        ghostStateScatter.update(gameData, 1, 0);

        Mockito.verify(inky, Mockito.times(1)).setState(GhostStateEnumeration.FRIGHTENED);
        Mockito.verify(ghostController, Mockito.times(2)).setChangeOrientation(true);
        Mockito.verify(ghostController, Mockito.times(1)).changeState(any(GhostStateFrightened.class));
        assertTrue(ghostStateScatter.isScatterTime(1));
        assertEquals(1, ghostStateScatter.getActivePPs());
        Mockito.verify(ghostController, Mockito.times(1)).changeState(any(GhostStateChase.class));
    }
}
