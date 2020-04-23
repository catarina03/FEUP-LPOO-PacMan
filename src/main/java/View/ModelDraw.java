package View;

import Model.Elements.*;
import Model.GameData;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class ModelDraw {
    private Screen screen;

    public ModelDraw(Screen screen) {
        this.screen = screen;
    }

    public void drawElement(MapComponent element) {
        if (element instanceof Ghost) drawGhost(element);
        if (element instanceof Cherry) drawCherry(element);
        if (element instanceof Wall) drawWall(element);
        if (element instanceof Coin) drawCoin(element);
        if (element instanceof EmptySpace) drawEmptySpace(element);
        if (element instanceof PowerPellet) drawPowerPellet(element);
    }

    public void drawPacMan(GameData gameData){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.ANSI.YELLOW);
        graphics.enableModifiers(SGR.BOLD);
        switch (gameData.getPacMan().getOrientation()){
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
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.enableModifiers(SGR.BOLD);
    }


    public void drawGameStats(GameData gameData) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.RED);
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.putString(9, 1, "SCORE", SGR.BOLD);
        textGraphics.putString(29, 1, "HI-SCORE", SGR.BOLD);
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.putString(13, 2, String.valueOf(gameData.getGameStats().getScore()), SGR.BOLD);
        textGraphics.putString(31, 2, "10000", SGR.BOLD);
        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW);
        textGraphics.putString(9, 34, "00000", SGR.BOLD);
        textGraphics.setForegroundColor(TextColor.ANSI.RED);
        textGraphics.putString(35, 34, "o", SGR.BOLD);
    }

    public void drawGhost(MapComponent element){
        TextGraphics textGraphics = screen.newTextGraphics();
        if (element instanceof Blinky){
            textGraphics.setForegroundColor(TextColor.ANSI.RED);
            textGraphics.setCharacter(element.getX(), element.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
        }
        if (element instanceof Clyde){
            textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFA500")); //Should be orange
            textGraphics.setCharacter(element.getX(), element.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
        }
        if (element instanceof Inky){
            textGraphics.setForegroundColor(TextColor.ANSI.CYAN);
            textGraphics.setCharacter(element.getX(), element.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
        }
        if (element instanceof Pinky){
            textGraphics.setForegroundColor(TextColor.ANSI.MAGENTA);
            textGraphics.setCharacter(element.getX(), element.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
        }
    }

    public void drawCherry(MapComponent element){
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.RED);
        textGraphics.setCharacter(element.getX(), element.getY(), Symbols.CLUB);
    }

    public void drawWall(MapComponent element){
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);
        textGraphics.putString(element.getX(), element.getY(), " ", SGR.BOLD);
    }

    public void drawCoin(MapComponent element){
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW);
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.setCharacter(element.getX(), element.getY(), Symbols.BULLET);
    }

    public void drawEmptySpace(MapComponent element){
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.putString(element.getX(), element.getY(), " ", SGR.BOLD);
    }

    public void drawPowerPellet(MapComponent element){
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFA500")); //Should be orange
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.setCharacter(element.getX(), element.getY(), Symbols.SPADES);
    }
}
