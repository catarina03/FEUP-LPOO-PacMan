package g11.controller.gamestates;

import com.googlecode.lanterna.input.KeyType;
import g11.controller.Game;
import g11.view.GuiSquare;

import java.io.IOException;


public class GameStatePresentation extends GameState {
    public GameStatePresentation(Game game) {
        super(game);
    }

    @Override
    public void screen(GuiSquare guiSquare) throws Throwable {
        GuiSquare.MOVE temp;
        guiSquare.presentationScreen();
        if (guiSquare.getKeyStroke().getKeyType() != KeyType.Escape && guiSquare.getKeyStroke().getKeyType() != KeyType.EOF) {
            game.changeGameState(new GameStateReady(game));
            game.getGameState().screen(guiSquare);
        } else
            game.closeEverything();
    }

}
