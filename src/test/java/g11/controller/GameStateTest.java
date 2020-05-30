package g11.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import g11.controller.gamestates.*;
import g11.model.GameData;
import g11.model.GameStats;
import g11.view.GuiSquare;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GameStateTest {
    @Test
    public void GameStatePresentation() throws Throwable {
        Game game = Mockito.mock(Game.class);
        GuiSquare guiSquare = Mockito.mock(GuiSquare.class);
        GameStatePresentation presentation = new GameStatePresentation(game);
        KeyStroke k = Mockito.mock(KeyStroke.class);

        Mockito.when(guiSquare.getKeyStroke()).thenReturn(k);
        Mockito.when(k.getKeyType()).thenReturn(KeyType.ArrowDown);

        assertEquals(false, presentation.execute(guiSquare));

        Mockito.verify(guiSquare, Mockito.times(1)).presentationScreen();
        Mockito.verify(k, Mockito.times(1)).getKeyType();

        Mockito.when(k.getKeyType()).thenReturn(KeyType.EOF);
        assertEquals(true, presentation.execute(guiSquare));

        Mockito.verify(guiSquare, Mockito.times(2)).presentationScreen();
        Mockito.verify(k, Mockito.times(2)).getKeyType();
    }

    @Test
    public void GameStateReady() throws Throwable {
        Game game = Mockito.mock(Game.class);
        GuiSquare guiSquare = Mockito.mock(GuiSquare.class);
        GameStateReady ready = new GameStateReady(game);
        KeyStroke k = Mockito.mock(KeyStroke.class);

        Mockito.when(guiSquare.getKeyStroke()).thenReturn(k);
        Mockito.when(k.getKeyType()).thenReturn(KeyType.ArrowDown);

        assertEquals(false, ready.execute(guiSquare));

        Mockito.verify(guiSquare, Mockito.times(1)).inicialScreen();
        Mockito.verify(k, Mockito.times(1)).getKeyType();

        Mockito.when(k.getKeyType()).thenReturn(KeyType.EOF);
        assertEquals(false, ready.execute(guiSquare));

        Mockito.verify(guiSquare, Mockito.times(2)).inicialScreen();
        Mockito.verify(k, Mockito.times(2)).getKeyType();
    }

    @Test
    public void GameStateRun(){

    }

    @Test
    public void GameStateEndScreen() throws Throwable {
        Game game = Mockito.mock(Game.class);
        GameData gameData = Mockito.mock(GameData.class);
        GameStats gameStats = Mockito.mock(GameStats.class);
        GuiSquare guiSquare = Mockito.mock(GuiSquare.class);
        KeyStroke k = Mockito.mock(KeyStroke.class);

        GameStateEndScreen endScreen = new GameStateEndScreen(game, true);

        Mockito.when(game.getGameData()).thenReturn(gameData);
        Mockito.when(gameData.getGameStats()).thenReturn(gameStats);
        Mockito.when(guiSquare.getKeyStroke()).thenReturn(k);
        Mockito.when(k.getKeyType()).thenReturn(KeyType.ArrowDown);

        assertEquals(false, endScreen.execute(guiSquare));

        Mockito.verify(guiSquare, Mockito.times(1)).endScreen(true, gameStats);
        Mockito.verify(k, Mockito.times(1)).getKeyType();

        Mockito.when(k.getKeyType()).thenReturn(KeyType.Escape);

        assertEquals(true, endScreen.execute(guiSquare));
    }
}
