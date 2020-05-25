package g11.controller.gamestates;

import g11.controller.CollisionChecker;
import g11.controller.Game;
import g11.controller.ghosts.*;
import g11.model.GameData;
import g11.model.GameStats;
import g11.view.GuiSquare;
import g11.view.MoveENUM;

import java.util.ArrayList;

public class GameStateRun extends GameState {

    public GameStateRun(Game game) {
        super(game);
    }

    @Override
    public Boolean execute(GuiSquare guiSquare) throws Throwable {
        // Os Ghosts atualizam a cada 200 ms em Scatter e Chase; 250 em Frightened; 150 em Eaten
        // o Pacman a cada 200 ms
        game.setRunning(true);
        game.setWinner(false);

        game.setLastmove(MoveENUM.LEFT);
        game.setCchecker(new CollisionChecker());

        game.setGameData(new GameData(new GameStats(0),
                game.getMapReader().startingPacMan(),
                game.getMapReader().ghostList(),
                game.getMapReader().getMap()));

        game.setNumberActivePP(game.getGameData().getMap().getPowerPellets().size());

        ArrayList<GhostController> ghostControllers = new ArrayList<>();
        ghostControllers.add(new GhostController(false, game.getGameData().getGhosts().get(0), new TargetStrategyBlinky(), 0));
        ghostControllers.add(new GhostController(true, game.getGameData().getGhosts().get(1), new TargetStrategyInky(), 5000));
        ghostControllers.add(new GhostController(true, game.getGameData().getGhosts().get(2), new TargetStrategyPinky(), 0));
        ghostControllers.add(new GhostController(true, game.getGameData().getGhosts().get(3), new TargetStrategyClyde(), 10000));
        game.setGhostControllers(ghostControllers);

        long startTime = System.currentTimeMillis();
        int step = 0;

        // Starting Sequence
        guiSquare.draw(game.getGameData());
        guiSquare.readyScreen();
        Thread.sleep(3000);
        guiSquare.draw(game.getGameData());

        while (game.getRunning()) {
            long current = System.currentTimeMillis();

            // process input
            MoveENUM temp = guiSquare.getMove();
            if (temp != null) game.setLastmove(temp);
            game.processKey(game.getLastmove());

            // update
            game.update(game.getGameData(), step, System.currentTimeMillis() - startTime);

            // render
            guiSquare.draw(game.getGameData());

            step++;
            long elapsed = System.currentTimeMillis() - current;
            if (elapsed < 50) Thread.sleep(50 - elapsed);
        }

        game.changeGameState(new GameStateEndScreen(game, game.getWinner()));
        return false;
    }
}
