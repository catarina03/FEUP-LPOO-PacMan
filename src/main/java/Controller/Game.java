package Controller;

import Model.Orientation;
import Model.GameData;
import View.Gui;

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
                    gameData.update();
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

    public void processKey(Gui.MOVE move) throws Throwable {
        if (move != null){
            switch (move){
                case ESC:
                    gui.close();
                    running = false;
                    break;
                case UP:
                    gameData.getPacMan().setDirection(Orientation.UP);
                    break;
                case DOWN:
                    gameData.getPacMan().setDirection(Orientation.DOWN);
                    break;
                case LEFT:
                    gameData.getPacMan().setDirection(Orientation.LEFT);
                    break;
                case RIGHT:
                    gameData.getPacMan().setDirection(Orientation.RIGHT);
                    break;
            }
        }

    }
}

