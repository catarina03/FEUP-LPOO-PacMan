package g11.controller.gamestates;

import com.googlecode.lanterna.input.KeyType;
import g11.controller.Game;
import g11.view.Gui;

import java.io.IOException;

public class GameStateReady extends GameState {
    public GameStateReady(Game game) {
        super(game);
    }

    @Override
    public Boolean execute(Gui gui) throws IOException {
        gui.inicialScreen();
        KeyType keyType = gui.getKeyStroke().getKeyType();
        if (keyType != KeyType.Escape && keyType != KeyType.EOF)
            game.changeGameState(new GameStateRun(game));
        else
            game.changeGameState(new GameStatePresentation(game));
        return false;
    }
}
