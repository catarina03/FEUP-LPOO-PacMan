/*
package g11.controller;

import g11.model.GameData;
import g11.model.GhostState;
import g11.model.Orientation;
import g11.model.Position;
import g11.model.elements.Ghost;

public class GhostControllerPinky extends GhostController {
    public GhostControllerPinky() { super(GhostState.SCATTER, true); }

    public void update(GameData gameData, long elapsedtime, int step, GhostState ghostState) {
        Ghost ghost = gameData.getGhosts().get(2);

        if (ghost.getPosition().equals(new Position(13,14))) // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
            setExitingHouse(false);

        if (isExitingHouse()) ghost.setState(GhostState.CHASE);
        else if (ghostState == GhostState.FRIGHTENED) ghost.setState(GhostState.FRIGHTENED);
        else ghost.setState(setStatetime(elapsedtime, ghost, gameData));

        if (elapsedtime > 0){
            switch (ghost.getState()){
                case SCATTER:
                    // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                    ghost.setTarget(ghost.getScatterTarget());
                    calculateAndStep(gameData, ghost, false, step);
                    break;
                case CHASE:
                    // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                    // atualiza posição de target
                    if (isExitingHouse()) ghost.setTarget(new Position(13,14));  // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
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
        Position pacman = gameData.getPacMan().getPosition();
        Orientation pacmanori = gameData.getPacMan().getOrientation();
        switch (pacmanori) {
            case UP:
                return new Position(pacman.getX() - 4, pacman.getY() - 4);
            case DOWN:
                return new Position(pacman.getX(), pacman.getY() + 4);
            case LEFT:
                return new Position(pacman.getX() - 4, pacman.getY());
            case RIGHT:
                return new Position(pacman.getX() + 4, pacman.getY());
        }
        return null;
    }
}
*/
