package g11.controller.gamestates;

import com.googlecode.lanterna.input.KeyType;
import g11.controller.Game;
import g11.view.GuiSquare;

import java.io.IOException;

public class GameStateReady extends GameState {
    public GameStateReady(Game game) {
        super(game);
    }

    @Override
    public void screen(GuiSquare guiSquare) throws IOException {
        GuiSquare.MOVE temp;
        guiSquare.presentationScreen();
        if (guiSquare.getKeyStroke().getKeyType() != KeyType.Escape && guiSquare.getKeyStroke().getKeyType() != KeyType.EOF)
            game.changeGameState(new GameStateRun(game));
        else
            game.changeGameState(new GameStatePresentation(game));
    }
}
