package g11.controller;

import g11.model.Elements.EmptySpace;
import g11.model.Elements.Ghost;
import g11.model.GameData;
import g11.model.GhostState;
import g11.model.Orientation;

import java.util.ArrayList;

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
                    if (availableOris.size() > 1){
                        Orientation tochange;
                        for (Orientation orientation : availableOris){

                        }
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
        ArrayList<Orientation> returning = new ArrayList<>();
        for (EmptySpace emptySpace : gameData.getMap().getEmptySpaces()){
            if (emptySpace.getPosition().equals(ghost.getPosition().up())){
                if (ghost.getOrientation().getOpposite() != Orientation.UP){
                    returning.add(Orientation.UP);
                }
            }
            else if (emptySpace.getPosition().equals(ghost.getPosition().down())){
                if (ghost.getOrientation().getOpposite() != Orientation.DOWN){
                    returning.add(Orientation.DOWN);
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
