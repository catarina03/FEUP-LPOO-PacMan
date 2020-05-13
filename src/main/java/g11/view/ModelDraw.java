package g11.view;

import g11.model.Elements.*;
import g11.model.GameData;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class ModelDraw {
    private Screen screen;
    private TextGraphics graphics;

    public ModelDraw(Screen screen) {
        this.screen = screen;
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

    private void drawGate(MapComponent element) {
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(element.getX(), element.getY(), "-", SGR.BOLD);
    }

    public void drawPacMan(GameData gameData){
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
        graphics.setForegroundColor(TextColor.ANSI.RED);
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.putString(9, 1, "SCORE", SGR.BOLD);
        graphics.putString(29, 1, "HI-SCORE", SGR.BOLD);
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(13, 2, String.valueOf(gameData.getGameStats().getScore()), SGR.BOLD);
        graphics.putString(31, 2, "10000", SGR.BOLD);
        graphics.setForegroundColor(TextColor.ANSI.YELLOW);
        graphics.putString(9, 34, "00000", SGR.BOLD);
        graphics.setForegroundColor(TextColor.ANSI.RED);
        graphics.putString(35, 34, "o", SGR.BOLD);
    }

    public void drawGhost(GameData gameData){

        for (Ghost element : gameData.getGhosts()){
            if (element instanceof Blinky){
                graphics.setForegroundColor(TextColor.ANSI.RED);
                graphics.setCharacter(element.getX(), element.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
            }
            if (element instanceof Clyde){
                graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
                graphics.setCharacter(element.getX(), element.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
            }
            if (element instanceof Inky){
                graphics.setForegroundColor(TextColor.ANSI.CYAN);
                graphics.setCharacter(element.getX(), element.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
            }
            if (element instanceof Pinky){
                graphics.setForegroundColor(TextColor.ANSI.MAGENTA);
                graphics.setCharacter(element.getX(), element.getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
            }
        }
    }

    public void drawCherry(MapComponent element){
        graphics.setForegroundColor(TextColor.ANSI.RED);
        graphics.setCharacter(element.getX(), element.getY(), Symbols.CLUB);
    }

    public void drawWall(MapComponent element){
        graphics.setBackgroundColor(TextColor.ANSI.BLUE);
        graphics.putString(element.getX(), element.getY(), " ", SGR.BOLD);
    }

    public void drawCoin(MapComponent element){
        graphics.setForegroundColor(TextColor.ANSI.YELLOW);
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.enableModifiers(SGR.BOLD);
        graphics.setCharacter(element.getX(), element.getY(), Symbols.BULLET);
    }

    public void drawEmptySpace(MapComponent element){
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.putString(element.getX(), element.getY(), " ", SGR.BOLD);
    }

    public void drawPowerPellet(MapComponent element){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.enableModifiers(SGR.BOLD);
        graphics.setCharacter(element.getX(), element.getY(), Symbols.SPADES);
    }
}
