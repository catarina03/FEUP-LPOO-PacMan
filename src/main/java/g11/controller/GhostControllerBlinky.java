package g11.controller;

import g11.model.Position;
import g11.model.elements.Ghost;
import g11.model.GameData;
import g11.model.GhostState;

public class GhostControllerBlinky extends GhostController {
    public GhostControllerBlinky() { super(GhostState.SCATTER); }

    public void update(GameData gameData, long elapsedtime, int step) {
        Ghost ghost = gameData.getGhosts().get(0);



        setState(setStatetime(elapsedtime, ghost));

        if (elapsedtime > 0) {
            switch (getState()) {
                case SCATTER:
                    // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                    calculateAndStep(gameData, ghost, false, GhostState.SCATTER, step);
                    break;
                case CHASE:
                    // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                    // atualiza posição de target
                    ghost.setTarget(getTarget(gameData));
                    calculateAndStep(gameData, ghost, false, GhostState.CHASE, step);
                    break;
                case FRIGHTENED:
                    break;
                case EATEN:
                    break;
            }
        }
    }

    @Override
    public Position getTarget(GameData gameData) {
        return gameData.getPacMan().getPosition();
    }

}
