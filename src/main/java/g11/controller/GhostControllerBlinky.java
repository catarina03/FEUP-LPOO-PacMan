package g11.controller;

import g11.model.elements.Ghost;
import g11.model.GameData;
import g11.model.GhostState;
import g11.model.Orientation;

import java.util.ArrayList;

import static g11.model.Orientation.*;

public class GhostControllerBlinky extends GhostController {

    private GhostState state;

    public GhostControllerBlinky() {
        this.state = GhostState.SCATTER;
    }

    public void update(GameData gameData, long elapsedtime) {
        state = setStatetime(elapsedtime);
        ArrayList<Orientation> availableOris;
        Ghost ghost = gameData.getGhosts().get(0);
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
                ghost.setTarget(gameData.getPacMan().getPosition());

                availableOris = getAvailableOrientations(gameData, ghost, false);
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
