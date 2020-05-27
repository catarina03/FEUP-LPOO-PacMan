package g11.controller.gamestates;

import com.googlecode.lanterna.input.KeyType;
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
        guiSquare.endScreen(winner, game.getGameData().getGameStats());
        Thread.sleep(1000);
        KeyType keyType = guiSquare.getKeyStroke().getKeyType();
        if (keyType != KeyType.Escape && keyType != KeyType.EOF) {
            game.changeGameState(new GameStatePresentation(game));
            return false;
        } else
            return true;
    }
}
