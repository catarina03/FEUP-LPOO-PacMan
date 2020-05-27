package g11.controller;

import g11.controller.gamestates.*;
import g11.controller.ghosts.*;
import g11.model.*;
import g11.view.GuiSquare;
import g11.view.MoveENUM;

import java.util.ArrayList;

public class Game {
    private Boolean running;
    private Boolean winner;
    private int numberActivePP;
    private int ticks;
    private int eatenGhosts;

    private GuiSquare guiSquare;
    private GameData gameData;
    private CollisionChecker cchecker;
    private GameState gameState;

    private MoveENUM lastmove;
    private ArrayList<GhostController> ghostControllers;


    public Game() {
        guiSquare = new GuiSquare();
        gameState = new GameStatePresentation(this);
        //gameState = new GameStateEndScreen(this, false);
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

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    public void setNumberActivePP(int numberActivePP) {
        this.numberActivePP = numberActivePP;
    }

    public GameData getGameData() {
        return gameData;
    }

    public MoveENUM getLastmove() {
        return lastmove;
    }

    public void setLastmove(MoveENUM lastmove) {
        this.lastmove = lastmove;
    }

    public void setGhostControllers(ArrayList<GhostController> ghostControllers) {
        this.ghostControllers = ghostControllers;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }


    public void update(GameData gameData, int step, long elapsedTime) {
        this.gameData = cchecker.updateFoodCollison(gameData, this);

        if (ticks > 0) {
            ticks--;
        } else eatenGhosts = 0;

        if (gameData.getMap().getCoins().isEmpty()) {
            running = false;
            winner = true;
        }

        if (!cchecker.checkWallCollision(gameData, MoveENUM.ESC) && step % 4 == 0)
            gameData.getPacMan().moveDirection();

        // Detect Ghost collision with Pac-Man
        ghostCollisionAndUpdate(step, elapsedTime, false);

        // Update Ghosts and Detect Colisions with Pac-Man
        ghostCollisionAndUpdate(step, elapsedTime, true);
    }

    public void ghostCollisionAndUpdate(int step, long elapsedTime, Boolean update) {
        for (GhostController ghostController : ghostControllers) {
            if (update)
                ghostController.update(gameData, step, elapsedTime);
            if (cchecker.collide(ghostController.getGhost().getPosition(), gameData.getPacMan().getPosition())) {
                if (ghostController.getGhostState() instanceof GhostStateFrightened) {
                    // TODO acresentar score dependendo do fantasma
                    // acrescentar numero de fantasmas comidos neste state de frightened
                    eatenGhosts++;
                    gameData.getGameStats().incrementEatenGhosts(eatenGhosts);
                    ghostController.changeState(new GhostStateEaten(ghostController, ghostController.getTargetStrategy(), ghostController.getGhostState().getActivePPs()));
                    ghostController.getGhost().setState(GhostStateENUM.EATEN);
                } else if (!(ghostController.getGhostState() instanceof GhostStateEaten)) {
                    running = false;
                    winner = false;
                }
            }
        }
    }

    public Boolean processKey(MoveENUM move) throws Throwable {
        if (move != null) {
            switch (move) {
                case ESC:
                    return true;
                case UP:
                    // check if can change position
                    if (!cchecker.checkWallCollision(gameData, MoveENUM.UP))
                        gameData.getPacMan().setOrientationENUM(OrientationENUM.UP);
                    break;
                case DOWN:
                    if (!cchecker.checkWallCollision(gameData, MoveENUM.DOWN))
                        gameData.getPacMan().setOrientationENUM(OrientationENUM.DOWN);
                    break;
                case LEFT:
                    if (!cchecker.checkWallCollision(gameData, MoveENUM.LEFT))
                        gameData.getPacMan().setOrientationENUM(OrientationENUM.LEFT);
                    break;
                case RIGHT:
                    if (!cchecker.checkWallCollision(gameData, MoveENUM.RIGHT))
                        gameData.getPacMan().setOrientationENUM(OrientationENUM.RIGHT);
                    break;
            }
            return false;
        }
        return false;
    }

    public void start() throws Throwable {
        Boolean close;
        do {
            close = gameState.execute(guiSquare);
        } while (!close);
        guiSquare.close();
    }

}

