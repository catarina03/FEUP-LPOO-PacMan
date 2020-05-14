package g11.controller;

import g11.model.GameData;
import g11.model.GhostState;
import g11.model.Orientation;
import g11.model.Position;
import g11.model.elements.Ghost;

import java.util.ArrayList;

import static g11.model.Orientation.DOWN;
import static g11.model.Orientation.UP;

public class GhostControllerPinky extends GhostController {
    private GhostState state;
    private boolean starting;

    public GhostControllerPinky() {
        this.state = GhostState.SCATTER;
        starting = true;
    }

    public void update(GameData gameData, long elapsedtime) {
        ArrayList<Orientation> availableOris;
        Ghost ghost = gameData.getGhosts().get(2);

        if (ghost.getPosition().equals(new Position(24,14)))
            starting = false;

        if (starting)
            state = GhostState.CHASE;
        else
            state = setStatetime(elapsedtime);

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
