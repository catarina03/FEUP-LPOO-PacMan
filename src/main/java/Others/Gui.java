package Others;

import Elements.*;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Gui {
    private Game game;
    private Screen screen;

    public Gui(Game game) throws IOException {
        try {
            TerminalSize terminalsize = new TerminalSize(50,36);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalsize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary

            this.game = game;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void draw() throws IOException {
        screen.clear();

        drawPacMan();
        drawGameStats();

        for (Drawable element : game.getMap().getMapComponents()) drawElement(element);
        for (Ghost ghost : game.)

        screen.refresh();
    }


    private void drawPacMan(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.ANSI.YELLOW);
        graphics.enableModifiers(SGR.BOLD);
        switch (this.game.getPacMan().getDirection()){
            case 'N':
                graphics.setCharacter(this.game.getPacMan().getX(), this.game.getPacMan().getY(), Symbols.ARROW_UP);
                break;
            case 'E':
                graphics.setCharacter(this.game.getPacMan().getX(), this.game.getPacMan().getY(), Symbols.ARROW_RIGHT);
                break;
            case 'S':
                graphics.setCharacter(this.game.getPacMan().getX(), this.game.getPacMan().getY(), Symbols.ARROW_DOWN);
                break;
            case 'W':
                graphics.setCharacter(this.game.getPacMan().getX(), this.game.getPacMan().getY(), Symbols.ARROW_LEFT);
                break;
        }
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.enableModifiers(SGR.BOLD);
    }


    private void drawGameStats() {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.RED);
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.putString(9, 1, "SCORE", SGR.BOLD);
        textGraphics.putString(29, 1, "HI-SCORE", SGR.BOLD);
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.putString(13, 2, String.valueOf(game.getGameStats().getScore()), SGR.BOLD);
        textGraphics.putString(31, 2, "10000", SGR.BOLD);
        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW);
        textGraphics.putString(9, 34, "00000", SGR.BOLD);
        textGraphics.setForegroundColor(TextColor.ANSI.RED);
        textGraphics.putString(35, 34, "o", SGR.BOLD);
    }

    private void drawElement(MapComponent element) {
        if (element instanceof Ghost) drawGhost(element);
        if (element instanceof Cherry) drawCharacter(element.getPosition(), "E", "#FF0000");
        if (element instanceof Wall) drawCharacter(element.getPosition(), "#", "#FFFFFF");
        if (element instanceof Coin) drawCharacter(element.getPosition(), "O", "#FFFF00");
        if (element instanceof EmptySpace) drawCharacter(element.getPosition(), "O", "#FFFF00");
        if (element instanceof PowerPellet) drawCharacter(element.getPosition(), "O", "#FFFF00");
    }

    private void drawGhost(MapComponent element){
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








    private void drawCharacter(Position position, static char character, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString(color));

        graphics.putString(position.getX(), position.getY(), character);
    }

    /*
    public Command getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        if (input.getKeyType() == KeyType.EOF) return new QuitCommand(arena, screen);
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == 'q') return new QuitCommand(arena, screen);
        if (input.getKeyType() == KeyType.ArrowDown) return new MoveHeroDownCommand(arena);
        if (input.getKeyType() == KeyType.ArrowUp) return new MoveHeroUpCommand(arena);
        if (input.getKeyType() == KeyType.ArrowLeft) return new MoveHeroLeftCommand(arena);
        if (input.getKeyType() == KeyType.ArrowRight) return new MoveHeroRightCommand(arena);

        return new DoNothingCommand();
    }
    */
}
