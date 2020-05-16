package g11.controller;

import g11.model.GameData;
import g11.model.GhostState;
import g11.model.Orientation;
import g11.model.Position;
import g11.model.elements.Ghost;

import java.util.ArrayList;

public class GhostControllerClyde extends GhostController {
    private GhostState state;
    private boolean starting;

    public GhostControllerClyde() {
        this.state = GhostState.SCATTER;
        starting = true;
    }

    public void update(GameData gameData, long elapsedtime) {
        ArrayList<Orientation> availableOris;
        Ghost ghost = gameData.getGhosts().get(3);

        if (ghost.getPosition().equals(new Position(13,14))) // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
            starting = false;

        if (starting)
            state = GhostState.CHASE;
        else
            state = setStatetime(elapsedtime);

        if (elapsedtime > 10000){
            switch (state){
                case SCATTER:
                    // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                    availableOris = getAvailableOrientations(gameData, ghost, false);
                    if (availableOris.size() > 0){
                        ghost.setOrientation(chooseOrientation(availableOris, ghost, true));
                    }
                    ghost.moveDirection();
                    break;
                case CHASE:
                    // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                    // atualiza posição de target
                    if (starting)
                        ghost.setTarget(new Position(13,14)); // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
                    else
                        ghost.setTarget(getTarget(gameData));

                    availableOris = getAvailableOrientations(gameData, ghost, true);
                    if (availableOris.size() > 0) {
                        ghost.setOrientation(chooseOrientation(availableOris, ghost, false));
                    }
                    ghost.moveDirection();

                    break;
            }
        }
    }

    @Override
    public Position getTarget(GameData gameData) {
        return gameData.getPacMan().getPosition().distance(gameData.getGhosts().get(3).getPosition()) >= 8 ? gameData.getPacMan().getPosition() : gameData.getGhosts().get(3).getScatterTarget();
    }
}
