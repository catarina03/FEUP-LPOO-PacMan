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

public abstract class GhostController {
    Ghost ghost;
    private boolean exitingHouse; // está a sair da GhostHouse
    private boolean changeOrientation;  // true quando tiver que alterar orientação no proximo calculateAndStep()
    private int ticksToEndFrightened;

    public GhostController(boolean starting, Ghost ghost) {
        this.ghost = ghost;
        this.exitingHouse = starting;
        this.changeOrientation = false;
        this.exitingHouse = true;
        this.ticksToEndFrightened = 0;}

    public Ghost getGhost() { return ghost; }
    public boolean isExitingHouse() { return exitingHouse; }
    public void setExitingHouse(boolean exitingHouse) { this.exitingHouse = exitingHouse; }
    public void setChangeOrientation(boolean changeOrientation) { this.changeOrientation = changeOrientation; }
    public int getTicksToEndFrightened() { return ticksToEndFrightened; }
    public void setTicksToEndFrightened(int ticksToEndFrightened) { this.ticksToEndFrightened = ticksToEndFrightened; }

    public abstract void update(GameData gameData, long elapsedTime, int step, GhostState ghostState);
    public abstract Position getTarget(GameData gameData);

    public boolean isInsideHouse(Ghost ghost){
        // TODO não está a ter em conta os Gates
        return ghost.getPosition().getX() >= 11 && ghost.getPosition().getX() <= 16 && ghost.getPosition().getY() >= 16 && ghost.getPosition().getY() <= 18;
    }

    /**
     * Método partilhado por cada controller especifico
     * para determinar o estado do Ghost
     * @param elapsedtime  Parametro para passar ao método setStateTime
     * @param ghostState  parametro recebido de update de Game() para alertar estados Eaten e Frightened
     */
    public void determineState(long elapsedtime, GhostState ghostState){
        // TODO Ainda fica preso na Ghost House

        // Caso fiquem presos dentro da casa em Chase ou Scatter
        if (isInsideHouse(ghost)){
            setExitingHouse(true);
        }
        else {
            // Houve colisão, Ou é comido ou acaba jogo
            if (ghostState == GhostState.EATEN) {
                if (ghost.getState() == GhostState.FRIGHTENED) {
                    ghost.setState(GhostState.EATEN);
                    setTicksToEndFrightened(0);
                }
            }
            // Entra em FRIGHTENED
            if (ghostState == GhostState.FRIGHTENED) {
                // só põe frightened se não estiver EATEN, ENTERINGHOUSE, ou exiting
                if (ghost.getState() != GhostState.EATEN && ghost.getState() != GhostState.ENTERINGHOUSE && !isExitingHouse()) {
                    ghost.setState(GhostState.FRIGHTENED);
                    setChangeOrientation(true);
                    setTicksToEndFrightened(160);
                }
            }
            // Se estiver em frightened, atualiza o tempo restante
            if (getTicksToEndFrightened() > 0) {
                setTicksToEndFrightened(getTicksToEndFrightened() - 1);
            }
            // Se acabar o Tempo e não estiver a meio de um dos outros passos passa para Chase, que depois é atualizado de acordo com o tempo em baixo
            if (getTicksToEndFrightened() == 0 && ghost.getState() != GhostState.EATEN && ghost.getState() != GhostState.ENTERINGHOUSE && !isExitingHouse())
                ghost.setState(setStatetime(elapsedtime));

            // se estiver em STATE EATEN, não atualiza STATE que vem de ghostState
            // se estiver em ENTERINGHOUSE, também não atualiza o seu state
            // se estiver em FRIGHTENED, não atualiza com base no tempo
            // se estiver a Sair da Casa, também não atualiza o seu state
            if (ghost.getState() != GhostState.EATEN &&
                    ghost.getState() != GhostState.ENTERINGHOUSE &&
                    ghost.getState() != GhostState.FRIGHTENED &&
                    !(isExitingHouse() && ghost.getState() == GhostState.CHASE))
                ghost.setState(setStatetime(elapsedtime));
        }
    }

