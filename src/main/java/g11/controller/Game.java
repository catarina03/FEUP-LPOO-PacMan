package g11.controller;

import com.googlecode.lanterna.input.KeyType;
import g11.controller.gamestates.*;
import g11.controller.ghosts.*;
import g11.model.*;
import g11.view.GuiSquare;

import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private Boolean running;
    private Boolean winner;
    private int numberActivePP;

    private GuiSquare guiSquare;
    private GameData gameData;
    private MapReader mapReader;
    private CollisionChecker cchecker;
    private GameState gameState;

    private GuiSquare.MOVE lastmove;
    private ArrayList<GhostController> ghostControllers;


    public Game() {
        guiSquare = new GuiSquare();
        mapReader = new MapReader(new ReadFile("mapv1.txt"));
        gameState = new GameStatePresentation(this);
    }

    public void changeGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setCchecker(CollisionChecker cchecker) {
        this.cchecker = cchecker;
    }

    public void setGuiSquare(GuiSquare guiSquare) {
        this.guiSquare = guiSquare;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public Boolean getRunning() {
        return running;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void run() throws Throwable {
        // TODO Por uma taxa de atualização a cada 50 ms
        // Os Ghosts atualizam a cada 200 ms em Scatter e Chase; 250 em Frightened; 150 em Eaten
        // o Pacman a cada 200 ms
        running = true;
        winner = false;

        lastmove = GuiSquare.MOVE.LEFT;
        cchecker = new CollisionChecker();

        gameData = new GameData(new GameStats(0),
                mapReader.startingPacMan(),
                mapReader.ghostList(),
                mapReader.getMap());

        numberActivePP = gameData.getMap().getPowerPellets().size();

        ghostControllers = new ArrayList<>();
        ghostControllers.add(new GhostController(false, gameData.getGhosts().get(0), new TargetStrategyBlinky(), 0));
        ghostControllers.add(new GhostController(true, gameData.getGhosts().get(1), new TargetStrategyInky(), 5000));
        ghostControllers.add(new GhostController(true, gameData.getGhosts().get(2), new TargetStrategyPinky(), 0));
        ghostControllers.add(new GhostController(true, gameData.getGhosts().get(3), new TargetStrategyClyde(), 10000));

        long startTime = System.currentTimeMillis();
        int step = 0;

        while (running) {
            long current = System.currentTimeMillis();

            // process input
            GuiSquare.MOVE temp = guiSquare.getMove();
            if (temp != null) lastmove = temp;
            processKey(lastmove);

            // update
            update(gameData, step, System.currentTimeMillis() - startTime);
            /*
            if (step % 4 == 0) pacman.update; ghosts.update;
            if (step % 5 == 0) frightenedghost.update;
            if (step % 3 == 0) eaten.update; */

            // render
            guiSquare.draw(gameData);

            step++;
            long elapsed = System.currentTimeMillis() - current;

            if (elapsed < 50) Thread.sleep(50 - elapsed);
        }

        //winner ? winPlusResult() : loserPlusResult();
    }

    public void update(GameData gameData, int step, long elapsedTime) throws Throwable {
        this.gameData = cchecker.updateFoodCollison(gameData);

        if (gameData.getMap().getCoins().isEmpty()) {
            running = false;
            winner = true;
        }

        if (!cchecker.checkWallCollision(gameData, GuiSquare.MOVE.ESC) && step % 4 == 0)
            gameData.getPacMan().moveDirection();

        // verificar colisão com Pacman
        for (GhostController ghostController : ghostControllers) {
            if (cchecker.collide(ghostController.getGhost().getPosition(), gameData.getPacMan().getPosition())) {
                if (ghostController.getGhostState() instanceof GhostStateFrightened) {
                    ghostController.changeState(new GhostStateEaten(ghostController, ghostController.getTargetStrategy(), ghostController.getGhostState().getActivePPs()));
                    ghostController.getGhost().setState(GhostStateENUM.EATEN);
                } else if (!(ghostController.getGhostState() instanceof GhostStateEaten)) {
                    running = false;
                    winner = false;
                }
            }
        }

        //Ghosts
        //mover fantasmas
        for (GhostController ghostController : ghostControllers) {
            ghostController.update(gameData, step, elapsedTime);
            // verificar colisão com Pacman
            if (cchecker.collide(ghostController.getGhost().getPosition(), gameData.getPacMan().getPosition())) {
                if (ghostController.getGhostState() instanceof GhostStateFrightened) {
                    ghostController.changeState(new GhostStateEaten(ghostController, ghostController.getTargetStrategy(), ghostController.getGhostState().getActivePPs()));
                    ghostController.getGhost().setState(GhostStateENUM.EATEN);
                } else if (!(ghostController.getGhostState() instanceof GhostStateEaten)) {
                    running = false;
                    winner = false;
                }
            }
        }

    }

    public void processKey(GuiSquare.MOVE move) throws Throwable {
        if (move != null){
            switch (move){
                case ESC:
                    guiSquare.close();
                    running = false;
                    break;
                case UP:
                    // check if can change position
                    if (!cchecker.checkWallCollision(gameData, GuiSquare.MOVE.UP))
                        gameData.getPacMan().setOrientation(Orientation.UP);
                    break;
                case DOWN:
                    if (!cchecker.checkWallCollision(gameData, GuiSquare.MOVE.DOWN))
                        gameData.getPacMan().setOrientation(Orientation.DOWN);
                    break;
                case LEFT:
                    if (!cchecker.checkWallCollision(gameData, GuiSquare.MOVE.LEFT))
                        gameData.getPacMan().setOrientation(Orientation.LEFT);
                    break;
                case RIGHT:
                    if (!cchecker.checkWallCollision(gameData, GuiSquare.MOVE.RIGHT))
                        gameData.getPacMan().setOrientation(Orientation.RIGHT);
                    break;
            }
        }
    }

    public void closeEverything() throws Throwable {
        guiSquare.close();
    }

    public void start() throws Throwable {
        gameState.screen(guiSquare);


        /*guiSquare.inicialScreen();
        guiSquare.getKeyStroke();
        guiSquare.draw(gameData);
        guiSquare.drawNumber(3);
        Thread.sleep(1000);
        guiSquare.draw(gameData);
        guiSquare.drawNumber(2);
        Thread.sleep(1000);
        guiSquare.draw(gameData);
        guiSquare.drawNumber(1);
        Thread.sleep(1000);
        guiSquare.draw(gameData);

        run();*/

    }

}

