package g11.view;

import g11.model.GhostStateENUM;
import g11.model.elements.*;
import g11.model.GameData;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class ModelDrawSquare {
    private Screen screen;
    private TextGraphics graphics;

    public ModelDrawSquare(Screen screen) {
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
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFF100"));
        graphics.enableModifiers(SGR.BOLD);
        switch (gameData.getPacMan().getOrientationENUM()) {
            case UP:
                graphics.setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), 'v');
                break;
            case RIGHT:
                graphics.setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), '<');
                break;
            case DOWN:
                graphics.setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), '^');
                break;
            case LEFT:
                graphics.setCharacter(gameData.getPacMan().getX(), gameData.getPacMan().getY(), '>');
                break;
        }
        graphics.enableModifiers(SGR.BOLD);
    }


    public void drawGameStats(GameData gameData) {
        graphics.setForegroundColor(TextColor.ANSI.RED);
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.putString(3, 1, "SCORE", SGR.BOLD);
        graphics.putString(15, 1, "HI-SCORE", SGR.BOLD);
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(7, 2, String.valueOf(gameData.getGameStats().getScore()), SGR.BOLD);
        graphics.putString(17, 2, "10000", SGR.BOLD);
        graphics.setForegroundColor(TextColor.ANSI.YELLOW);
        graphics.putString(3, 34, "00000", SGR.BOLD);
        graphics.setForegroundColor(TextColor.ANSI.RED);
        graphics.putString(20, 34, "o", SGR.BOLD);

        // Descomentar para ver Targets em ação
        /*graphics.setForegroundColor(TextColor.Factory.fromString("#FF1400"));
        graphics.putString(gameData.getGhosts().get(0).getTarget().getX(), gameData.getGhosts().get(0).getTarget().getY(), "X", SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#00F9FF"));
        graphics.putString(gameData.getGhosts().get(1).getTarget().getX(), gameData.getGhosts().get(1).getTarget().getY(), "X", SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC2FF"));
        graphics.putString(gameData.getGhosts().get(2).getTarget().getX(), gameData.getGhosts().get(2).getTarget().getY(), "X", SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC55B"));
        graphics.putString(gameData.getGhosts().get(3).getTarget().getX(), gameData.getGhosts().get(3).getTarget().getY(), "X", SGR.BOLD);*/
    }

    public void drawGhost(GameData gameData){
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        for (Ghost element : gameData.getGhosts()) {
            if (element.getState() == GhostStateENUM.EATEN || element.getState() == GhostStateENUM.ENTERINGHOUSE) {
                graphics.setForegroundColor(TextColor.ANSI.WHITE);
                graphics.setCharacter(element.getX(), element.getY(), '\"');
            } else {
                if (element instanceof Blinky) {
                    if (element.getState() == GhostStateENUM.FRIGHTENED)
                        graphics.setForegroundColor(TextColor.ANSI.BLUE);
                    else graphics.setForegroundColor(TextColor.Factory.fromString("#FF1400"));
                    graphics.setCharacter(element.getX(), element.getY(), '#');
                }
                if (element instanceof Clyde) {
                    if (element.getState() == GhostStateENUM.FRIGHTENED)
                        graphics.setForegroundColor(TextColor.ANSI.BLUE);
                    else graphics.setForegroundColor(TextColor.Factory.fromString("#FFC55B"));
                    graphics.setCharacter(element.getX(), element.getY(), '#');
                }
                if (element instanceof Inky) {
                    if (element.getState() == GhostStateENUM.FRIGHTENED)
                        graphics.setForegroundColor(TextColor.ANSI.BLUE);
                    else graphics.setForegroundColor(TextColor.Factory.fromString("#00F9FF"));
                    graphics.setCharacter(element.getX(), element.getY(), '#');
                }
                if (element instanceof Pinky) {
                    if (element.getState() == GhostStateENUM.FRIGHTENED)
                        graphics.setForegroundColor(TextColor.ANSI.BLUE);
                    else graphics.setForegroundColor(TextColor.Factory.fromString("#FFC2FF"));
                    graphics.setCharacter(element.getX(), element.getY(), '#');
                }
            }
        }
    }

    public void drawCherry(MapComponent element){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF1400"));
        graphics.setCharacter(element.getX(), element.getY(), '$');
    }

    public void drawWall(MapComponent element){
        graphics.setBackgroundColor(TextColor.ANSI.BLUE);
        graphics.putString(element.getX(), element.getY(), " ", SGR.BOLD);
    }

    public void drawCoin(MapComponent element){
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFF100"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.setCharacter(element.getX(), element.getY(), '.');
    }

    public void drawEmptySpace(MapComponent element){
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.putString(element.getX(), element.getY(), " ", SGR.BOLD);
    }

    public void drawPowerPellet(MapComponent element){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.enableModifiers(SGR.BOLD);
        graphics.setCharacter(element.getX(), element.getY(), '*');
    }
}
