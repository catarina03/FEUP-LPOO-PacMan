package g11.controller;

import g11.model.*;
import g11.view.GuiSquare;

public class Game {
    private GuiSquare guiSquare;
    private GameData gameData;
    private Boolean running;
    private MapReader mapReader;
    private GuiSquare.MOVE lastmove;
    private CollisionChecker cchecker;

    public Game() {
        guiSquare = new GuiSquare();
        mapReader = new MapReader(new ReadFile("mapv1.txt"));
        gameData = new GameData(new GameStats(0),
                                mapReader.startingPacMan(),
                                mapReader.ghostList(),
                                mapReader.getMap());
        cchecker = new CollisionChecker();
        lastmove = GuiSquare.MOVE.LEFT;
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
        running = true;
        long startTime = System.currentTimeMillis();
        boolean alreadyin = false;
        // ciclo de jogo
        while(running) {
            GuiSquare.MOVE temp = guiSquare.getMove();
            if (temp != null)
                lastmove = temp;
            processKey(lastmove);
            // taxa de atualização
            if ((System.currentTimeMillis() - startTime) % 200 == 0){
                // como entra mais do que uma vez a cada milissegundo, só vai atualizar uma vez
                if (!alreadyin){
                    update(gameData);
                    guiSquare.draw(gameData);
                    alreadyin = true;
                }
            }
            else{
                // assim que sair do milissegundo em que dá refresh, avisa que pode dar refresh outra vez
                alreadyin = false;
            }
        }
    }

    public void update(GameData gameData) {

        // Can Pacman move to next position?
            // Pacman's next position?
            // Is a wall in the position pacman is about to move to?
        // yes -> update position
        // no  -> don't update
        this.gameData = cchecker.updateCoinCollison(gameData);
        if (!cchecker.checkWallCollision(gameData, GuiSquare.MOVE.ESC)){
            gameData.update();
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



}

