package Controller;

import Model.GameData;
import View.Gui;

public class Game {
    private Gui gui;
    private GameData gameData;
    private Boolean running;

    public Game() {
        gui = new Gui();
        gameData = new GameData();
        running = true;
    }

    public Gui getGui() {
        return gui;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void run() throws Throwable {
        long startTime = System.currentTimeMillis();
        boolean alreadyin = false;
        // ciclo de jogo
        while(running) {
            Gui.MOVE move = gui.getMove();
            processKey(move);
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
                    gameData.getPacMan().setDirection('N');
                    break;
                case DOWN:
                    gameData.getPacMan().setDirection('S');
                    break;
                case LEFT:
                    gameData.getPacMan().setDirection('W');
                    break;
                case RIGHT:
                    gameData.getPacMan().setDirection('E');
                    break;
            }
        }

    }
}

