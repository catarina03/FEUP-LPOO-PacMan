package view;

import View.Gui;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.swing.TerminalScrollController;
import org.junit.Test;
import org.mockito.Mockito;

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
    public void getMove() throws Throwable {
        Gui gui = new Gui();
        Screen screen = Mockito.mock(Screen.class);
        gui.setScreen(screen);

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowUp));
        assertEquals(Gui.MOVE.UP, gui.getMove());
        Mockito.verify(screen, Mockito.times(1)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowDown));
        assertEquals(Gui.MOVE.DOWN, gui.getMove());
        Mockito.verify(screen, Mockito.times(2)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowRight));
        assertEquals(Gui.MOVE.RIGHT, gui.getMove());
        Mockito.verify(screen, Mockito.times(3)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowLeft));
        assertEquals(Gui.MOVE.LEFT, gui.getMove());
        Mockito.verify(screen, Mockito.times(4)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(Escape));
        assertEquals(Gui.MOVE.ESC, gui.getMove());
        Mockito.verify(screen, Mockito.times(5)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(Backspace));
        assertEquals(null, gui.getMove());
        Mockito.verify(screen, Mockito.times(6)).pollInput();
    }
}
