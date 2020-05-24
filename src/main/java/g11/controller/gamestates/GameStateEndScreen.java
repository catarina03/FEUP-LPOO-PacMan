package g11.controller.gamestates;

import g11.controller.Game;
import g11.view.GuiSquare;

public class GameStateEndScreen extends GameState {
    Boolean winner;

    public GameStateEndScreen(Game game, Boolean winner) {
        super(game);
        this.winner = winner;
    }

    @Override
    public Boolean execute(GuiSquare guiSquare) throws Throwable {
        guiSquare.inicialScreen();
        return true;
    }
}