    /**
     * Retorna o GhostState baseado no tempo
     * Sem Frightened ou Eaten, o State alterna entre Chase e Scatter dependendo do tempo
     * @param elapsedtime  tempo desde inicio da partida
     * @return o GhostState de acordo com o tempo desde o inicio do jogo
     */
    public GhostState setStatetime(long elapsedtime) {
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

    /**
     * Determina as orientações disponiveis para o Ghost
     * @param gameData para obter info sobre os espaços vazios, paredes e gates
     * @return um Array com as orientações disponiveis
     */
    public ArrayList<Orientation> getAvailableOrientations(GameData gameData) {
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

    /**
     * Escolhe a orientação do Ghost tendo em conta availableOris e a distancia até ao Pac-Man
     * @param availableOris array com as orientações possiveis
     * @return A orientação para a qual mudar
     */
    public Orientation chooseOrientation(ArrayList<Orientation> availableOris){
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
            return chooseOrientationPriority(availableOris, itosend, minDistance);
        return tochange;
    }

    /**
     * Método auxiliar de chooseOrientation paradeterminar a orientação caso haja mais do que uma orientação com distancia minima
     * Encontra as orientações com distancia minima e tendo em conta a preferencia Up, Left, Down escolhe a orientação
     * @param availableOris array com as orientações possiveis
     * @param index indice de availableOris da ultima Orientalão que tem distancia minima
     * @param dist a distancia minima
     * @return A orientação para a qual mudar
     */
    private Orientation chooseOrientationPriority(ArrayList<Orientation> availableOris, int index, double dist){
        // index tem valor de uma orientação que tem distancia a target igual a outra orientação
        Orientation ori1 = availableOris.get(index);
        Orientation ori2 = UP;
        double tempdistance;

        if (availableOris.size() == 2)
            ori2 = availableOris.get(Math.abs(index-1));
        else{
            //encontrar ori2
            int i = 0;
            for (Orientation ori : availableOris){
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

    /**
     * Método Principal que chama os métodos restantes tendo em conta o state do Ghost
     * @param gameData Para usar em calculateStep
     * @param step Para usar em calculateStep
     */
    public void stateSwitch(GameData gameData, int step){
        switch (ghost.getState()){
            case SCATTER:
                // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                ghost.setTarget(ghost.getScatterTarget());
                calculateAndStep(gameData, step);
                break;
            case CHASE:
                if (isExitingHouse() && ghost.getPosition().equals(new Position(13,14))) // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
                    setExitingHouse(false);

                if (isExitingHouse()) ghost.setTarget(new Position(13,14));  // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
                else ghost.setTarget(getTarget(gameData));

                calculateAndStep(gameData, step);
                break;
            case FRIGHTENED:
                // não interessa o target
                calculateAndStep(gameData, step);
                break;
            case EATEN:
                if (ghost.getPosition().equals(new Position(13,14))){
                    ghost.setState(GhostState.ENTERINGHOUSE); }
                else{
                    ghost.setTarget(new Position(13,14));  // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
                    calculateAndStep(gameData, step);
                }
                break;
            case ENTERINGHOUSE:
                if (ghost.getPosition().equals(new Position(13,17))){
                    ghost.setTarget(new Position(13,14));  // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
                    ghost.setState(GhostState.CHASE);
                    setExitingHouse(true);}
                else {
                    ghost.setTarget(new Position(13, 17));  // FIXME depende do mapa -> v2 (24, 16) ; v1 (13, 16)
                    calculateAndStep(gameData,  step);
                }
                break;
        }
    }

    /**
     * Método auxiliar de StateSwitch que dependendo do estado do fantasma, decide se atualiza a sua posição ou não
     * Atualizando em alturas diferentes, dependendo do State
     * @param gameData Para usar em getAvailableOrientations
     * @param step cada 50 ms corresponde a 1 step, dependedo do step e do State do ghost, irá, ou não, atualizar
     * @return Atualiza posição do Ghost
     */
    public void calculateAndStep(GameData gameData, int step){
        ArrayList<Orientation> availableOris;
        if (step % 4 == 0){
            if ((ghost.getState() == GhostState.CHASE || ghost.getState() == GhostState.SCATTER)){
                {
                    if (changeOrientation) {
                        ghost.setOrientation(ghost.getOrientation().getOpposite());
                        changeOrientation = false;
                    }
                    else {
                        availableOris = getAvailableOrientations(gameData);
                        if (availableOris.size() > 0) {
                            ghost.setOrientation(chooseOrientation(availableOris));
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
                availableOris = getAvailableOrientations(gameData);
                if (availableOris.size() > 0) {
                    // choose random orientation
                    int randomNum = ThreadLocalRandom.current().nextInt(0, availableOris.size());
                    ghost.setOrientation(availableOris.get(randomNum));
                }
            }
            ghost.moveDirection();
        }
        else if (step % 3 == 0 && ghost.getState() == GhostState.EATEN){
            availableOris = getAvailableOrientations(gameData);
            if (availableOris.size() > 0) {
                ghost.setOrientation(chooseOrientation(availableOris));
            }
            ghost.moveDirection();
        }
    }
}
