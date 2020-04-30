package view;

import Model.Elements.*;
import Model.GameData;
import Model.GameStats;
import Model.Map;
import View.Gui;
import View.ModelDraw;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static com.googlecode.lanterna.input.KeyType.*;
import static org.junit.Assert.assertEquals;

public class GuiTest {

    @Test
    public void close() throws Throwable {
        Gui gui = new Gui();
        Screen screen = Mockito.mock(Screen.class);
        gui.setScreen(screen);

        gui.close();
        Mockito.verify(screen, Mockito.times(1)).close();
    }

    @Test
    public void getMove() throws IOException {
        Gui gui = new Gui();
        Screen screen = Mockito.mock(Screen.class);
        gui.setScreen(screen);

        screen.pollInput();
        assertEquals(null, gui.getMove());

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowUp));
        assertEquals(Gui.MOVE.UP, gui.getMove());
        Mockito.verify(screen, Mockito.times(3)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowDown));
        assertEquals(Gui.MOVE.DOWN, gui.getMove());
        Mockito.verify(screen, Mockito.times(4)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowRight));
        assertEquals(Gui.MOVE.RIGHT, gui.getMove());
        Mockito.verify(screen, Mockito.times(5)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowLeft));
        assertEquals(Gui.MOVE.LEFT, gui.getMove());
        Mockito.verify(screen, Mockito.times(6)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(Escape));
        assertEquals(Gui.MOVE.ESC, gui.getMove());
        Mockito.verify(screen, Mockito.times(7)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(Backspace));
        assertEquals(null, gui.getMove());
        Mockito.verify(screen, Mockito.times(8)).pollInput();
    }

    @Test
    public void draw() throws Throwable {
        Gui gui = new Gui();
        Screen screen = Mockito.mock(Screen.class);
        gui.setScreen(screen);
        GameData gameData = Mockito.mock(GameData.class);
        GameStats gameStats = Mockito.mock(GameStats.class);
        ModelDraw modelDraw = Mockito.mock(ModelDraw.class);
        gui.setModelDraw(modelDraw);

        ArrayList<MapComponent> mc = new ArrayList<MapComponent>();
        Wall wall = Mockito.mock(Wall.class);
        EmptySpace es = Mockito.mock(EmptySpace.class);
        Coin coin = Mockito.mock(Coin.class);
        PowerPellet pp = Mockito.mock(PowerPellet.class);
        mc.add(wall);
        mc.add(es);
        mc.add(coin);
        mc.add(pp);

        Map map = Mockito.mock(Map.class);
        Mockito.when(map.getMapComponents()).thenReturn(mc);
        Mockito.when(gameData.getMap()).thenReturn(map);
        PacMan pacman = Mockito.mock(PacMan.class);
        Mockito.when(gameData.getPacMan()).thenReturn(pacman);
        Mockito.when(gameData.getGameStats()).thenReturn(gameStats);

        gui.draw(gameData);

        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(modelDraw, Mockito.times(1)).drawElement(wall);
        Mockito.verify(modelDraw, Mockito.times(1)).drawElement(es);
        Mockito.verify(modelDraw, Mockito.times(1)).drawElement(coin);
        Mockito.verify(modelDraw, Mockito.times(1)).drawElement(pp);
        Mockito.verify(modelDraw, Mockito.times(1)).drawPacMan(gameData);
        Mockito.verify(modelDraw, Mockito.times(1)).drawGameStats(gameData);
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }
}
