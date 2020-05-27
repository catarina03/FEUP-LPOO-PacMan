package g11.controller.ghosts;

import g11.model.OrientationENUM;
import g11.model.Position;
import g11.model.elements.*;
import g11.model.GameData;

import java.util.ArrayList;

import static g11.model.OrientationENUM.*;

public class GhostController {
    Ghost ghost;
    private boolean accessingHouse; // está a sair da GhostHouse
    private boolean changeOrientation;  // true quando tiver que alterar orientação no proximo calculateAndStep()
    private int ticksToEndFrightened;
    private long timeToStart;
    private GhostState ghostState;
    private TargetStrategy targetStrategy;


    public GhostController(boolean accessingHouse, Ghost ghost, TargetStrategy targetStrategy, long timeToStart) {
        this.ghostState = (ghost instanceof Blinky) ?
                new GhostStateScatter(this, targetStrategy, 4) :
                new GhostStateExitingHouse(this, targetStrategy, 4);

        this.targetStrategy = targetStrategy;
        this.timeToStart = timeToStart;

        this.ghost = ghost;
        this.accessingHouse = accessingHouse;
        this.changeOrientation = false;
        this.ticksToEndFrightened = 0;
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

    public void changeState(GhostState ghostState) {
        this.ghostState = ghostState;
    }

    /**
     * Determina as orientações disponiveis para o Ghost
     *
     * @param gameData para obter info sobre os espaços vazios, paredes e gates
     * @return um Array com as orientações disponiveis
     */
    public ArrayList<OrientationENUM> getAvailableOrientations(GameData gameData) {
        ArrayList<OrientationENUM> returning = new ArrayList<>();
        // se estiverem nos cruzamentos amarelos não podem mudar de direção
        // TODO valores variam com mapa: v1 (12,14), (15,14), (12,26), (15,26) ; v2 (23,26), (26, 26), () e ()
        if (ghost.getPosition().equals(new Position(12, 14)) ||
                ghost.getPosition().equals(new Position(15, 14)) ||
                ghost.getPosition().equals(new Position(12, 26)) ||
                ghost.getPosition().equals(new Position(15, 26))) {
            if (ghost.getOrientationENUM().getOpposite() != LEFT) {
                returning.add(LEFT);
            }
            if (ghost.getOrientationENUM().getOpposite() != RIGHT) {
                returning.add(RIGHT);
            }
            return returning;
        }

        for (EmptySpace emptySpace : gameData.getMap().getEmptySpaces()) {
            for (OrientationENUM ori : OrientationENUM.allOptions()) {
                if (emptySpace.getPosition().equals(ghost.getPosition(ori))) {
                    if (ghost.getOrientationENUM().getOpposite() != ori)
                        returning.add(ori);
                }
            }
        }

        for (Coin coin : gameData.getMap().getCoins()) {
            for (OrientationENUM ori : OrientationENUM.allOptions()) {
                if (coin.getPosition().equals(ghost.getPosition(ori))) {
                    if (ghost.getOrientationENUM().getOpposite() != ori)
                        returning.add(ori);
                }
            }
        }

        // Gates abertos, pode sair/entrar
        if (isAccessingHouse()) {
            for (Gate gate : gameData.getMap().getGates()) {
                if (gate.getPosition().equals(ghost.getPosition().up())) {
                    if (ghost.getOrientationENUM().getOpposite() != UP) {
                        returning.add(UP);
                    }
                }
                if (gate.getPosition().equals(ghost.getPosition().down())) {
                    if (ghost.getOrientationENUM().getOpposite() != DOWN) {
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
    public OrientationENUM chooseOrientation(ArrayList<OrientationENUM> availableOris) {
        OrientationENUM tochange = UP;
        double tempdistance;
        double minDistance = 1000.0, equaldistance = 1000.0;
        int i = 0, itosend = 0;

        if (availableOris.size() == 1)
            return availableOris.get(0);
        else {
            for (OrientationENUM orientationENUM : availableOris) {
                tempdistance = ghost.getPosition().nextPositionWithOrientation(orientationENUM).distance(ghost.getTarget());
                if (tempdistance == minDistance) {
                    itosend = i;
                    equaldistance = minDistance;
                } else if (tempdistance < minDistance) {
                    tochange = orientationENUM;
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
    private OrientationENUM chooseOrientationPriority(ArrayList<OrientationENUM> availableOris, int index, double dist) {
        // index tem valor de uma orientação que tem distancia a target igual a outra orientação
        OrientationENUM ori1 = availableOris.get(index);
        OrientationENUM ori2 = UP;
        double tempdistance;

        if (availableOris.size() == 2)
            ori2 = availableOris.get(Math.abs(index - 1));
        else {
            //encontrar ori2
            int i = 0;
            for (OrientationENUM ori : availableOris) {
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
