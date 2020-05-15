package g11.controller;

import g11.model.*;
import g11.model.elements.Ghost;
import g11.view.Gui;

import java.util.ArrayList;

public class Game {
    private Gui gui;
    private GameData gameData;
    private Boolean running;
    private MapReader mapReader;
    private Gui.MOVE lastmove;
    private CollisionChecker cchecker;
    private ArrayList<GhostController> ghostControllers;

    public Game() {
        gui = new Gui();
        mapReader = new MapReader(new ReadFile("mapv2.txt"));

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
        lastmove = Gui.MOVE.LEFT;

        mapReader = null; // limpar a informação aqui guardada (pode ser retirado depois para recomeçar o nivel)
    }

    public void setCchecker(CollisionChecker cchecker) {
        this.cchecker = cchecker;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public Boolean getRunning() {
        return running;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public void run() throws Throwable {
        running = true;
        long startTime = System.currentTimeMillis();
        boolean alreadyin = false;

        // ciclo de jogo
        while(running) {

            Gui.MOVE temp = gui.getMove();
            if (temp != null)
                lastmove = temp;
            processKey(lastmove);

            // taxa de atualização (a cada 200 ms)
            if ((System.currentTimeMillis() - startTime) % 200 == 0){
                // como entra mais do que uma vez a cada milissegundo, só vai atualizar uma vez
                if (!alreadyin){
                    long elapsedtime = System.currentTimeMillis() - startTime;
                    update(gameData, elapsedtime);
                    gui.draw(gameData);
                    alreadyin = true; }
            }
            else{ alreadyin = false; } // assim que sair do milissegundo em que dá refresh, avisa que pode dar refresh outra vez
        }
    }

    public void update(GameData gameData, long elapsedTime) throws Throwable {

        // Can Pacman move to next position?
            // Pacman's next position?
            // Is a wall in the position pacman is about to move to?
        // yes -> update position
        // no  -> don't update
        this.gameData = cchecker.updateCoinCollison(gameData);
        if (!cchecker.checkWallCollision(gameData, Gui.MOVE.ESC)){
            gameData.getPacMan().moveDirection();
        }

        // verificar colisão com Pacman
        for (Ghost ghost : gameData.getGhosts()){
            if (cchecker.collide(ghost.getPosition(), gameData.getPacMan().getPosition())) {
                gui.getTerminal().bell();
            }
        }

        //Ghosts
            //mover fantasmas
        for (GhostController ghostController : ghostControllers){
            ghostController.update(gameData, elapsedTime);
            // verificar colisão com Pacman
            for (Ghost ghost : gameData.getGhosts()){
                if (cchecker.collide(ghost.getPosition(), gameData.getPacMan().getPosition())) {
                    gui.getTerminal().bell();
                }
            }
        }

    }

    public void processKey(Gui.MOVE move) throws Throwable {

        if (move != null){
            switch (move){
                case ESC:
                    gui.close();
                    running = false;
                    break;
                case UP:
                    // check if can change position
                    if (!cchecker.checkWallCollision(gameData, Gui.MOVE.UP))
                        gameData.getPacMan().setOrientation(Orientation.UP);
                    break;
                case DOWN:
                    if (!cchecker.checkWallCollision(gameData, Gui.MOVE.DOWN))
                        gameData.getPacMan().setOrientation(Orientation.DOWN);
                    break;
                case LEFT:
                    if (!cchecker.checkWallCollision(gameData, Gui.MOVE.LEFT))
                        gameData.getPacMan().setOrientation(Orientation.LEFT);
                    break;
                case RIGHT:
                    if (!cchecker.checkWallCollision(gameData, Gui.MOVE.RIGHT))
                        gameData.getPacMan().setOrientation(Orientation.RIGHT);
                    break;
            }
        }
    }



}

