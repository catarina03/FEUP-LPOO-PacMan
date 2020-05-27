package g11.controller.ghosts;

import g11.model.GameData;
import g11.model.OrientationENUM;
import g11.model.Position;

public class TargetStrategyPinky implements TargetStrategy {
    @Override
    public Position getTarget(GameData gameData) {
        Position pacman = gameData.getPacMan().getPosition();
        OrientationENUM pacmanori = gameData.getPacMan().getOrientationENUM();
        switch (pacmanori) {
            case UP:
                return new Position(pacman.getX() - 4, pacman.getY() - 4);
            case DOWN:
                return new Position(pacman.getX(), pacman.getY() + 4);
            case LEFT:
                return new Position(pacman.getX() - 4, pacman.getY());
            case RIGHT:
                return new Position(pacman.getX() + 4, pacman.getY());
        }
        return null;
    }
}
