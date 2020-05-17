/*
package g11.controller;

import g11.model.GameData;
import g11.model.GhostState;
import g11.model.Orientation;
import g11.model.Position;
import g11.model.elements.Ghost;

public class GhostControllerInky extends GhostController {
    public GhostControllerInky() { super(GhostState.SCATTER, true); }

    public void update(GameData gameData, long elapsedtime, int step, GhostState ghostState) {
        Ghost ghost = gameData.getGhosts().get(1);

        if (ghost.getPosition().equals(new Position(13,14))) // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
            setStarting(false);

        if (isStarting()) ghost.setState(GhostState.CHASE);
        else if (ghostState == GhostState.FRIGHTENED) ghost.setState(GhostState.FRIGHTENED);
        else ghost.setState(setStatetime(elapsedtime, ghost, gameData));

        if (elapsedtime > 5000 ){
            switch (ghost.getState()){
                case SCATTER:
                    // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                    ghost.setTarget(ghost.getScatterTarget());
                    calculateAndStep(gameData, ghost, false, step);
                    break;
                case CHASE:
                    // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                    // atualiza posição de target
                    if (isStarting()) ghost.setTarget(new Position(13,14)); // FIXME depende do mapa -> v2 (13, 14) ; v1 (13, 14)
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
        Position pacmanPos = gameData.getPacMan().getPosition();
        Position blinkyPos = gameData.getGhosts().get(0).getPosition();
        Orientation pacmanOri = gameData.getPacMan().getOrientation();
        Position tempPos = new Position(0,0);
        switch (pacmanOri) {
            case UP:
                tempPos =  new Position(pacmanPos.getX() - 2, pacmanPos.getY() - 2);
            case DOWN:
                tempPos = new Position(pacmanPos.getX(), pacmanPos.getY() + 2);
            case LEFT:
                tempPos =  new Position(pacmanPos.getX() - 2, pacmanPos.getY());
            case RIGHT:
                tempPos = new Position(pacmanPos.getX() + 2, pacmanPos.getY());
        }
        // TODO calculo da posição
        return new Position(2*tempPos.getX()-blinkyPos.getX(), 2*tempPos.getY()-blinkyPos.getY());
    }
}
*/
