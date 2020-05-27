package g11;

import g11.controller.Game;

public class Application {
    public static void main(String[] args){
        Game game = new Game();
        try {
            game.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
