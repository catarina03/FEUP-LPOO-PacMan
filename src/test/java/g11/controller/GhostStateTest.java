package g11.controller;

import g11.controller.ghosts.GhostController;
import g11.controller.ghosts.TargetStrategy;
import g11.controller.ghosts.states.GhostStateChase;
import g11.controller.ghosts.states.GhostStateFrightened;
import g11.controller.ghosts.states.GhostStateScatter;
import g11.model.*;
import g11.model.elements.ghosts.Inky;
import g11.model.elements.map.PowerPellet;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

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
}
