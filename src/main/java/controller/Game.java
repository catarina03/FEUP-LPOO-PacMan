package controller;

import model.*;
import model.elements.*;
import view.Gui;

import java.util.ArrayList;

public class Game {
    private Gui gui;
    private GameData gameData;
    private Boolean running;
    private MapReader mapReader;
    private Gui.MOVE lastmove;
    private CollisionChecker cchecker;

    public Game() {
        gui = new Gui();
        mapReader = new MapReader("mapv2.txt");
        gameData = new GameData(new GameStats(0),
                                mapReader.startingPacMan(),
                                mapReader.ghostList(),
                                mapReader.getMap());
        lastmove = Gui.MOVE.LEFT;
        cchecker = new CollisionChecker();
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
            // taxa de atualização
            if ((System.currentTimeMillis() - startTime) % 200 == 0){
                // como entra mais do que uma vez a cada milissegundo, só vai atualizar uma vez
                if (!alreadyin){
                    Update(gameData);
                    gui.draw(gameData);
                    alreadyin = true;
                }
            }
            else{
                // assim que sair do milissegundo em que dá refresh, avisa que pode dar refresh outra vez
                alreadyin = false;
            }
        }
    }

    private void Update(GameData gameData) {
        // Can Pacman move to next position?
            // Pacman's next position?
            // Is a wall in the position pacman is about to move to?
        // yes -> update position
        // no  -> don't update
        this.gameData = cchecker.updateCoinCollison(gameData);
        if (!cchecker.checkWallCollision(gameData, Gui.MOVE.ESC)){
            gameData.update();
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

