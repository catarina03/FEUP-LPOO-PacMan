package g11.controller;

import g11.controller.gamestates.*;
import g11.controller.ghosts.*;
import g11.controller.ghosts.states.GhostStateEaten;
import g11.controller.ghosts.states.GhostStateFrightened;
import g11.model.*;
import g11.view.Gui;
import g11.view.guis.GuiSquare;
import g11.view.MoveEnumeration;

import java.util.ArrayList;

public class Game {
    private boolean running;
    private boolean winner;
    private int numberActivePP;
    private int ticks;
    private int eatenGhosts;

    private Gui gui;
    private GameData gameData;
    private CollisionChecker cchecker;
    private GameState gameState;

    private MoveEnumeration lastmove;
    private ArrayList<GhostController> ghostControllers;


    public Game(Gui gui) {
        this.gui = gui;
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
        this.gui = guiSquare;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public boolean getRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean getWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public void setNumberActivePP(int numberActivePP) {
        this.numberActivePP = numberActivePP;
    }

    public GameData getGameData() {
        return gameData;
    }

    public MoveEnumeration getLastmove() {
        return lastmove;
    }

    public void setLastmove(MoveEnumeration lastmove) {
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
                    // TODO acresentar score dependendo do fantasma
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
        gui.close();
    }

}

