package g11.controller.gamestates;

import g11.controller.Game;
import g11.view.Gui;


public abstract class GameState {
    Game game;

    public GameState(Game game) {
        this.game = game;
    }

    public abstract Boolean execute(Gui gui) throws Throwable;
}
