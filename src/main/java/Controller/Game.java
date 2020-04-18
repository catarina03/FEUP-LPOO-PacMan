package Controller;

import Model.GameData;
import View.Gui;

import java.io.IOException;

public class Game {
    private Gui gui;
    private GameData gameData;

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

    public void run() throws IOException {
        long startTime = System.currentTimeMillis();
        boolean alreadyin = false;
        // ciclo de jogo
        while(true) {
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
        /*if (screen != null)
            screen.close();*/
    }

}

