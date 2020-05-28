package g11.view.guis;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.swing.*;
import g11.model.GameStats;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import g11.view.Gui;
import g11.view.modeldraws.ModelDrawSquare;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GuiSquare extends Gui {
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
            TerminalSize terminalsize = new TerminalSize(28, 36);

            defaultTerminalFactory.setForceAWTOverSwing(true);
            defaultTerminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
            defaultTerminalFactory.setInitialTerminalSize(terminalsize);
            Terminal defterminal = defaultTerminalFactory.createTerminal();

            setTerminal(defterminal);
            setScreen(new TerminalScreen(defterminal));

            startScreen();
        } catch (IOException | FontFormatException | IllegalArgumentException e) {
            //Handle exception
            e.printStackTrace();
        }
        setModelDraw(new ModelDrawSquare(getScreen()));
    }

    public GuiSquare(int no) {
        // TODO modificar esta função
        try {
            TerminalSize terminalsize = new TerminalSize(50, 36);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalsize);
            Terminal terminal = terminalFactory.createTerminal();

            setTerminal(terminal);
            setScreen(new TerminalScreen(terminal));

            getScreen().close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        setModelDraw(new ModelDrawSquare(getScreen()));
    }

    public void readyScreen() throws IOException {
        TextGraphics graphics = getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFF100"));
        graphics.putString(11, 20, "READY!", SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#00F9FF"));
        graphics.putString(9, 14, "PLAYER ONE", SGR.BOLD);
        getScreen().refresh();
    }

    public void presentationScreen() throws IOException {
        getScreen().clear();
        TextGraphics graphics = getScreen().newTextGraphics();
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
        getScreen().refresh();
    }

    public void inicialScreen() throws IOException {
        getScreen().clear();
        TextGraphics graphics = getScreen().newTextGraphics();
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

        getScreen().refresh();
    }

    public void endScreen(Boolean winner, GameStats gameStats) throws IOException {
        getScreen().clear();
        TextGraphics graphics = getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(3, 0, "1UP   HIGH SCORE   2UP", SGR.BOLD);
        graphics.putString(5, 1, "00", SGR.BOLD);
        graphics.putString(0, 35, "CREDIT 3", SGR.BOLD);

        graphics.putString(20, 15, String.valueOf(gameStats.getScore()), SGR.BOLD);
        graphics.putString(20, 17, String.valueOf(gameStats.getEatenCoins()), SGR.BOLD);
        graphics.putString(20, 19, String.valueOf(gameStats.getEatenGhosts()), SGR.BOLD);
        graphics.putString(20, 21, String.valueOf(gameStats.getEatenPP()), SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC2FF"));
        graphics.putString(3, 33, "@ 1980 MIDWAY MFG. CO.", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC55B"));
        if (winner) {
            graphics.putString(6, 10, "CONGRATULATIONS", SGR.BOLD);
            graphics.putString(10, 11, "YOU WON", SGR.BOLD);
        } else {
            graphics.putString(9, 10, "GAME OVER", SGR.BOLD);
            graphics.putString(3, 11, "BETTER LUCK NEXT TIME", SGR.BOLD);
        }

        graphics.putString(5, 15, "SCORE", SGR.BOLD);
        graphics.putString(5, 17, "COINS", SGR.BOLD);
        graphics.putString(5, 19, "GHOSTS", SGR.BOLD);
        graphics.putString(5, 21, "POWERPELLETS", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#00F9FF"));
        graphics.putString(5, 26, "PUSH START BUTTON", SGR.BOLD);

        getScreen().refresh();
    }

    public void pauseScreen(int i) throws IOException {
        getScreen().clear();
        TextGraphics graphics = getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(3, 0, "1UP   HIGH SCORE   2UP", SGR.BOLD);
        graphics.putString(5, 1, "00", SGR.BOLD);
        graphics.putString(0, 35, "CREDIT 2", SGR.BOLD);

        if (i == 0) {
            graphics.putString(6, 10, "> Continue", SGR.BOLD);
            graphics.putString(6, 11, "  Exit", SGR.BOLD);
        } else {
            graphics.putString(6, 10, "  Continue", SGR.BOLD);
            graphics.putString(6, 11, "> Exit", SGR.BOLD);
        }

        getScreen().refresh();
    }
}
