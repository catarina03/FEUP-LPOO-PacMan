package g11.controller;

import g11.controller.ghosts.GhostController;
import g11.controller.ghosts.GhostState;
import g11.controller.ghosts.TargetStrategy;
import g11.controller.ghosts.states.GhostStateChase;
import g11.controller.ghosts.strategies.TargetStrategyBlinky;
import g11.model.GameData;
import g11.model.OrientationEnumeration;
import g11.model.Position;
import g11.model.elements.Ghost;
import g11.model.elements.ghosts.Blinky;
import org.junit.Test;
import org.mockito.Mock;
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
}
