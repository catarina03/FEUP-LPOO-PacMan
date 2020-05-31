package g11.controller;

import g11.controller.ghosts.GhostController;
import g11.controller.ghosts.GhostState;
import g11.controller.ghosts.TargetStrategy;
import g11.controller.ghosts.states.GhostStateChase;
import g11.controller.ghosts.strategies.TargetStrategyBlinky;
import g11.model.GameData;
import g11.model.elements.ghosts.Blinky;
import org.junit.Test;
import org.mockito.Mockito;

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


}
