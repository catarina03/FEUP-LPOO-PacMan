package g11.controller;

import g11.model.GameData;
import g11.model.GhostState;
import g11.model.Orientation;
import g11.model.Position;
import g11.model.elements.Ghost;

import java.util.ArrayList;

public class GhostControllerInky extends GhostController {
    private GhostState state;
    private boolean starting;

    public GhostControllerInky() {
        this.state = GhostState.SCATTER;
        starting = true;
    }

    public void update(GameData gameData, long elapsedtime) {
        ArrayList<Orientation> availableOris;
        Ghost ghost = gameData.getGhosts().get(1);

        if (ghost.getPosition().equals(new Position(24,14)))
            starting = false;

        if (starting)
            state = GhostState.CHASE;
        else
            state = setStatetime(elapsedtime);

        if (elapsedtime > 5000){
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
                    ghost.setTarget(new Position(24,14));
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
