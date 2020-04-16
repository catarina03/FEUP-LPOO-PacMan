package Others;

import Elements.*;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
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

        for (Drawable element : game.getAllElements()) drawElement(element);

        screen.refresh();
    }

    private void drawMap() {
        int x;
        int y=3;

        for (Wall wall : game.getMap().getWalls()) {
            textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);
            textGraphics.putString(getX(), getY(), " ", SGR.BOLD);
        }
        for (EmptySpace emptySpace : emptySpaces) emptySpace.draw(textGraphics);
        for (Coin coin : coins) coin.draw(textGraphics);
        for (PowerPellet pp : powerPellets) pp.draw(textGraphics);
    }

    private void drawGameStats() {
        this.game.getGameStats().draw(screen.newTextGraphics());
    }

    private void drawElement(Element element) {
        if (element instanceof Ghost) drawCharacter(element.getPosition(), "H", "#FFFFFF");
        if (element instanceof Enemy) drawCharacter(element.getPosition(), "E", "#FF0000");
        if (element instanceof Wall) drawCharacter(element.getPosition(), "#", "#FFFFFF");
        if (element instanceof Coin) drawCharacter(element.getPosition(), "O", "#FFFF00");
    }

    private void drawCharacter(Position position, String character, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString(color));

        graphics.putString(position.getX(), position.getY(), character);
    }

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


}
