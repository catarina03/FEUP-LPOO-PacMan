package g11.controller.gamestates;

import com.googlecode.lanterna.input.KeyType;
import g11.controller.Game;
import g11.view.Gui;


public class GameStatePresentation extends GameState {
    public GameStatePresentation(Game game) {
        super(game);
    }

    @Override
    public Boolean execute(Gui gui) throws Throwable {
        gui.presentationScreen();
        KeyType keyType = gui.getKeyStroke().getKeyType();
        if (keyType != KeyType.Escape && keyType != KeyType.EOF) {
            game.changeGameState(new GameStateReady(game));
            return false;
        } else
            return true;
    }
}
