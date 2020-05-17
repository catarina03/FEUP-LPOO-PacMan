package g11.controller;

import g11.model.GameData;
import g11.model.GhostState;
import g11.model.Orientation;
import g11.model.Position;
import g11.model.elements.Ghost;

import java.util.ArrayList;

public class GhostControllerClyde extends GhostController {
    public GhostControllerClyde() { super(GhostState.SCATTER, true); }

    public void update(GameData gameData, long elapsedtime, int step) {
        Ghost ghost = gameData.getGhosts().get(3);

        if (ghost.getPosition().equals(new Position(13,14))) // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
            setStarting(false);

        setState( isStarting() ? GhostState.CHASE : setStatetime(elapsedtime, ghost));

        if (elapsedtime > 10000){
            switch (getState()){
                case SCATTER:
                    // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                    calculateAndStep(gameData, ghost, false, GhostState.SCATTER, step);
                    break;
                case CHASE:
                    // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                    // atualiza posição de target
                    if (isStarting()) ghost.setTarget(new Position(13,14)); // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
                    else ghost.setTarget(getTarget(gameData));

                    calculateAndStep(gameData, ghost, true, GhostState.CHASE, step);
                    break;
            }
        }
    }

    @Override
    public Position getTarget(GameData gameData) {
        return gameData.getPacMan().getPosition().distance(gameData.getGhosts().get(3).getPosition()) >= 8 ? gameData.getPacMan().getPosition() : gameData.getGhosts().get(3).getScatterTarget();
    }
}
