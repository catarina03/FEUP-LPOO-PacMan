package g11.view;

import g11.model.GhostStateEnumeration;
import g11.model.elements.*;
import g11.model.GameData;
import g11.model.GameStats;
import g11.model.OrientationEnumeration;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import g11.model.elements.ghosts.Blinky;
import g11.model.elements.ghosts.Clyde;
import g11.model.elements.ghosts.Inky;
import g11.model.elements.ghosts.Pinky;
import g11.model.elements.map.*;
import g11.view.modeldraws.ModelDrawRectangle;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ModelDrawRectangleTest {

    @Test
    public void drawCherry() {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawRectangle modelDrawRectangle = new ModelDrawRectangle(screen);
        modelDrawRectangle.setGraphics(graphics);

        MapComponent cherry = Mockito.mock(Cherry.class);
        Mockito.when(cherry.getX()).thenReturn(10);
        Mockito.when(cherry.getY()).thenReturn(10);

        modelDrawRectangle.drawElement(cherry);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FF1400"));
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(cherry.getX(), cherry.getY(), Symbols.CLUB);
    }

    @Test
    public void drawWall() {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawRectangle modelDrawRectangle = new ModelDrawRectangle(screen);
        modelDrawRectangle.setGraphics(graphics);

        MapComponent wall = Mockito.mock(Wall.class);
        Mockito.when(wall.getX()).thenReturn(20);
        Mockito.when(wall.getY()).thenReturn(20);

        modelDrawRectangle.drawElement(wall);
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.BLUE);
        Mockito.verify(graphics, Mockito.times(1)).putString(wall.getX(), wall.getY(), " ", SGR.BOLD);
    }

    @Test
    public void drawCoin() {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawRectangle modelDrawRectangle = new ModelDrawRectangle(screen);
        modelDrawRectangle.setGraphics(graphics);

        MapComponent coin = Mockito.mock(Coin.class);
        Mockito.when(coin.getX()).thenReturn(30);
        Mockito.when(coin.getY()).thenReturn(30);

        modelDrawRectangle.drawElement(coin);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFF100"));
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(graphics, Mockito.times(1)).enableModifiers(SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(coin.getX(), coin.getY(), Symbols.BULLET);
    }

    @Test
    public void drawGate(){
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawRectangle modelDrawRectangle = new ModelDrawRectangle(screen);
        modelDrawRectangle.setGraphics(graphics);

        MapComponent gate = Mockito.mock(Gate.class);
        Mockito.when(gate.getX()).thenReturn(30);
        Mockito.when(gate.getY()).thenReturn(30);

        modelDrawRectangle.drawElement(gate);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.WHITE);
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(graphics, Mockito.times(1)).putString(gate.getX(), gate.getY(), "-", SGR.BOLD);
    }

    @Test
    public void drawEmptySpace() {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawRectangle modelDrawRectangle = new ModelDrawRectangle(screen);
        modelDrawRectangle.setGraphics(graphics);

        MapComponent emptySapce = Mockito.mock(EmptySpace.class);
        Mockito.when(emptySapce.getX()).thenReturn(40);
        Mockito.when(emptySapce.getY()).thenReturn(40);

        modelDrawRectangle.drawElement(emptySapce);
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(graphics, Mockito.times(1)).putString(emptySapce.getX(), emptySapce.getY(), " ", SGR.BOLD);
    }

    @Test
    public void drawPowerPellet() {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawRectangle modelDrawRectangle = new ModelDrawRectangle(screen);
        modelDrawRectangle.setGraphics(graphics);

        MapComponent powerPellet = Mockito.mock(PowerPellet.class);
        Mockito.when(powerPellet.getX()).thenReturn(50);
        Mockito.when(powerPellet.getY()).thenReturn(50);

        modelDrawRectangle.drawElement(powerPellet);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(graphics, Mockito.times(1)).enableModifiers(SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(powerPellet.getX(), powerPellet.getY(), Symbols.SPADES);
    }

    @Test
    public void drawGhosts(){
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawRectangle modelDrawRectangle = new ModelDrawRectangle(screen);
        GameData gameData = Mockito.mock(GameData.class);
        modelDrawRectangle.setGraphics(graphics);

        Blinky blinky = Mockito.mock(Blinky.class);
        Mockito.when(blinky.getX()).thenReturn(60);
        Mockito.when(blinky.getY()).thenReturn(60);
        Mockito.when(blinky.getState()).thenReturn(GhostStateEnumeration.CHASE);

        Inky inky = Mockito.mock(Inky.class);
        Mockito.when(inky.getX()).thenReturn(70);
        Mockito.when(inky.getY()).thenReturn(70);
        Mockito.when(inky.getState()).thenReturn(GhostStateEnumeration.CHASE);

        Clyde clyde = Mockito.mock(Clyde.class);
        Mockito.when(clyde.getX()).thenReturn(80);
        Mockito.when(clyde.getY()).thenReturn(80);
        Mockito.when(clyde.getState()).thenReturn(GhostStateEnumeration.CHASE);

        Pinky pinky = Mockito.mock(Pinky.class);
        Mockito.when(pinky.getX()).thenReturn(90);
        Mockito.when(pinky.getY()).thenReturn(90);
        Mockito.when(pinky.getState()).thenReturn(GhostStateEnumeration.CHASE);

        ArrayList<Ghost> ghosts = new ArrayList<>();
        ghosts.add(blinky);
        ghosts.add(inky);
        ghosts.add(clyde);
        ghosts.add(pinky);

        Mockito.when(gameData.getGhosts()).thenReturn(ghosts);

        modelDrawRectangle.drawGhost(gameData);

        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FF1400"));
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#00F9FF"));
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFC55B"));
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFC2FF"));
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(blinky.getX(), blinky.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(inky.getX(), inky.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(clyde.getX(), clyde.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(pinky.getX(), pinky.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);

        Mockito.when(blinky.getState()).thenReturn(GhostStateEnumeration.FRIGHTENED);
        Mockito.when(inky.getState()).thenReturn(GhostStateEnumeration.FRIGHTENED);
        Mockito.when(clyde.getState()).thenReturn(GhostStateEnumeration.FRIGHTENED);
        Mockito.when(pinky.getState()).thenReturn(GhostStateEnumeration.FRIGHTENED);

        modelDrawRectangle.drawGhost(gameData);
        Mockito.verify(graphics, Mockito.times(4)).setForegroundColor(TextColor.ANSI.BLUE);

        Mockito.when(blinky.getState()).thenReturn(GhostStateEnumeration.EATEN);
        Mockito.when(inky.getState()).thenReturn(GhostStateEnumeration.EATEN);
        Mockito.when(clyde.getState()).thenReturn(GhostStateEnumeration.EATEN);
        Mockito.when(pinky.getState()).thenReturn(GhostStateEnumeration.EATEN);

        modelDrawRectangle.drawGhost(gameData);
        Mockito.verify(graphics, Mockito.times(4)).setForegroundColor(TextColor.ANSI.WHITE);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(blinky.getX(), blinky.getY(), '\"');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(inky.getX(), inky.getY(), '\"');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(clyde.getX(), clyde.getY(), '\"');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(pinky.getX(), pinky.getY(), '\"');
    }

    @Test
    public void drawGameStats() {
        GameData gameData = Mockito.mock(GameData.class);
        GameStats gameStats = Mockito.mock(GameStats.class);
        Mockito.when(gameStats.getScore()).thenReturn(100);
        Mockito.when(gameData.getGameStats()).thenReturn(gameStats);

        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawRectangle modelDrawRectangle = new ModelDrawRectangle(screen);
        modelDrawRectangle.setGraphics(graphics);

        modelDrawRectangle.drawGameStats(gameData);
        Mockito.verify(graphics, Mockito.times(2)).setForegroundColor(TextColor.ANSI.RED);
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(graphics, Mockito.times(1)).putString(9, 1, "SCORE", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(29, 1, "HI-SCORE", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.WHITE);
        Mockito.verify(graphics, Mockito.times(1)).putString(13 - String.valueOf(gameData.getGameStats().getScore()).length(), 2, String.valueOf(gameData.getGameStats().getScore()), SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(31, 2, String.valueOf(gameData.getGameStats().getHighScore()), SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.YELLOW);
        Mockito.verify(graphics, Mockito.times(1)).putString(9, 34, "00000", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(35, 34, "o", SGR.BOLD);
    }

    @Test
    public void drawPacman() {
        PacMan pacman = Mockito.mock(PacMan.class);
        Mockito.when(pacman.getX()).thenReturn(10);
        Mockito.when(pacman.getY()).thenReturn(10);
        GameData gameData = Mockito.mock(GameData.class);
        Mockito.when(gameData.getPacMan()).thenReturn(pacman);

        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ModelDrawRectangle modelDraw = new ModelDrawRectangle(screen);
        modelDraw.setGraphics(graphics);

        Mockito.when(pacman.getOrientationEnumeration()).thenReturn(OrientationEnumeration.UP);
        modelDraw.drawPacMan(gameData);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), Symbols.ARROW_UP);

        Mockito.when(pacman.getOrientationEnumeration()).thenReturn(OrientationEnumeration.RIGHT);
        modelDraw.drawPacMan(gameData);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), Symbols.ARROW_RIGHT);

        Mockito.when(pacman.getOrientationEnumeration()).thenReturn(OrientationEnumeration.DOWN);
        modelDraw.drawPacMan(gameData);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), Symbols.ARROW_DOWN);

        Mockito.when(pacman.getOrientationEnumeration()).thenReturn(OrientationEnumeration.LEFT);
        modelDraw.drawPacMan(gameData);
        Mockito.verify(graphics, Mockito.times(4)).setForegroundColor(TextColor.Factory.fromString("#FFF100"));
        Mockito.verify(graphics, Mockito.times(4)).enableModifiers(SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), Symbols.ARROW_LEFT);
    }
}