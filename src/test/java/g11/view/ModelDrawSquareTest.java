package g11.view;

import g11.model.elements.*;
import g11.model.GameData;
import g11.model.GameStats;
import g11.model.Orientation;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Test;
import org.mockito.Mockito;

public class ModelDrawSquareTest {

    @Test
    public void drawCherry() {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawSquare modelDraw = new ModelDrawSquare(screen);
        modelDraw.setGraphics(graphics);

        MapComponent cherry = Mockito.mock(Cherry.class);
        Mockito.when(cherry.getX()).thenReturn(10);
        Mockito.when(cherry.getY()).thenReturn(10);

        modelDraw.drawElement(cherry);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.RED);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(cherry.getX(), cherry.getY(), '$');
    }

    @Test
    public void drawWall() {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawSquare modelDraw = new ModelDrawSquare(screen);
        modelDraw.setGraphics(graphics);

        MapComponent wall = Mockito.mock(Wall.class);
        Mockito.when(wall.getX()).thenReturn(20);
        Mockito.when(wall.getY()).thenReturn(20);

        modelDraw.drawElement(wall);
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.BLUE);
        Mockito.verify(graphics, Mockito.times(1)).putString(wall.getX(), wall.getY(), " ", SGR.BOLD);
    }

    @Test
    public void drawCoin() {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawSquare modelDraw = new ModelDrawSquare(screen);
        modelDraw.setGraphics(graphics);

        MapComponent coin = Mockito.mock(Coin.class);
        Mockito.when(coin.getX()).thenReturn(30);
        Mockito.when(coin.getY()).thenReturn(30);

        modelDraw.drawElement(coin);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.YELLOW);
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(graphics, Mockito.times(1)).enableModifiers(SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(coin.getX(), coin.getY(), '.');
    }

    @Test
    public void drawEmptySpace() {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawSquare modelDraw = new ModelDrawSquare(screen);
        modelDraw.setGraphics(graphics);

        MapComponent emptySapce = Mockito.mock(EmptySpace.class);
        Mockito.when(emptySapce.getX()).thenReturn(40);
        Mockito.when(emptySapce.getY()).thenReturn(40);

        modelDraw.drawElement(emptySapce);
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(graphics, Mockito.times(1)).putString(emptySapce.getX(), emptySapce.getY(), " ", SGR.BOLD);
    }

    @Test
    public void drawPowerPellet() {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawSquare modelDraw = new ModelDrawSquare(screen);
        modelDraw.setGraphics(graphics);

        MapComponent powerPellet = Mockito.mock(PowerPellet.class);
        Mockito.when(powerPellet.getX()).thenReturn(50);
        Mockito.when(powerPellet.getY()).thenReturn(50);

        modelDraw.drawElement(powerPellet);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(graphics, Mockito.times(1)).enableModifiers(SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(powerPellet.getX(), powerPellet.getY(), '*');
    }

    @Test
    public void drawGhosts(){
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawSquare modelDraw = new ModelDrawSquare(screen);
        modelDraw.setGraphics(graphics);

        MapComponent blinky = Mockito.mock(Blinky.class);
        Mockito.when(blinky.getX()).thenReturn(60);
        Mockito.when(blinky.getY()).thenReturn(60);

        modelDraw.drawElement(blinky);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.RED);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(blinky.getX(), blinky.getY(), '#');

        MapComponent inky = Mockito.mock(Inky.class);
        Mockito.when(inky.getX()).thenReturn(70);
        Mockito.when(inky.getY()).thenReturn(70);

        modelDraw.drawElement(inky);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.CYAN);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(inky.getX(), inky.getY(), '#');

        MapComponent clyde = Mockito.mock(Clyde.class);
        Mockito.when(clyde.getX()).thenReturn(80);
        Mockito.when(clyde.getY()).thenReturn(80);

        modelDraw.drawElement(clyde);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(clyde.getX(), clyde.getY(), '#');

        MapComponent pinky = Mockito.mock(Pinky.class);
        Mockito.when(pinky.getX()).thenReturn(90);
        Mockito.when(pinky.getY()).thenReturn(90);

        modelDraw.drawElement(pinky);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.MAGENTA);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(pinky.getX(), pinky.getY(), '#');
    }

    @Test
    public void drawGameStats(){
        GameData gameData = Mockito.mock(GameData.class);
        GameStats gameStats = Mockito.mock(GameStats.class);
        Mockito.when(gameStats.getScore()).thenReturn(100);
        Mockito.when(gameData.getGameStats()).thenReturn(gameStats);

        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawSquare modelDraw = new ModelDrawSquare(screen);
        modelDraw.setGraphics(graphics);

        modelDraw.drawGameStats(gameData);
        Mockito.verify(graphics, Mockito.times(2)).setForegroundColor(TextColor.ANSI.RED);
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(graphics, Mockito.times(1)).putString(3, 1, "SCORE", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(15, 1, "HI-SCORE", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.WHITE);
        Mockito.verify(graphics, Mockito.times(1)).putString(7, 2, String.valueOf(gameData.getGameStats().getScore()), SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(17, 2, "10000", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.YELLOW);
        Mockito.verify(graphics, Mockito.times(1)).putString(3, 34, "00000", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(20, 34, "o", SGR.BOLD);
    }

    @Test
    public void drawPacman(){
        PacMan pacman = Mockito.mock(PacMan.class);
        Mockito.when(pacman.getX()).thenReturn(10);
        Mockito.when(pacman.getY()).thenReturn(10);
        GameData gameData = Mockito.mock(GameData.class);
        Mockito.when(gameData.getPacMan()).thenReturn(pacman);

        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawSquare modelDraw = new ModelDrawSquare(screen);
        modelDraw.setGraphics(graphics);

        Mockito.when(pacman.getOrientation()).thenReturn(Orientation.UP);
        modelDraw.drawPacMan(gameData);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.YELLOW);
        Mockito.verify(graphics, Mockito.times(2)).enableModifiers(SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), 'v');
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.WHITE);

        Mockito.when(pacman.getOrientation()).thenReturn(Orientation.RIGHT);
        modelDraw.drawPacMan(gameData);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), '<');

        Mockito.when(pacman.getOrientation()).thenReturn(Orientation.DOWN);
        modelDraw.drawPacMan(gameData);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), '^');

        Mockito.when(pacman.getOrientation()).thenReturn(Orientation.LEFT);
        modelDraw.drawPacMan(gameData);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), '>');
    }
}
