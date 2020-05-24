package g11.controller.gamestates;

import g11.controller.Game;
import g11.view.GuiSquare;

import java.io.IOException;

public class GameStateRun extends GameState {

    public GameStateRun(Game game) {
        super(game);
    }

    @Override
    public void screen(GuiSquare guiSquare) throws IOException {
        game.changeGameState(new GameStatePresentation(game));
    }
}
