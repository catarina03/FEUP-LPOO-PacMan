package g11.controller;

import g11.model.Elements.Coin;
import g11.model.Elements.EmptySpace;
import g11.model.Elements.Ghost;
import g11.model.GameData;
import g11.model.GhostState;
import g11.model.Orientation;

import java.util.ArrayList;

import static g11.model.Orientation.DOWN;
import static g11.model.Orientation.UP;

public class GhostController {
    private GhostState state;

    public GhostController() {
        this.state = GhostState.SCATTER;
    }

    public void update(GameData gameData, long elapsedtime) {
        switch (state){
            case SCATTER:
                // está em ponto de cruzamento?
                    // sim -> atualiza direção
                    // não -> skip a atualização da direção
                // update da posição

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
                break;
        }
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
                if (ghost.getOrientation().getOpposite() != Orientation.LEFT){
                    returning.add(Orientation.LEFT);
                }
            }
            else if (emptySpace.getPosition().equals(ghost.getPosition().right())){
                if (ghost.getOrientation().getOpposite() != Orientation.RIGHT){
                    returning.add(Orientation.RIGHT);
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
                if (ghost.getOrientation().getOpposite() != Orientation.LEFT){
                    returning.add(Orientation.LEFT);
                }
            }
            else if (emptySpace.getPosition().equals(ghost.getPosition().right())){
                if (ghost.getOrientation().getOpposite() != Orientation.RIGHT){
                    returning.add(Orientation.RIGHT);
                }
            }
        }

        return returning;
    }


}
