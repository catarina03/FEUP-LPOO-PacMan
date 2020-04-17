import Controller.Game;
import View.Gui;

import java.io.IOException;

public class Application {
    //private Gui gui;

    public static void main(String[] args){
        Game game = new Game();
        Gui gui = new Gui(game);

        try {
            gui.draw();
            game.run();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    public void start() throws IOException {
        Gui gui = new Gui(game);
    }

     */
}
