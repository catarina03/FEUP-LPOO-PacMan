package g11.controller.gamestates;

import com.googlecode.lanterna.input.KeyType;
import g11.controller.CollisionChecker;
import g11.controller.Game;
import g11.controller.MapReader;
import g11.controller.ReadFile;
import g11.controller.ghosts.*;
import g11.controller.ghosts.strategies.TargetStrategyBlinky;
import g11.controller.ghosts.strategies.TargetStrategyClyde;
import g11.controller.ghosts.strategies.TargetStrategyInky;
import g11.controller.ghosts.strategies.TargetStrategyPinky;
import g11.model.GameData;
import g11.model.GameStats;
import g11.view.Gui;
import g11.view.guis.GuiSquare;
import g11.view.MoveEnumeration;

import java.io.IOException;
import java.util.ArrayList;

public class GameStateRun extends GameState {
    public GameStateRun(Game game) {
        super(game);
    }

    @Override
    public boolean execute(Gui gui) throws Throwable {
        initialize(gui);

        long startTime = System.currentTimeMillis();
        int step = 0;

        while (game.isRunning()) {
            long current = System.currentTimeMillis();

            // process input
            MoveEnumeration temp = gui.getMove();
            if (temp != null) game.setLastmove(temp);
            boolean pause = game.processKey(game.getLastmove());

            if (pause){
                long starOfPause = System.currentTimeMillis();
                if (pauseScreen(gui)) {
                    game.changeGameState(new GameStatePresentation(game));
                    return false;
                }
                startTime = startTime + (System.currentTimeMillis() - starOfPause);
                game.setLastmove(null);
            }

            // update
            game.update(game.getGameData(), step, System.currentTimeMillis() - startTime);

            // render
            gui.draw(game.getGameData());

            step++;
            long elapsed = System.currentTimeMillis() - current;
            if (elapsed < 50) Thread.sleep(50 - elapsed);
        }

        if (game.getGameData().getGameStats().getScore() > game.getHighScore())
            game.setHighScore(game.getGameData().getGameStats().getScore());

        game.changeGameState(new GameStateEndScreen(game, game.isWinner()));
        return false;
    }

    private void initialize(Gui gui) throws Throwable {
        game.setRunning(true);
        game.setWinner(false);

        game.setLastmove(MoveEnumeration.LEFT);
        game.setCchecker(new CollisionChecker());

        MapReader mapReader = (gui instanceof GuiSquare) ? new MapReader(new ReadFile("mapv1.txt")) : new MapReader(new ReadFile("mapv2.txt"));

        game.setGameData(new GameData(new GameStats(game.getHighScore()),
                mapReader.startingPacMan(),
                mapReader.ghostList(),
                mapReader.getMap()));

        ArrayList<GhostController> ghostControllers = new ArrayList<>();
        ghostControllers.add(new GhostController(false, game.getGameData().getGhosts().get(0), new TargetStrategyBlinky(), 0));
        ghostControllers.add(new GhostController(true, game.getGameData().getGhosts().get(1), new TargetStrategyInky(), 5000));
        ghostControllers.add(new GhostController(true, game.getGameData().getGhosts().get(2), new TargetStrategyPinky(), 0));
        ghostControllers.add(new GhostController(true, game.getGameData().getGhosts().get(3), new TargetStrategyClyde(), 10000));
        game.setGhostControllers(ghostControllers);

        // Starting Sequence
        gui.draw(game.getGameData());
        gui.readyScreen();
        Thread.sleep(3000);
        gui.draw(game.getGameData());
    }

    public boolean pauseScreen(Gui gui) throws IOException {
        int option = 0;
        gui.pauseScreen(option);
        KeyType keyType;
        do {
            keyType = gui.getKeyStroke().getKeyType();
            if (keyType == KeyType.ArrowDown || keyType == KeyType.ArrowUp) {
                option = Math.abs(option - 1);
                gui.pauseScreen(option);
            }
            if (keyType == KeyType.Escape || keyType == KeyType.EOF) {
                game.changeGameState(new GameStateReady(game));
                return false;
            }
        } while (keyType != KeyType.Enter);

        return option == 1;
    }
}
