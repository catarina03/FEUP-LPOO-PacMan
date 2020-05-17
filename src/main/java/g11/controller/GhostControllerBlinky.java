package g11.controller;

import g11.model.Position;
import g11.model.elements.Ghost;
import g11.model.GameData;
import g11.model.GhostState;

public class GhostControllerBlinky extends GhostController {
    public GhostControllerBlinky() { super(GhostState.SCATTER); }

    public void update(GameData gameData, long elapsedtime, int step, boolean frightened) {
        Ghost ghost = gameData.getGhosts().get(0);


        if (frightened) ghost.setState(GhostState.FRIGHTENED);
        else ghost.setState(setStatetime(elapsedtime, ghost, gameData));

        if (elapsedtime > 0) {
            switch (ghost.getState()) {
                case SCATTER:
                    // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                    ghost.setTarget(ghost.getScatterTarget());
                    calculateAndStep(gameData, ghost, false, step);
                    break;
                case CHASE:
                    // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                    // atualiza posição de target
                    ghost.setTarget(getTarget(gameData));
                    calculateAndStep(gameData, ghost, false, step);
                    break;
                case FRIGHTENED:
                    // não interessa o target
                    calculateAndStep(gameData, ghost, false, step);
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
