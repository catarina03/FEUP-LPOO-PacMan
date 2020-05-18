package g11.controller;

import g11.model.GameData;
import g11.model.GhostState;
import g11.model.Orientation;
import g11.model.Position;
import g11.model.elements.Ghost;

public class GhostControllerInky extends GhostController {
    public GhostControllerInky() { super(GhostState.SCATTER, true); }

    public void update(GameData gameData, long elapsedtime, int step, GhostState ghostState) {
        Ghost ghost = gameData.getGhosts().get(1);

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
                ghost.setState(setStatetime(elapsedtime, ghost, gameData));

            // se estiver em STATE EATEN, não atualiza STATE que vem de ghostState
            // se estiver em ENTERINGHOUSE, também não atualiza o seu state
            // se estiver em FRIGHTENED, não atualiza com base no tempo
            // se estiver a Sair da Casa, também não atualiza o seu state
            if (ghost.getState() != GhostState.EATEN &&
                    ghost.getState() != GhostState.ENTERINGHOUSE &&
                    ghost.getState() != GhostState.FRIGHTENED &&
                    !(isExitingHouse() && ghost.getState() == GhostState.CHASE))
                ghost.setState(setStatetime(elapsedtime, ghost, gameData));
        }

        if (elapsedtime > 5000 ){
            switch (ghost.getState()){
                case SCATTER:
                    // vê as direções possiveis que pode tomar -> para cada posição vê a melhor -> muda a direção -> atualiza posição
                    ghost.setTarget(ghost.getScatterTarget());
                    calculateAndStep(gameData, ghost, step);
                    break;
                case CHASE:
                    if (isExitingHouse() && ghost.getPosition().equals(new Position(13,14))) // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
                        setExitingHouse(false);

                    if (isExitingHouse()) ghost.setTarget(new Position(13,14));  // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
                    else ghost.setTarget(getTarget(gameData));

                    calculateAndStep(gameData, ghost, step);
                    break;
                case FRIGHTENED:
                    calculateAndStep(gameData, ghost, step);
                    break;
                case EATEN:
                    if (ghost.getPosition().equals(new Position(13,14))){
                        ghost.setState(GhostState.ENTERINGHOUSE); }
                    else{
                        ghost.setTarget(new Position(13,14));  // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
                        calculateAndStep(gameData, ghost, step);
                    }
                    break;
                case ENTERINGHOUSE:
                    if (ghost.getPosition().equals(new Position(13,17))){
                        ghost.setTarget(new Position(13,14));  // FIXME depende do mapa -> v2 (24, 14) ; v1 (13, 14)
                        ghost.setState(GhostState.CHASE);
                        setExitingHouse(true);}
                    else {
                        ghost.setTarget(new Position(13, 17));  // FIXME depende do mapa -> v2 (24, 16) ; v1 (13, 16)
                        calculateAndStep(gameData, ghost,  step);
                    }
            }
        }
    }

    @Override
    public Position getTarget(GameData gameData) {
        Position pacmanPos = gameData.getPacMan().getPosition();
        Position blinkyPos = gameData.getGhosts().get(0).getPosition();
        Orientation pacmanOri = gameData.getPacMan().getOrientation();
        Position tempPos = new Position(0,0);
        switch (pacmanOri) {
            case UP:
                tempPos =  new Position(pacmanPos.getX() - 2, pacmanPos.getY() - 2);
            case DOWN:
                tempPos = new Position(pacmanPos.getX(), pacmanPos.getY() + 2);
            case LEFT:
                tempPos =  new Position(pacmanPos.getX() - 2, pacmanPos.getY());
            case RIGHT:
                tempPos = new Position(pacmanPos.getX() + 2, pacmanPos.getY());
        }
        // TODO calculo da posição
        return new Position(2*tempPos.getX()-blinkyPos.getX(), 2*tempPos.getY()-blinkyPos.getY());
    }
}
