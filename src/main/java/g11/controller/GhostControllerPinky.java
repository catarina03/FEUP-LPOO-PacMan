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
                    Orientation tochange = UP;
                    double distance = 1000.0;
                    for (Orientation orientation : availableOris){
                        double tempdistance = ghost.getPosition().nextPositionWithOrientation(orientation).distance(ghost.getScatterTarget());
                        if(tempdistance < distance) {
                            tochange = orientation;
                            distance = tempdistance;
                        }
                    }
                    ghost.setOrientation(tochange);
                }
                ghost.moveDirection();
                break;
            case CHASE:
                // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                // atualiza posição de target
                if (starting)
                    ghost.setTarget(new Position(24,14));
                else
                    ghost.setTarget(gameData.getPacMan().getPosition());

                availableOris = getAvailableOrientations(gameData, ghost, true);
                if (availableOris.size() > 0) {
                    Orientation tochange = DOWN;
                    double distance = 1000.0;
                    for (Orientation orientation : availableOris) {
                        double tempdistance = ghost.getPosition().nextPositionWithOrientation(orientation).distance(ghost.getTarget());
                        if (tempdistance < distance) {
                            tochange = orientation;
                            distance = tempdistance;
                        }
                    }
                    ghost.setOrientation(tochange);
                }
                ghost.moveDirection();

                break;

        }
    }
}
