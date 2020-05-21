package g11.controller;

import g11.model.GameData;
import g11.model.GhostStateENUM;
import g11.model.Orientation;
import g11.model.Position;
import g11.model.elements.Ghost;

public class GhostControllerInky extends GhostController {
    public GhostControllerInky(Ghost ghost) {
        super(true, ghost);
    }

    public void update(GameData gameData, long elapsedtime, int step, GhostStateENUM ghostStateENUM) {
        determineState(elapsedtime, ghostStateENUM);
        if (elapsedtime > 5000) {
            stateSwitch(gameData, step);
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
