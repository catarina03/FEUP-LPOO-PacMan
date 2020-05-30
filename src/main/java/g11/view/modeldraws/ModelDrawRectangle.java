package g11.view.modeldraws;

import g11.model.GhostStateEnumeration;
import g11.model.elements.*;
import g11.model.GameData;
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
import g11.view.ModelDraw;

public class ModelDrawRectangle implements ModelDraw {
    private TextGraphics graphics;

    public ModelDrawRectangle(Screen screen) {
        this.graphics = screen.newTextGraphics();
    }

    public void setGraphics(TextGraphics graphics) {
        this.graphics = graphics;
    }

    public void drawElement(MapComponent element) {
        if (element instanceof Cherry) drawCherry(element);
        if (element instanceof Wall) drawWall(element);
        if (element instanceof Coin) drawCoin(element);
        if (element instanceof EmptySpace) drawEmptySpace(element);
        if (element instanceof PowerPellet) drawPowerPellet(element);
        if (element instanceof Gate) drawGate(element);
    }

    public void drawGate(MapComponent element) {
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(element.getX(), element.getY(), "-", SGR.BOLD);
    }

    public void drawPacMan(GameData gameData) {
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.setForegroundColor(TextColor.ANSI.YELLOW);
        graphics.enableModifiers(SGR.BOLD);
        switch (gameData.getPacMan().getOrientationEnumeration()) {
            case UP:
                graphics.setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), Symbols.ARROW_UP);
                break;
            case RIGHT:
                graphics.setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), Symbols.ARROW_RIGHT);
                break;
            case DOWN:
                graphics.setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), Symbols.ARROW_DOWN);
                break;
            case LEFT:
                graphics.setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), Symbols.ARROW_LEFT);
                break;
        }
    }


    public void drawGameStats(GameData gameData) {
        graphics.setForegroundColor(TextColor.ANSI.RED);
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.putString(9, 1, "SCORE", SGR.BOLD);
        graphics.putString(29, 1, "HI-SCORE", SGR.BOLD);
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(13 - String.valueOf(gameData.getGameStats().getScore()).length(), 2, String.valueOf(gameData.getGameStats().getScore()), SGR.BOLD);
        graphics.putString(31, 2, String.valueOf(gameData.getGameStats().getHighScore()), SGR.BOLD);
        graphics.setForegroundColor(TextColor.ANSI.YELLOW);
        graphics.putString(9, 34, "00000", SGR.BOLD);
        graphics.setForegroundColor(TextColor.ANSI.RED);
        graphics.putString(35, 34, "o", SGR.BOLD);
    }

    public void drawGhost(GameData gameData) {
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        for (Ghost element : gameData.getGhosts()) {
            if (element.getState() == GhostStateEnumeration.EATEN || element.getState() == GhostStateEnumeration.ENTERINGHOUSE) {
                graphics.setForegroundColor(TextColor.ANSI.WHITE);
                graphics.setCharacter(element.getX(), element.getY(), '\"');
            } else {
                if (element instanceof Blinky) {
                    if (element.getState() == GhostStateEnumeration.FRIGHTENED)
                        graphics.setForegroundColor(TextColor.ANSI.BLUE);
                    else graphics.setForegroundColor(TextColor.Factory.fromString("#FF1400"));
                    graphics.setCharacter(element.getX(), element.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
                }
                if (element instanceof Clyde) {
                    if (element.getState() == GhostStateEnumeration.FRIGHTENED)
                        graphics.setForegroundColor(TextColor.ANSI.BLUE);
                    else graphics.setForegroundColor(TextColor.Factory.fromString("#FFC55B"));
                    graphics.setCharacter(element.getX(), element.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
                }
                if (element instanceof Inky) {
                    if (element.getState() == GhostStateEnumeration.FRIGHTENED)
                        graphics.setForegroundColor(TextColor.ANSI.BLUE);
                    else graphics.setForegroundColor(TextColor.Factory.fromString("#00F9FF"));
                    graphics.setCharacter(element.getX(), element.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
                }
                if (element instanceof Pinky) {
                    if (element.getState() == GhostStateEnumeration.FRIGHTENED)
                        graphics.setForegroundColor(TextColor.ANSI.BLUE);
                    else graphics.setForegroundColor(TextColor.Factory.fromString("#FFC2FF"));
                    graphics.setCharacter(element.getX(), element.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
                }
            }
        }
    }

    public void drawCherry(MapComponent element) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF1400"));
        graphics.setCharacter(element.getX(), element.getY(), Symbols.CLUB);
    }

    public void drawWall(MapComponent element) {
        graphics.setBackgroundColor(TextColor.ANSI.BLUE);
        graphics.putString(element.getX(), element.getY(), " ", SGR.BOLD);
    }

    public void drawCoin(MapComponent element) {
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFF100"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.setCharacter(element.getX(), element.getY(), Symbols.BULLET);
    }

    public void drawEmptySpace(MapComponent element) {
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.putString(element.getX(), element.getY(), " ", SGR.BOLD);
    }

    public void drawPowerPellet(MapComponent element) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.enableModifiers(SGR.BOLD);
        graphics.setCharacter(element.getX(), element.getY(), Symbols.SPADES);
    }
}