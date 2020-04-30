package view;

import Model.Elements.*;
import Model.GameData;
import Model.Map;
import View.Gui;
import View.ModelDraw;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.swing.TerminalScrollController;
import org.junit.Test;
import org.mockito.Mock;
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

    /*
    @Test
    public void draw() throws Throwable {
        GameData gamedata = Mockito.mock(GameData.class);
        Map map = Mockito.mock(Map.class);
        ModelDraw modelDraw = Mockito.mock(ModelDraw.class);
        PacMan pacman = Mockito.mock(PacMan.class);

        gamedata.setPacMan(pacman);

        ArrayList<MapComponent> mc = new ArrayList<MapComponent>();

        Wall wall = Mockito.mock(Wall.class);
        EmptySpace es = Mockito.mock(EmptySpace.class);
        Coin coin  = Mockito.mock(Coin.class);
        PowerPellet pp = Mockito.mock(PowerPellet.class);

        mc.add(wall);
        mc.add(es);
        mc.add(coin);
        mc.add(pp);

        map.setMapComponents(mc);
        gamedata.setMap(map);

        Gui gui = new Gui();
        gui.setModelDraw(modelDraw);
        gui.draw(gamedata);

        Mockito.verify(modelDraw, Mockito.times(1)).drawElement(pacman);
        
     */

        /*
        Mockito.verify(modelDraw, Mockito.times(1)).drawElement(wall);
        Mockito.verify(modelDraw, Mockito.times(1)).drawElement(es);
        Mockito.verify(modelDraw, Mockito.times(1)).drawElement(coin);
        Mockito.verify(modelDraw, Mockito.times(1)).drawElement(pp);

         */

        /*
        Mockito.verify(modelDraw, Mockito.times(4)).drawElemen
        Screen screen = Mockito.mock(Screen.class);
        gui.setScreen(screen);

        gui.close();
        Mockito.verify(screen, Mockito.times(1)).close();
        }
        */

}
