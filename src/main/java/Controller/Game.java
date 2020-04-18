package Controller;

import Model.Elements.Wall;
import Model.Orientation;
import Model.GameData;
import Model.Position;
import View.Gui;

import java.util.ArrayList;

public class Game {
    private Gui gui;
    private GameData gameData;
    private Boolean running;

    public Game() {
        gui = new Gui();
        gameData = new GameData();
    }

    public Gui getGui() {
        return gui;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void run() throws Throwable {
        running = true;
        long startTime = System.currentTimeMillis();
        boolean alreadyin = false;
        // ciclo de jogo
        while(running) {
            processKey(gui.getMove());
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
        if (!checkWallColison()){
            gameData.update();
        }
    }

    private Position pacManNextPosition(){
        Position position = gameData.getPacMan().getPosition();
        Orientation orientation = gameData.getPacMan().getOrientation();
        switch (orientation){
            case UP:
                return position.up();
            case DOWN:
                return position.down();
            case LEFT:
                return position.left();
            case RIGHT:
                return position.right();
        }
        return position;
    }

    private boolean checkWallColison(){
        ArrayList<Wall> walls = gameData.getMap().getWalls();
        Position position = pacManNextPosition();
        for(Wall wall : walls){
            if (wall.getPosition().getX() == position.getX() && wall.getPosition().getY() == position.getY()){
                return true;
            }
        }
        return false;
    }

    public void processKey(Gui.MOVE move) throws Throwable {
        if (move != null){
            switch (move){
                case ESC:
                    gui.close();
                    running = false;
                    break;
                case UP:
                    gameData.getPacMan().setOrientation(Orientation.UP);
                    break;
                case DOWN:
                    gameData.getPacMan().setOrientation(Orientation.DOWN);
                    break;
                case LEFT:
                    gameData.getPacMan().setOrientation(Orientation.LEFT);
                    break;
                case RIGHT:
                    gameData.getPacMan().setOrientation(Orientation.RIGHT);
                    break;
            }
        }

    }
}

