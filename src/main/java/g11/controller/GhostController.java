package g11.controller;

import g11.model.Elements.Blinky;
import g11.model.Elements.Coin;
import g11.model.Elements.EmptySpace;
import g11.model.Elements.Ghost;
import g11.model.GameData;
import g11.model.GhostState;
import g11.model.Orientation;

import java.util.ArrayList;

import static g11.model.Orientation.*;

public class GhostController {
    private GhostState state;

    public GhostController() {
        this.state = GhostState.CHASE;
    }

    public void update(GameData gameData, long elapsedtime) {
        state = setStatetime(elapsedtime);
        switch (state){
            case SCATTER:
                // Para cada ghost -> vê as direções possiveis que pode tomar -> para cada possição vê a melhor -> muda a direção -> atualiza posição
                for (Ghost ghost : gameData.getGhosts()){
                    ArrayList<Orientation> availableOris = getAvailableOrientations(gameData, ghost);
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
                }
                break;
            case CHASE:
                // Para cada ghost -> vê as direções possiveis que pode tomar -> para cada possição vê a melhor -> muda a direção -> atualiza posição
                for (Ghost ghost : gameData.getGhosts()){
                    // atualiza posição de target
                    if (ghost instanceof Blinky) {
                        ghost.setTarget(gameData.getPacMan().getPosition());


                        ArrayList<Orientation> availableOris = getAvailableOrientations(gameData, ghost);
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
                    }
                }
                break;
        }
    }

    private GhostState setStatetime(long elapsedtime) {
        // 7 secs scatter -> 20 secs chase
        // 7 secs scatter -> 20 secs chase
        // 5 secs scatter -> 20 secs chase
        // 5 secs scatter -> inf secs chase
        if (elapsedtime > 0 && elapsedtime <= 7000 || elapsedtime > 27000 && elapsedtime <= 34000 || elapsedtime > 54000 && elapsedtime <= 59000 || elapsedtime > 79000 && elapsedtime <= 84000)
            return GhostState.SCATTER;
        else
            return GhostState.CHASE;
    }

    private ArrayList<Orientation> getAvailableOrientations(GameData gameData, Ghost ghost) {
        // cima
        ArrayList<Orientation> returning = new ArrayList<Orientation>();
        for (EmptySpace emptySpace : gameData.getMap().getEmptySpaces()){
            if (emptySpace.getPosition().equals(ghost.getPosition().up())){
                if (ghost.getOrientation().getOpposite() != UP){
                    returning.add(UP);
                }
            }
            else if (emptySpace.getPosition().equals(ghost.getPosition().down())){
                if (ghost.getOrientation().getOpposite() != DOWN){
                    returning.add(DOWN);
                }
            }
            else if (emptySpace.getPosition().equals(ghost.getPosition().left())){
                if (ghost.getOrientation().getOpposite() != LEFT){
                    returning.add(LEFT);
                }
            }
            else if (emptySpace.getPosition().equals(ghost.getPosition().right())){
                if (ghost.getOrientation().getOpposite() != RIGHT){
                    returning.add(RIGHT);
                }
            }
        }
        for (Coin emptySpace : gameData.getMap().getCoins()){
            if (emptySpace.getPosition().equals(ghost.getPosition().up())){
                if (ghost.getOrientation().getOpposite() != UP){
                    returning.add(UP);
                }
            }
            else if (emptySpace.getPosition().equals(ghost.getPosition().down())){
                if (ghost.getOrientation().getOpposite() != DOWN){
                    returning.add(DOWN);
                }
            }
            else if (emptySpace.getPosition().equals(ghost.getPosition().left())){
                if (ghost.getOrientation().getOpposite() != LEFT){
                    returning.add(LEFT);
                }
            }
            else if (emptySpace.getPosition().equals(ghost.getPosition().right())){
                if (ghost.getOrientation().getOpposite() != RIGHT){
                    returning.add(RIGHT);
                }
            }
        }

        return returning;
    }


}
