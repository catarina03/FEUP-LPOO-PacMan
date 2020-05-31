package g11.view;

import g11.model.elements.*;
import g11.model.GameData;
import g11.model.GameStats;
import g11.model.Map;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import g11.model.elements.map.Coin;
import g11.model.elements.map.EmptySpace;
import g11.model.elements.map.PowerPellet;
import g11.model.elements.map.Wall;
import g11.view.guis.GuiRectangle;
import g11.view.guis.GuiRectangle;
import g11.view.modeldraws.ModelDrawRectangle;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static com.googlecode.lanterna.input.KeyType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GuiRectangleTest {

    @Test
    public void close() throws Throwable {
        Screen screen = Mockito.mock(Screen.class);
        GuiRectangle guiRectangle = new GuiRectangle(screen);

        guiRectangle.close();
        Mockito.verify(screen, Mockito.times(1)).close();
    }

    @Test
    public void getMove() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        GuiRectangle guiRectangle = new GuiRectangle(screen);

        screen.pollInput();
        assertNull(guiRectangle.getMove());

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowUp));
        assertEquals(MoveEnumeration.UP, guiRectangle.getMove());
        Mockito.verify(screen, Mockito.times(3)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowDown));
        assertEquals(MoveEnumeration.DOWN, guiRectangle.getMove());
        Mockito.verify(screen, Mockito.times(4)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowRight));
        assertEquals(MoveEnumeration.RIGHT, guiRectangle.getMove());
        Mockito.verify(screen, Mockito.times(5)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowLeft));
        assertEquals(MoveEnumeration.LEFT, guiRectangle.getMove());
        Mockito.verify(screen, Mockito.times(6)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(Escape));
        assertEquals(MoveEnumeration.ESC, guiRectangle.getMove());
        Mockito.verify(screen, Mockito.times(7)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(Backspace));
        assertNull(guiRectangle.getMove());
        Mockito.verify(screen, Mockito.times(8)).pollInput();
    }


    @Test
    public void draw() throws Throwable {
        Screen screen = Mockito.mock(Screen.class);
        GuiRectangle guiRectangle = new GuiRectangle(screen);
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