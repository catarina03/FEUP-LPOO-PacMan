package g11.controller.ghosts;

import g11.controller.ghosts.states.GhostStateExitingHouse;
import g11.controller.ghosts.states.GhostStateScatter;
import g11.model.OrientationEnumeration;
import g11.model.Position;
import g11.model.elements.*;
import g11.model.GameData;
import g11.model.elements.ghosts.Blinky;
import g11.model.elements.map.Coin;
import g11.model.elements.map.EmptySpace;
import g11.model.elements.map.Gate;

import java.util.ArrayList;

import static g11.model.OrientationEnumeration.*;

public class GhostController {
    Ghost ghost;
    private boolean accessingHouse; // está a sair da GhostHouse
    private boolean changeOrientation;  // true quando tiver que alterar orientação no proximo calculateAndStep()
    private GhostState ghostState;
    private final long timeToStart;
    private final TargetStrategy targetStrategy;


    public GhostController(boolean accessingHouse, Ghost ghost, TargetStrategy targetStrategy, long timeToStart) {
        this.ghostState = (ghost instanceof Blinky) ?
                new GhostStateScatter(this, targetStrategy, 4) :
                new GhostStateExitingHouse(this, targetStrategy, 4);

        this.targetStrategy = targetStrategy;
        this.timeToStart = timeToStart;

        this.ghost = ghost;
        this.accessingHouse = accessingHouse;
        this.changeOrientation = false;
    }

    public Ghost getGhost() {
        return ghost;
    }
    public boolean isAccessingHouse() {
        return accessingHouse;
    }
    public void setAccessingHouse(boolean accessingHouse) {
        this.accessingHouse = accessingHouse;
    }
    public boolean isChangeOrientation() {
        return changeOrientation;
    }
    public void setChangeOrientation(boolean changeOrientation) {
        this.changeOrientation = changeOrientation;
    }
    public TargetStrategy getTargetStrategy() {
        return targetStrategy;
    }
    public GhostState getGhostState() {
        return ghostState;
    }

    public void setGhostState(GhostState ghostState) {
        this.ghostState = ghostState;
    }

    public void changeState(GhostState ghostState) {
        this.ghostState = ghostState;
    }

    /**
     * Determina as orientações disponiveis para o Ghost
     *
     * @param gameData para obter info sobre os espaços vazios, paredes e gates
     * @return um Array com as orientações disponiveis
     */
    public ArrayList<OrientationEnumeration> getAvailableOrientations(GameData gameData) {
        ArrayList<OrientationEnumeration> returning = new ArrayList<>();
        // TODO se estiverem nos cruzamentos amarelos não podem mudar de direção

        for (Position pos : gameData.getMap().getUnturnable()) {
            if (ghost.getPosition().equals(pos)) {
                if (ghost.getOrientationEnumeration().getOpposite() != LEFT) {
                    returning.add(LEFT);
                }
                if (ghost.getOrientationEnumeration().getOpposite() != RIGHT) {
                    returning.add(RIGHT);
                }
                return returning;
            }
        }

        for (EmptySpace emptySpace : gameData.getMap().getEmptySpaces()) {
            for (OrientationEnumeration ori : OrientationEnumeration.allOptions()) {
                if (emptySpace.getPosition().equals(ghost.getPosition(ori))) {
                    if (ghost.getOrientationEnumeration().getOpposite() != ori)
                        returning.add(ori);
                }
            }
        }

        for (Coin coin : gameData.getMap().getCoins()) {
            for (OrientationEnumeration ori : OrientationEnumeration.allOptions()) {
                if (coin.getPosition().equals(ghost.getPosition(ori))) {
                    if (ghost.getOrientationEnumeration().getOpposite() != ori)
                        returning.add(ori);
                }
            }
        }

        // Gates abertos, pode sair/entrar
        if (isAccessingHouse()) {
            for (Gate gate : gameData.getMap().getGates()) {
                if (gate.getPosition().equals(ghost.getPosition().up())) {
                    if (ghost.getOrientationEnumeration().getOpposite() != UP) {
                        returning.add(UP);
                    }
                }
                if (gate.getPosition().equals(ghost.getPosition().down())) {
                    if (ghost.getOrientationEnumeration().getOpposite() != DOWN) {
                        returning.add(DOWN);
                    }
                }
            }
        }
        return returning;
    }

    /**
     * Escolhe a orientação do Ghost tendo em conta availableOris e a distancia até ao Pac-Man
     *
     * @param availableOris array com as orientações possiveis
     * @return A orientação para a qual mudar
     */
    public OrientationEnumeration chooseOrientation(ArrayList<OrientationEnumeration> availableOris) {
        OrientationEnumeration tochange = UP;
        double tempdistance;
        double minDistance = 1000.0, equaldistance = 1000.0;
        int i = 0, itosend = 0;

        if (availableOris.size() == 1)
            return availableOris.get(0);
        else {
            for (OrientationEnumeration orientationEnumeration : availableOris) {
                tempdistance = ghost.getPosition().nextPositionWithOrientation(orientationEnumeration).distance(ghost.getTarget());
                if (tempdistance == minDistance) {
                    itosend = i;
                    equaldistance = minDistance;
                } else if (tempdistance < minDistance) {
                    tochange = orientationEnumeration;
                    minDistance = tempdistance;
                }
                i++;
            }
        }

        if (minDistance == equaldistance)
            return chooseOrientationPriority(availableOris, itosend, minDistance);
        return tochange;
    }

    /**
     * Método auxiliar de chooseOrientation paradeterminar a orientação caso haja mais do que uma orientação com distancia minima
     * Encontra as orientações com distancia minima e tendo em conta a preferencia Up, Left, Down escolhe a orientação
     *
     * @param availableOris array com as orientações possiveis
     * @param index         indice de availableOris da ultima Orientalão que tem distancia minima
     * @param dist          a distancia minima
     * @return A orientação para a qual mudar
     */
    private OrientationEnumeration chooseOrientationPriority(ArrayList<OrientationEnumeration> availableOris, int index, double dist) {
        // index tem valor de uma orientação que tem distancia a target igual a outra orientação
        OrientationEnumeration ori1 = availableOris.get(index);
        OrientationEnumeration ori2 = UP;
        double tempdistance;

        if (availableOris.size() == 2)
            ori2 = availableOris.get(Math.abs(index - 1));
        else {
            //encontrar ori2
            int i = 0;
            for (OrientationEnumeration ori : availableOris) {
                tempdistance = ghost.getPosition().nextPositionWithOrientation(ori).distance(ghost.getTarget());
                if (tempdistance == dist && i != index) {
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


    public void update(GameData gameData, int step, long elapsedTime) {
        if (elapsedTime > timeToStart) {
            ghostState.update(gameData, step, elapsedTime);
            ghostState.calculateAndStep(gameData, step);
        }
    }
}
