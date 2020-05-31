package g11;

import g11.controller.Game;
import g11.view.guis.GuiRectangle;
import g11.view.guis.GuiSquare;

public class Application {
    public static void main(String[] args){
        Game game = new Game(new GuiSquare());
        try {
            game.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
