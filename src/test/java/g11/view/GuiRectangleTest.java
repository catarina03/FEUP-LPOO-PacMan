package g11.view;

import g11.model.elements.*;
import g11.model.GameData;
import g11.model.GameStats;
import g11.model.Map;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static com.googlecode.lanterna.input.KeyType.*;
import static org.junit.Assert.assertEquals;

public class GuiRectangleTest {

    @Test
    public void close() throws Throwable {
        GuiRectangle guiRectangle = new GuiRectangle(1);
        Screen screen = Mockito.mock(Screen.class);
        guiRectangle.setScreen(screen);

        guiRectangle.close();
        Mockito.verify(screen, Mockito.times(1)).close();
    }

    @Test
    public void getMove() throws IOException {
        GuiRectangle guiRectangle = new GuiRectangle(1);
        Screen screen = Mockito.mock(Screen.class);
        guiRectangle.setScreen(screen);

        screen.pollInput();
        assertEquals(null, guiRectangle.getMove());

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowUp));
        assertEquals(GuiRectangle.MOVE.UP, guiRectangle.getMove());
        Mockito.verify(screen, Mockito.times(3)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowDown));
        assertEquals(GuiRectangle.MOVE.DOWN, guiRectangle.getMove());
        Mockito.verify(screen, Mockito.times(4)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowRight));
        assertEquals(GuiRectangle.MOVE.RIGHT, guiRectangle.getMove());
        Mockito.verify(screen, Mockito.times(5)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowLeft));
        assertEquals(GuiRectangle.MOVE.LEFT, guiRectangle.getMove());
        Mockito.verify(screen, Mockito.times(6)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(Escape));
        assertEquals(GuiRectangle.MOVE.ESC, guiRectangle.getMove());
        Mockito.verify(screen, Mockito.times(7)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(Backspace));
        assertEquals(null, guiRectangle.getMove());
        Mockito.verify(screen, Mockito.times(8)).pollInput();
    }

    @Test
    public void draw() throws Throwable {
        GuiRectangle guiRectangle = new GuiRectangle(1);
        Screen screen = Mockito.mock(Screen.class);
        guiRectangle.setScreen(screen);
        GameData gameData = Mockito.mock(GameData.class);
        GameStats gameStats = Mockito.mock(GameStats.class);
        ModelDrawRectangle modelDrawRectangle = Mockito.mock(ModelDrawRectangle.class);
        guiRectangle.setModelDraw(modelDrawRectangle);

        ArrayList<MapComponent> mc = new ArrayList<>();
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

        guiRectangle.draw(gameData);

        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(modelDrawRectangle, Mockito.times(1)).drawElement(wall);
        Mockito.verify(modelDrawRectangle, Mockito.times(1)).drawElement(es);
        Mockito.verify(modelDrawRectangle, Mockito.times(1)).drawElement(coin);
        Mockito.verify(modelDrawRectangle, Mockito.times(1)).drawElement(pp);
        Mockito.verify(modelDrawRectangle, Mockito.times(1)).drawPacMan(gameData);
        Mockito.verify(modelDrawRectangle, Mockito.times(1)).drawGameStats(gameData);
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }


}