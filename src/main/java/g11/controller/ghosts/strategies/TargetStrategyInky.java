package g11.controller.ghosts.strategies;

import g11.controller.ghosts.TargetStrategy;
import g11.model.GameData;
import g11.model.OrientationEnumeration;
import g11.model.Position;

public class TargetStrategyInky implements TargetStrategy {
    @Override
    public Position getTarget(GameData gameData) {
        Position pacmanPos = gameData.getPacMan().getPosition();
        Position blinkyPos = gameData.getGhosts().get(0).getPosition();
        OrientationEnumeration pacmanOri = gameData.getPacMan().getOrientationEnumeration();
        Position tempPos = null;
        switch (pacmanOri) {
            case UP:
                tempPos = new Position(pacmanPos.getX() - 2, pacmanPos.getY() - 2);
                break;
            case DOWN:
                tempPos = new Position(pacmanPos.getX(), pacmanPos.getY() + 2);
                break;
            case LEFT:
                tempPos = new Position(pacmanPos.getX() - 2, pacmanPos.getY());
                break;
            case RIGHT:
                tempPos = new Position(pacmanPos.getX() + 2, pacmanPos.getY());
                break;
        }
        return new Position(2 * tempPos.getX() - blinkyPos.getX(), 2 * tempPos.getY() - blinkyPos.getY());
    }
}
