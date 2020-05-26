package g11.controller.ghosts;

import g11.model.GameData;
import g11.model.Orientation;
import g11.model.Position;

public class TargetStrategyInky implements TargetStrategy {
    @Override
    public Position getTarget(GameData gameData) {
        Position pacmanPos = gameData.getPacMan().getPosition();
        Position blinkyPos = gameData.getGhosts().get(0).getPosition();
        Orientation pacmanOri = gameData.getPacMan().getOrientation();
        Position tempPos = new Position(0, 0);
        switch (pacmanOri) {
            case UP:
                tempPos = new Position(pacmanPos.getX() - 2, pacmanPos.getY() - 2);
            case DOWN:
                tempPos = new Position(pacmanPos.getX(), pacmanPos.getY() + 2);
            case LEFT:
                tempPos = new Position(pacmanPos.getX() - 2, pacmanPos.getY());
            case RIGHT:
                tempPos = new Position(pacmanPos.getX() + 2, pacmanPos.getY());
        }
        // TODO calculo da posição
        return new Position(2 * tempPos.getX() - blinkyPos.getX(), 2 * tempPos.getY() - blinkyPos.getY());
    }
}
