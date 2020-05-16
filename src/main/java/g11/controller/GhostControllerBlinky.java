package g11.controller;

import g11.model.Position;
import g11.model.elements.Ghost;
import g11.model.GameData;
import g11.model.GhostState;
import g11.model.Orientation;

import java.util.ArrayList;

import static g11.model.Orientation.*;

public class GhostControllerBlinky extends GhostController {

    private GhostState state;

    public GhostControllerBlinky() {
        this.state = GhostState.SCATTER;
    }

    public void update(GameData gameData, long elapsedtime, int step) {
        state = setStatetime(elapsedtime);
        ArrayList<Orientation> availableOris;
        Ghost ghost = gameData.getGhosts().get(0);
        switch (state){
            case SCATTER:
                // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                calculateAndStep(gameData, ghost, false, true, step);
                break;
            case CHASE:
                // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                // atualiza posição de target
                ghost.setTarget(getTarget(gameData));
                calculateAndStep(gameData, ghost, false, false, step);
                break;
        }
    }

    @Override
    public Position getTarget(GameData gameData) {
        return gameData.getPacMan().getPosition();
    }

}
