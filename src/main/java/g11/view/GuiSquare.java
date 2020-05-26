package g11.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.swing.*;
import g11.model.elements.*;
import g11.model.GameData;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static g11.view.MoveENUM.*;

public class GuiSquare {
    private Terminal terminal;
    private Screen screen;
    private ModelDrawSquare modelDraw;

    public GuiSquare() {
        try {
            File fontfile = new File(getClass().getClassLoader().getResource("square.ttf").getFile());
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontfile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            Font loadedFont = font.deriveFont(Font.PLAIN, 25);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);

            DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
            TerminalSize terminalsize = new TerminalSize(28,36);

            defaultTerminalFactory.setForceAWTOverSwing(true);
            defaultTerminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
            defaultTerminalFactory.setInitialTerminalSize(terminalsize);
            Terminal defterminal = defaultTerminalFactory.createTerminal();

            terminal = defterminal;
            screen = new TerminalScreen(defterminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (IOException | FontFormatException | IllegalArgumentException e) {
            //Handle exception
            e.printStackTrace();
        }


        modelDraw = new ModelDrawSquare(screen);
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public GuiSquare(int no) {
        // TODO modificar esta função
        try {
            TerminalSize terminalsize = new TerminalSize(50,36);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalsize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        modelDraw = new ModelDrawSquare(screen);
    }

    public void setModelDraw(ModelDrawSquare modelDraw) {
        this.modelDraw = modelDraw;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void close() throws Throwable {
        if (screen != null){
            screen.close();
        }
    }

    public KeyStroke getKeyStroke() throws IOException {
        return screen.readInput();
    }

    public MoveENUM getMove() throws IOException {
        // Ler Esc para sair de ciclo
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke != null) {
            switch (keyStroke.getKeyType()) {
                case ArrowUp:
                    return UP;
                case ArrowDown:
                    return DOWN;
                case ArrowLeft:
                    return LEFT;
                case ArrowRight:
                    return RIGHT;
                case Escape:
                case EOF:
                    return ESC;
                default:
                    return null;
            }
        }
        return null;
    }

    public void draw(GameData gameData) throws IOException {
        screen.clear();

        for (MapComponent element : gameData.getMap().getMapComponents()) modelDraw.drawElement(element);

        modelDraw.drawGhost(gameData);
        modelDraw.drawPacMan(gameData);
        modelDraw.drawGameStats(gameData);

        screen.refresh();
    }

    public void readyScreen() throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFF100"));
        graphics.putString(11, 20, "READY!", SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#00F9FF"));
        graphics.putString(9, 14, "PLAYER ONE", SGR.BOLD);
        screen.refresh();
    }

    public void presentationScreen() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(3, 0, "1UP   HIGH SCORE   2UP", SGR.BOLD);
        graphics.putString(5, 5, "CHARACTER / NICKNAME", SGR.BOLD);
        graphics.putString(11, 26, "10 PTS", SGR.BOLD);
        graphics.putString(11, 27, "50 PTS", SGR.BOLD);
        graphics.putString(5, 1, "00", SGR.BOLD);
        graphics.putString(0, 35, "CREDIT 0", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FF1400"));
        graphics.putString(3, 7, "# -SHADOW    \"BLINKY\" ", SGR.BOLD);
        graphics.putString(10, 18, "#", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC2FF"));
        graphics.putString(3, 33, "@ 1980 MIDWAY MFG. CO.", SGR.BOLD);
        graphics.putString(3, 9, "# -SPEEDY    \"PINKY\" ", SGR.BOLD);
        graphics.putString(12, 18, "#", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#00F9FF"));
        graphics.putString(3, 11, "# -BASHFUL   \"INKY\" ", SGR.BOLD);
        graphics.putString(14, 18, "#", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC55B"));
        graphics.putString(3, 13, "# -POKEY     \"CLYDE\" ", SGR.BOLD);
        graphics.putString(16, 18, "#", SGR.BOLD);
        graphics.putString(9, 27, "*", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFF100"));
        graphics.putString(8, 18, ">", SGR.BOLD);
        graphics.putString(9, 26, ".", SGR.BOLD);
        screen.refresh();
    }

    public void inicialScreen() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(3, 0, "1UP   HIGH SCORE   2UP", SGR.BOLD);
        graphics.putString(5, 1, "00", SGR.BOLD);
        graphics.putString(3, 35, "CREDIT 1", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC55B"));
        graphics.putString(5, 15, "PUSH START BUTTON", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#00F9FF"));
        graphics.putString(7, 19, "1 PLAYER ONLY", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFCEC0"));
        graphics.putString(1, 23, "BONUS PAC-MAN FOR 1000 PTS", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC2FF"));
        graphics.putString(3, 27, "@ 1980 MIDWAY MFG. CO.", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FF1400"));
        graphics.putString(20, 35, "O", SGR.BOLD);

        screen.refresh();
    }

    public void endScreen(Boolean winner, GameData gameData) throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(3, 0, "1UP   HIGH SCORE   2UP", SGR.BOLD);
        graphics.putString(5, 1, "00", SGR.BOLD);
        graphics.putString(0, 35, "CREDIT 2", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC2FF"));
        graphics.putString(3, 33, "@ 1980 MIDWAY MFG. CO.", SGR.BOLD);

        if (winner) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFC55B"));
            graphics.putString(6, 10, "CONGRATULATIONS", SGR.BOLD);
            graphics.putString(10, 11, "YOU WON", SGR.BOLD);
        } else {
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFC55B"));
            graphics.putString(9, 10, "GAME OVER", SGR.BOLD);
            graphics.putString(3, 11, "BETTER LUCK NEXT TIME", SGR.BOLD);
        }

        screen.refresh();
    }
}
