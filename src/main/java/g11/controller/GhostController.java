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
import java.util.concurrent.ThreadLocalRandom;

import static g11.model.Orientation.*;
import static g11.model.Orientation.RIGHT;

// TODO Frightened e Eaten States
public abstract class GhostController {
    Ghost ghost;
    private boolean exitingHouse; // está a sair da GhostHouse
    private boolean changeOrientation;  // true quando tiver que alterar orientação no proximo calculateAndStep()
    private int ticksToEndFrightened;

    public GhostController(GhostState state) {this.changeOrientation = false; this.exitingHouse = false; this.ticksToEndFrightened = 0;}
    public GhostController(GhostState state, boolean starting) {this.exitingHouse = starting; this.changeOrientation = false; this.exitingHouse = true; this.ticksToEndFrightened = 0;}

    public Ghost getGhost() { return ghost; }
    public boolean isExitingHouse() { return exitingHouse; }
    public void setExitingHouse(boolean exitingHouse) { this.exitingHouse = exitingHouse; }
    public boolean isChangeOrientation() { return changeOrientation; }
    public void setChangeOrientation(boolean changeOrientation) { this.changeOrientation = changeOrientation; }
    public int getTicksToEndFrightened() { return ticksToEndFrightened; }
    public void setTicksToEndFrightened(int ticksToEndFrightened) { this.ticksToEndFrightened = ticksToEndFrightened; }

    public abstract void update(GameData gameData, long elapsedTime, int step, GhostState ghostState);
    public abstract Position getTarget(GameData gameData);

    public boolean isInsideHouse(Ghost ghost){
        // TODO não está a ter em conta os Gates
        if (ghost.getPosition().getX() >= 11 && ghost.getPosition().getX()<=16 && ghost.getPosition().getY() >= 16 && ghost.getPosition().getY() <= 18)
            return true;
        return false;
    }

    public GhostState setStatetime(long elapsedtime, Ghost ghost, GameData gameData) {
        // 7 secs scatter -> 20 secs chase
        // 7 secs scatter -> 20 secs chase
        // 5 secs scatter -> 20 secs chase
        // 5 secs scatter -> inf secs chase
        if (elapsedtime > 0 && elapsedtime <= 7000)
            return GhostState.SCATTER;
        else if (elapsedtime > 27000 && elapsedtime <= 34000 || elapsedtime > 54000 && elapsedtime <= 59000 || elapsedtime > 79000 && elapsedtime <= 84000){
            if (ghost.getState() != GhostState.SCATTER)
                changeOrientation = true;
            return GhostState.SCATTER;}
        else {
            if (ghost.getState() != GhostState.CHASE)
                changeOrientation = true;
            return GhostState.CHASE;
        }
    }

    public ArrayList<Orientation> getAvailableOrientations(GameData gameData, Ghost ghost) {
        ArrayList<Orientation> returning = new ArrayList<>();
        // se estiverem nos cruzamentos amarelos não podem mudar de direção
        // TODO valores variam com mapa: v1 (12,14), (15,14), (12,26), (15,26) ; v2 (23,26), (26, 26), () e ()
        if (ghost.getPosition().equals(new Position(12,14)) ||
                ghost.getPosition().equals(new Position(15,14)) ||
                ghost.getPosition().equals(new Position(12,26)) ||
                ghost.getPosition().equals(new Position(15,26)) ||
                ghost.getPosition().equals(new Position(13,14))){
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

        // Gates abertos, pode sair/entrar
        if (isExitingHouse()){
            for (Gate gate : gameData.getMap().getGates()){
                if (gate.getPosition().equals(ghost.getPosition().up())){
                    if (ghost.getOrientation().getOpposite() != UP){
                        returning.add(UP);
                    }
                }
                if (gate.getPosition().equals(ghost.getPosition().down())){
                    if (ghost.getOrientation().getOpposite() != DOWN){
                        returning.add(DOWN);
                    }
                }
            }
        }
        return returning;
    }

    public Orientation chooseOrientation(ArrayList<Orientation> availableOris, Ghost ghost){
        Orientation tochange = UP;
        double tempdistance;
        double minDistance = 1000.0, equaldistance = 1000.0;
        int i = 0, itosend = 0;

        if (availableOris.size() == 1)
            return availableOris.get(0);
        else {
            for (Orientation orientation : availableOris) {
                tempdistance = ghost.getPosition().nextPositionWithOrientation(orientation).distance(ghost.getTarget());
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
            return chooseOrientationPriority(availableOris, ghost, itosend, minDistance);
        return tochange;
    }

    private Orientation chooseOrientationPriority(ArrayList<Orientation> availableoris, Ghost ghost, int index, double dist){
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
                tempdistance = ghost.getPosition().nextPositionWithOrientation(ori).distance(ghost.getTarget());
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

    public void calculateAndStep(GameData gameData, Ghost ghost, int step){
        ArrayList<Orientation> availableOris;
        if (step % 4 == 0){
            if ((ghost.getState() == GhostState.CHASE || ghost.getState() == GhostState.SCATTER)){
                {
                    if (changeOrientation) {
                        ghost.setOrientation(ghost.getOrientation().getOpposite());
                        changeOrientation = false;
                    }
                    else {
                        availableOris = getAvailableOrientations(gameData, ghost);
                        if (availableOris.size() > 0) {
                            ghost.setOrientation(chooseOrientation(availableOris, ghost));
                        }
                    }
                }
                ghost.moveDirection();
            }
            if (ghost.getState() == GhostState.ENTERINGHOUSE){
                ghost.setOrientation(DOWN);
                ghost.moveDirection();
            }
        }
        else if (step % 5 == 0 && ghost.getState() == GhostState.FRIGHTENED){
            if (changeOrientation) {
                ghost.setOrientation(ghost.getOrientation().getOpposite());
                changeOrientation = false;
            }
            else {
                availableOris = getAvailableOrientations(gameData, ghost);
                if (availableOris.size() > 0) {
                    // choose random orientation
                    int randomNum = ThreadLocalRandom.current().nextInt(0, availableOris.size());
                    ghost.setOrientation(availableOris.get(randomNum));
                }
            }
            ghost.moveDirection();
        }
        else if (step % 3 == 0 && ghost.getState() == GhostState.EATEN){
            availableOris = getAvailableOrientations(gameData, ghost);
            if (availableOris.size() > 0) {
                ghost.setOrientation(chooseOrientation(availableOris, ghost));
            }
            ghost.moveDirection();
        }
    }
}
