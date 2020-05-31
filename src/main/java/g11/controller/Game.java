package g11.controller;

import g11.controller.gamestates.*;
import g11.controller.ghosts.*;
import g11.controller.ghosts.states.GhostStateEaten;
import g11.controller.ghosts.states.GhostStateFrightened;
import g11.model.*;
import g11.view.Gui;
import g11.view.guis.GuiSquare;
import g11.view.MoveEnumeration;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Game {
    private boolean running;
    private boolean winner;
    private int ticks;
    private int eatenGhosts;
    private int highScore;

    private Gui gui;
    private GameData gameData;
    private CollisionChecker cchecker;
    private GameState gameState;

    private MoveEnumeration lastmove;
    private ArrayList<GhostController> ghostControllers;

    public Game(Gui gui) {
        this.gui = gui;
        this.gameState = new GameStatePresentation(this);

        ReadFile readFile = (gui instanceof GuiSquare) ? new ReadFile("mapv1.txt") : new ReadFile("mapv2.txt");
        ArrayList<String> lines = readFile.fileContent();
        this.highScore = Integer.parseInt(lines.get(0));
    }

    public boolean isRunning() { return running; }

    public boolean isWinner() { return winner; }

    public void setRunning(boolean running) { this.running = running; }

    public void setWinner(boolean winner) { this.winner = winner; }

    public void setTicks(int ticks) { this.ticks = ticks; }

    public int getHighScore() { return highScore; }

    public void setGui(Gui gui) { this.gui = gui; }

    public void setHighScore(int highScore) { this.highScore = highScore; }

    public GameData getGameData() { return gameData; }

    public void setGameData(GameData gameData) { this.gameData = gameData; }

    public void setCchecker(CollisionChecker cchecker) { this.cchecker = cchecker; }

    public MoveEnumeration getLastmove() { return lastmove; }

    public void setLastmove(MoveEnumeration lastmove) { this.lastmove = lastmove; }

    public void setGhostControllers(ArrayList<GhostController> ghostControllers) { this.ghostControllers = ghostControllers; }

    public void changeGameState(GameState gameState) { this.gameState = gameState; }

    
    public void update(GameData gameData, int step, long elapsedTime) {
        this.gameData = cchecker.updateFoodCollison(gameData, this);

        if (ticks > 0) {
            ticks--;
        } else eatenGhosts = 0;

        if (gameData.getMap().getCoins().isEmpty()) {
            running = false;
            winner = true;
        }

        if (!cchecker.checkWallCollision(gameData, MoveEnumeration.ESC) && step % 4 == 0)
            gameData.getPacMan().moveDirection();

        // Detect Ghost collision with Pac-Man
        ghostCollisionAndUpdate(step, elapsedTime, false);

        // Update Ghosts and Detect Colisions with Pac-Man
        ghostCollisionAndUpdate(step, elapsedTime, true);
    }

    public void ghostCollisionAndUpdate(int step, long elapsedTime, boolean update) {
        for (GhostController ghostController : ghostControllers) {
            if (update)
                ghostController.update(gameData, step, elapsedTime);
            if (cchecker.collide(ghostController.getGhost().getPosition(), gameData.getPacMan().getPosition())) {
                if (ghostController.getGhostState() instanceof GhostStateFrightened) {
                    // acrescentar numero de fantasmas comidos neste state de frightened
                    eatenGhosts++;
                    gameData.getGameStats().incrementEatenGhosts(eatenGhosts);
                    ghostController.changeState(new GhostStateEaten(ghostController, ghostController.getTargetStrategy(), ghostController.getGhostState().getActivePPs()));
                    ghostController.getGhost().setState(GhostStateEnumeration.EATEN);
                } else if (!(ghostController.getGhostState() instanceof GhostStateEaten)) {
                    running = false;
                    winner = false;
                }
            }
        }
    }

    public boolean processKey(MoveEnumeration move) {
        if (move != null) {
            switch (move) {
                case ESC:
                    return true;
                case UP:
                    // check if can change position
                    if (!cchecker.checkWallCollision(gameData, MoveEnumeration.UP))
                        gameData.getPacMan().setOrientationEnumeration(OrientationEnumeration.UP);
                    break;
                case DOWN:
                    if (!cchecker.checkWallCollision(gameData, MoveEnumeration.DOWN))
                        gameData.getPacMan().setOrientationEnumeration(OrientationEnumeration.DOWN);
                    break;
                case LEFT:
                    if (!cchecker.checkWallCollision(gameData, MoveEnumeration.LEFT))
                        gameData.getPacMan().setOrientationEnumeration(OrientationEnumeration.LEFT);
                    break;
                case RIGHT:
                    if (!cchecker.checkWallCollision(gameData, MoveEnumeration.RIGHT))
                        gameData.getPacMan().setOrientationEnumeration(OrientationEnumeration.RIGHT);
                    break;
            }
            return false;
        }
        return false;
    }

    public void start() throws Throwable {
        boolean close;
        do {
            close = gameState.execute(gui);
        } while (!close);
        saveHighScore();
        gui.close();
    }

    private void saveHighScore() throws FileNotFoundException, UnsupportedEncodingException {
        String fileName = (gui instanceof GuiSquare) ? "mapv1.txt" : "mapv2.txt";

        ReadFile readFile = new ReadFile(fileName);
        ArrayList<String> contents = readFile.fileContent();

        PrintWriter writer = new PrintWriter("./src/main/resources/" + fileName, "UTF-8");
        writer.println(highScore);
        for (int i = 1; i < contents.size(); i++) {
            writer.printf(contents.get(i));
            if (i != contents.size() - 1)
                writer.printf("%n");
        }
        writer.close();
    }
}
