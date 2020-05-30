package g11.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import g11.controller.gamestates.*;
import g11.controller.ghosts.*;
import g11.model.GameData;
import g11.model.GameStats;
import g11.model.Map;
import g11.model.elements.*;
import g11.view.GuiSquare;
import g11.view.MoveENUM;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

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
    public void GameStateRunPauseScreen() throws Throwable {
        Game game = Mockito.mock(Game.class);
        GuiSquare guiSquare = Mockito.mock(GuiSquare.class);
        KeyStroke keyStroke = Mockito.mock(KeyStroke.class);

        Mockito.when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowDown).thenReturn(KeyType.Enter);
        Mockito.when(guiSquare.getKeyStroke()).thenReturn(keyStroke);

        GameStateRun gameStateRun = new GameStateRun(game);

        assertEquals(true, gameStateRun.pauseScreen(guiSquare));

        Mockito.verify(guiSquare, Mockito.times(1)).pauseScreen(0);
        Mockito.verify(keyStroke, Mockito.times(2)).getKeyType();
        Mockito.verify(guiSquare, Mockito.times(1)).pauseScreen(1);

        Mockito.when(keyStroke.getKeyType()).thenReturn(KeyType.EOF);

        assertEquals(false, gameStateRun.pauseScreen(guiSquare));
    }

    @Test
    public void GameStateRunExecute() throws Throwable {
        Game game = Mockito.mock(Game.class);
        GameData gameData = Mockito.mock(GameData.class);
        Map map = Mockito.mock(Map.class);
        GuiSquare guiSquare = Mockito.mock(GuiSquare.class);
        KeyStroke keyStroke = Mockito.mock(KeyStroke.class);

        Blinky blinky = Mockito.mock(Blinky.class);
        Inky inky = Mockito.mock(Inky.class);
        Pinky pinky = Mockito.mock(Pinky.class);
        Clyde clyde = Mockito.mock(Clyde.class);
        ArrayList<Ghost> ghosts = new ArrayList<>();
        ghosts.add(blinky);
        ghosts.add(inky);
        ghosts.add(pinky);
        ghosts.add(clyde);

        Mockito.when(game.getRunning()).thenReturn(true).thenReturn(false);
        Mockito.when(game.getGameData()).thenReturn(gameData);
        Mockito.when(gameData.getMap()).thenReturn(map);
        Mockito.when(gameData.getGhosts()).thenReturn(ghosts);
        Mockito.when(guiSquare.getMove()).thenReturn(MoveENUM.UP);

        GameStateRun gameStateRun = new GameStateRun(game);

        assertEquals(false, gameStateRun.execute(guiSquare));

        Mockito.verify(guiSquare, Mockito.times(1)).getMove();
        Mockito.verify(game, Mockito.times(1)).setLastmove(guiSquare.getMove());
        Mockito.verify(game, Mockito.times(1)).processKey(game.getLastmove());
        Mockito.verify(game, Mockito.times(1)).update(eq(gameData), eq(0), Mockito.anyLong());
        Mockito.verify(guiSquare, Mockito.times(3)).draw(gameData);
        Mockito.verify(game, Mockito.times(1)).changeGameState(any(GameStateEndScreen.class));

        Mockito.when(game.getRunning()).thenReturn(true);
        Mockito.when(guiSquare.getMove()).thenReturn(MoveENUM.ESC);
        Mockito.when(game.getLastmove()).thenReturn(MoveENUM.ESC);
        Mockito.when(game.processKey(any(MoveENUM.class))).thenReturn(true);
        Mockito.when(keyStroke.getKeyType()).thenReturn(KeyType.Enter).thenReturn(KeyType.ArrowDown).thenReturn(KeyType.Enter);
        Mockito.when(guiSquare.getKeyStroke()).thenReturn(keyStroke);

        assertEquals(false, gameStateRun.execute(guiSquare));

        Mockito.verify(game, Mockito.times(1)).setLastmove(null);
        Mockito.verify(game, Mockito.times(1)).changeGameState(any(GameStatePresentation.class));
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
