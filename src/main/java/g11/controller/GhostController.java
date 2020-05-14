package g11.controller;

import g11.model.Position;
import g11.model.elements.Coin;
import g11.model.elements.EmptySpace;
import g11.model.elements.Gate;
import g11.model.elements.Ghost;
import g11.model.GameData;
import g11.model.GhostState;
import g11.model.Orientation;

import java.util.ArrayList;
import java.util.logging.Level;

import static g11.model.Orientation.*;
import static g11.model.Orientation.RIGHT;

public abstract class GhostController {

    public abstract void update(GameData gameData, long elapsedTime);

    public abstract Position getTarget(GameData gameData);

    public GhostState setStatetime(long elapsedtime) {
        // 7 secs scatter -> 20 secs chase
        // 7 secs scatter -> 20 secs chase
        // 5 secs scatter -> 20 secs chase
        // 5 secs scatter -> inf secs chase
        if (elapsedtime > 0 && elapsedtime <= 7000 || elapsedtime > 27000 && elapsedtime <= 34000 || elapsedtime > 54000 && elapsedtime <= 59000 || elapsedtime > 79000 && elapsedtime <= 84000)
            return GhostState.SCATTER;
        else
            return GhostState.CHASE;
    }

    public ArrayList<Orientation> getAvailableOrientations(GameData gameData, Ghost ghost, boolean exitGhostHouse) {
        ArrayList<Orientation> returning = new ArrayList<>();
        // se estiverem nos cruzamentos amarelos não podem mudar de direção
        if (ghost.getPosition().equals(new Position(23,26)) || ghost.getPosition().equals(new Position(26,26))){
            if (ghost.getOrientation().getOpposite() != LEFT){
                returning.add(LEFT);
            }
            if (ghost.getOrientation().getOpposite() != RIGHT){
                returning.add(RIGHT);
            }
            return returning;
        }
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
        // Gates abertos, pode sair
        if (exitGhostHouse){
            for (Gate gate : gameData.getMap().getGates()){
                if (gate.getPosition().equals(ghost.getPosition().up())){
                    if (ghost.getOrientation().getOpposite() != UP){
                        returning.add(UP);
                    }
                }
            }
        }
        return returning;
    }

    public Orientation chooseOrientation(ArrayList<Orientation> availableOris, Ghost ghost, boolean scatter){
        Orientation tochange = UP;
        double tempdistance;
        double distance = 1000.0;
        for (Orientation orientation : availableOris){
            if (scatter)
                tempdistance = ghost.getPosition().nextPositionWithOrientation(orientation).distance(ghost.getScatterTarget());
            else
                tempdistance = ghost.getPosition().nextPositionWithOrientation(orientation).distance(ghost.getTarget());

            if(tempdistance < distance) {
                tochange = orientation;
                distance = tempdistance;
            }
        }
        return tochange;
    }

}
