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
import java.util.Scanner;

import static g11.model.Orientation.*;
import static g11.model.Orientation.RIGHT;

// TODO Frightened e Eaten States
public abstract class GhostController {
    private GhostState state;
    private boolean starting;
    private boolean changeOri;

    public GhostController(GhostState state) { this.state = state; this.changeOri = false;}
    public GhostController(GhostState state, boolean starting) { this.state = state; this.starting = starting; this.changeOri = false;}

    public GhostState getState() { return state; }
    public void setState(GhostState state) { this.state = state; }
    public boolean isStarting() { return starting; }
    public void setStarting(boolean starting) { this.starting = starting; }

    public abstract void update(GameData gameData, long elapsedTime, int step);
    public abstract Position getTarget(GameData gameData);

    public GhostState setStatetime(long elapsedtime, Ghost ghost) {
        // 7 secs scatter -> 20 secs chase
        // 7 secs scatter -> 20 secs chase
        // 5 secs scatter -> 20 secs chase
        // 5 secs scatter -> inf secs chase
        if (elapsedtime > 0 && elapsedtime <= 6800)
            return GhostState.SCATTER;
        else if (elapsedtime > 27000 && elapsedtime <= 34000 || elapsedtime > 54000 && elapsedtime <= 59000 || elapsedtime > 79000 && elapsedtime <= 84000){
            if (getState() != GhostState.SCATTER)
                changeOri = true;
            return GhostState.SCATTER;}
        else
        { ghost.setOrientation(ghost.getOrientation().getOpposite());
            if (getState() != GhostState.CHASE)
                changeOri = true;
            return GhostState.CHASE;
        }
    }

    public ArrayList<Orientation> getAvailableOrientations(GameData gameData, Ghost ghost, boolean exitGhostHouse) {
        ArrayList<Orientation> returning = new ArrayList<>();
        // se estiverem nos cruzamentos amarelos não podem mudar de direção
        // TODO valores variam com mapa: v1 (), (), (), () ; v2 (23,26), (26, 26), () e ()
        if (ghost.getPosition().equals(new Position(12,14)) ||
                ghost.getPosition().equals(new Position(15,14)) ||
                ghost.getPosition().equals(new Position(12,26)) ||
                ghost.getPosition().equals(new Position(15,26))){
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

    public Orientation chooseOrientation(ArrayList<Orientation> availableOris, Ghost ghost, GhostState ghostState){
        Orientation tochange = UP;
        double tempdistance;
        double minDistance = 1000.0, equaldistance = 1000.0;
        int i = 0, itosend = 0;

        if (availableOris.size() == 1)
            return availableOris.get(0);
        else {
            for (Orientation orientation : availableOris) {
                tempdistance = ghostState == GhostState.SCATTER ? ghost.getPosition().nextPositionWithOrientation(orientation).distance(ghost.getScatterTarget()) : ghost.getPosition().nextPositionWithOrientation(orientation).distance(ghost.getTarget());
                if (tempdistance == minDistance){
                    itosend = i;
                    equaldistance = minDistance;
                }
                else if (tempdistance < minDistance) {
                    tochange = orientation;
                    minDistance = tempdistance;
                }
                i++;
            }
        }

        if (minDistance == equaldistance)
            return ghostState == GhostState.SCATTER ? chooseOrientationPriority(availableOris, ghost, true, itosend, minDistance) : chooseOrientationPriority(availableOris, ghost, false, itosend, minDistance);
        return tochange;
    }

    private Orientation chooseOrientationPriority(ArrayList<Orientation> availableoris, Ghost ghost, boolean scatter, int index, double dist){
        // index tem valor de uma orientação que tem distancia a target igual a outra orientação
        Orientation ori1 = availableoris.get(index);
        Orientation ori2 = UP;
        double tempdistance;

        if (availableoris.size() == 2)
            ori2 = availableoris.get(Math.abs(index-1));
        else{
            //encontrar ori2
            int i = 0;
            for (Orientation ori : availableoris){
                tempdistance = scatter ? ghost.getPosition().nextPositionWithOrientation(ori).distance(ghost.getScatterTarget()) : ghost.getPosition().nextPositionWithOrientation(ori).distance(ghost.getTarget());
                if (tempdistance == dist && i != index){
                    ori2 = ori;
                    break;
                }
                i++;
            }
        }
        if (ori1 == UP || ori2 == UP)
            return UP;
        if (ori1 == LEFT || ori2 == LEFT)
            return LEFT;
        if (ori1 == DOWN || ori2 == DOWN)
            return DOWN;
        return UP;
    }

    public void calculateAndStep(GameData gameData, Ghost ghost, boolean exitingHouse, GhostState ghostState, int step){
        ArrayList<Orientation> availableOris;
        if (step % 4 == 0 && (getState() == GhostState.CHASE || getState() == GhostState.SCATTER)){
            {
                if (changeOri) {
                    ghost.setOrientation(ghost.getOrientation().getOpposite());
                    changeOri = false;
                }
                else {
                    availableOris = getAvailableOrientations(gameData, ghost, exitingHouse);
                    if (availableOris.size() > 0) {
                        ghost.setOrientation(chooseOrientation(availableOris, ghost, ghostState));
                    }
                }
            }
            ghost.moveDirection();
        }
    }
}
