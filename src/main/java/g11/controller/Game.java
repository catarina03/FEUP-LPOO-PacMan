package g11.controller;

import g11.model.*;
import g11.model.elements.Ghost;
import g11.view.GuiSquare;

import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private GuiSquare guiSquare;
    private GameData gameData;
    private Boolean running;
    private MapReader mapReader;
    private GuiSquare.MOVE lastmove;
    private CollisionChecker cchecker;
    private ArrayList<GhostController> ghostControllers;

    public Game() {
        guiSquare = new GuiSquare();
        mapReader = new MapReader(new ReadFile("mapv1.txt"));
      
        ghostControllers = new ArrayList<>();
        ghostControllers.add(new GhostControllerBlinky());
        ghostControllers.add(new GhostControllerPinky());
        ghostControllers.add(new GhostControllerInky());
        ghostControllers.add(new GhostControllerClyde());
        gameData = new GameData(new GameStats(0),
                                mapReader.startingPacMan(),
                                mapReader.ghostList(),
                                mapReader.getMap());
        cchecker = new CollisionChecker();
        lastmove = GuiSquare.MOVE.LEFT;
        mapReader = null; // limpar a informação aqui guardada (pode ser retirado depois para recomeçar o nivel)
    }

    public void setCchecker(CollisionChecker cchecker) {
        this.cchecker = cchecker;
    }

    public void setGuiSquare(GuiSquare guiSquare) {
        this.guiSquare = guiSquare;
    }

    public Boolean getRunning() {
        return running;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public void run() throws Throwable {
        // TODO Por uma taxa de atualização a cada 50 ms
        // Os Ghosts atualizam a cada 200 ms em Scatter e Chase; 250 em Frightened; 150 em Eaten
        // o Pacman a cada 200 ms

        // TENTATIVA de TESTE
        running = true;
        long startTime = System.currentTimeMillis();
        int step = 0;

        while(running){
            long current = System.currentTimeMillis();

            // process input
            GuiSquare.MOVE temp = guiSquare.getMove();
            if (temp != null) lastmove = temp;
            processKey(lastmove);

            // update
            update(gameData, System.currentTimeMillis() - startTime, step);
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
    }

    public void update(GameData gameData, long elapsedTime, int step) throws Throwable {

        // Can Pacman move to next position?
            // Pacman's next position?
            // Is a wall in the position pacman is about to move to?
        // yes -> update position
        // no  -> don't update
        this.gameData = cchecker.updateFoodCollison(gameData);
        if (!cchecker.checkWallCollision(gameData, GuiSquare.MOVE.ESC) && step % 4 == 0){
            gameData.getPacMan().moveDirection();
        }

        // verificar colisão com Pacman
        for (Ghost ghost : gameData.getGhosts()){
            if (cchecker.collide(ghost.getPosition(), gameData.getPacMan().getPosition())) {
                guiSquare.getTerminal().bell();
            }
        }

        //Ghosts
            //mover fantasmas
        for (GhostController ghostController : ghostControllers){
            ghostController.update(gameData, elapsedTime, step);
            // verificar colisão com Pacman
            for (Ghost ghost : gameData.getGhosts()){
                if (cchecker.collide(ghost.getPosition(), gameData.getPacMan().getPosition())) {
                    guiSquare.getTerminal().bell();
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


    public void start() throws Throwable {
       /* GuiSquare.MOVE temp;
        guiSquare.presentationScreen();
        guiSquare.getKeyStroke();
        guiSquare.inicialScreen();
        guiSquare.getKeyStroke();
        guiSquare.draw(gameData);
        Thread.sleep(3000);*/
        run();
    }
}

