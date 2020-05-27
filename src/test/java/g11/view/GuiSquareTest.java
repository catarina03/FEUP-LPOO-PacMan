package g11.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import g11.model.elements.*;
import g11.model.GameData;
import g11.model.GameStats;
import g11.model.Map;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static com.googlecode.lanterna.input.KeyType.*;
import static org.junit.Assert.assertEquals;

public class GuiSquareTest {

    @Test
    public void close() throws Throwable {
        GuiSquare guiSquare = new GuiSquare(1);
        Screen screen = Mockito.mock(Screen.class);
        guiSquare.setScreen(screen);

        guiSquare.close();
        Mockito.verify(screen, Mockito.times(1)).close();
    }

    /*
    @Test
    public void getMove() throws IOException {
        GuiSquare guiSquare = new GuiSquare(1);
        Screen screen = Mockito.mock(Screen.class);
        guiSquare.setScreen(screen);

        screen.pollInput();
        assertEquals(null, guiSquare.getMove());

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowUp));
        assertEquals(GuiSquare.MOVE.UP, guiSquare.getMove());
        Mockito.verify(screen, Mockito.times(3)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowDown));
        assertEquals(GuiSquare.MOVE.DOWN, guiSquare.getMove());
        Mockito.verify(screen, Mockito.times(4)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowRight));
        assertEquals(GuiSquare.MOVE.RIGHT, guiSquare.getMove());
        Mockito.verify(screen, Mockito.times(5)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(ArrowLeft));
        assertEquals(GuiSquare.MOVE.LEFT, guiSquare.getMove());
        Mockito.verify(screen, Mockito.times(6)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(Escape));
        assertEquals(GuiSquare.MOVE.ESC, guiSquare.getMove());
        Mockito.verify(screen, Mockito.times(7)).pollInput();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(Backspace));
        assertEquals(null, guiSquare.getMove());
        Mockito.verify(screen, Mockito.times(8)).pollInput();
    }
     */

    @Test
    public void draw() throws Throwable {
        GuiSquare guiSquare = new GuiSquare(1);
        Screen screen = Mockito.mock(Screen.class);
        guiSquare.setScreen(screen);
        GameData gameData = Mockito.mock(GameData.class);
        GameStats gameStats = Mockito.mock(GameStats.class);
        ModelDrawSquare modelDraw = Mockito.mock(ModelDrawSquare.class);
        guiSquare.setModelDraw(modelDraw);

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

        guiSquare.draw(gameData);

        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(modelDraw, Mockito.times(1)).drawElement(wall);
        Mockito.verify(modelDraw, Mockito.times(1)).drawElement(es);
        Mockito.verify(modelDraw, Mockito.times(1)).drawElement(coin);
        Mockito.verify(modelDraw, Mockito.times(1)).drawElement(pp);
        Mockito.verify(modelDraw, Mockito.times(1)).drawPacMan(gameData);
        Mockito.verify(modelDraw, Mockito.times(1)).drawGameStats(gameData);
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }

    /*
    @Test
    public void presentationScreen() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);



        Mockito.verify(screen, Mockito.times(1)).clear();;
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.BLACK);

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.WHITE);
        Mockito.verify(graphics, Mockito.times(1)).putString(3, 0, "1UP   HIGH SCORE   2UP", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(5, 5, "CHARACTER / NICKNAME", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(11, 26, "10 PTS", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(11, 27, "50 PTS", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(5, 1, "00", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(0, 35, "CREDIT 0", SGR.BOLD);

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FF1400"));
        Mockito.verify(graphics, Mockito.times(1)).putString(3, 7, "# -SHADOW    \"BLINKY\" ", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(10, 18, "#", SGR.BOLD);

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFC2FF"));
        Mockito.verify(graphics, Mockito.times(1)).putString(3, 9, "# -SPEEDY    \"PINKY\" ", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(12, 18, "#", SGR.BOLD);

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#00F9FF"));
        Mockito.verify(graphics, Mockito.times(1)).putString(3, 11, "# -BASHFUL   \"INKY\" ", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(14, 18, "#", SGR.BOLD);

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFC55B"));
        Mockito.verify(graphics, Mockito.times(1)).putString(3, 13, "# -POKEY     \"CLYDE\" ", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(16, 18, "#", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(9, 27, "*", SGR.BOLD);

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFF100"));
        Mockito.verify(graphics, Mockito.times(1)).putString(8, 18, ">", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(9, 26, ".", SGR.BOLD);

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFC2FF"));
        Mockito.verify(graphics, Mockito.times(1)).putString(3, 33, "@ 1980 MIDWAY MFG. CO.", SGR.BOLD);
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }

     */

}
