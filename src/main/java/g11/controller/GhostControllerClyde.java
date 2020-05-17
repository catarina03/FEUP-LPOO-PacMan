/*
package g11.controller;

import g11.model.GameData;
import g11.model.GhostState;
import g11.model.Position;
import g11.model.elements.Ghost;

public class GhostControllerClyde extends GhostController {
    public GhostControllerClyde() { super(GhostState.SCATTER, true); }

    public void update(GameData gameData, long elapsedtime, int step, GhostState ghostState) {
        Ghost ghost = gameData.getGhosts().get(3);

        if (ghost.getPosition().equals(new Position(13,14))) // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
            setStarting(false);

        if (isStarting()) ghost.setState(GhostState.CHASE);
        else if (ghostState == GhostState.FRIGHTENED) ghost.setState(GhostState.FRIGHTENED);
        else ghost.setState(setStatetime(elapsedtime, ghost, gameData));

        if (elapsedtime > 10000){
            switch (ghost.getState()){
                case SCATTER:
                    // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                    ghost.setTarget(ghost.getScatterTarget());
                    calculateAndStep(gameData, ghost, false, step);
                    break;
                case CHASE:
                    // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                    // atualiza posição de target
                    if (isStarting()) ghost.setTarget(new Position(13,14)); // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
                    else ghost.setTarget(getTarget(gameData));

                    calculateAndStep(gameData, ghost, true, step);
                    break;
                case FRIGHTENED:
                    calculateAndStep(gameData, ghost, false, step);
                    break;
            }
        }
    }

    @Override
    public Position getTarget(GameData gameData) {
        return gameData.getPacMan().getPosition().distance(gameData.getGhosts().get(3).getPosition()) >= 8 ? gameData.getPacMan().getPosition() : gameData.getGhosts().get(3).getScatterTarget();
    }
}
*/
