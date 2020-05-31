package g11.view.guis;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import g11.model.GameStats;
import g11.view.Gui;
import g11.view.modeldraws.ModelDrawRectangle;
import g11.view.modeldraws.ModelDrawSquare;

import java.io.IOException;

public class GuiRectangle extends Gui {
    public GuiRectangle() {
        try {
            TerminalSize terminalsize = new TerminalSize(50, 36);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalsize);
            Terminal terminal = terminalFactory.createTerminal();

            setTerminal(terminal);
            setScreen(new TerminalScreen(terminal));

            startScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }
        setModelDraw(new ModelDrawRectangle(getScreen()));
    }

    public GuiRectangle(Screen screen) {
        setScreen(screen);
    }

    @Override
    public void readyScreen() throws IOException {
        TextGraphics graphics = getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFF100"));
        graphics.putString(22, 20, "READY!", SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#00F9FF"));
        graphics.putString(20, 14, "PLAYER ONE", SGR.BOLD);
        getScreen().refresh();
    }

    @Override
    public void presentationScreen() throws IOException {
        getScreen().clear();
        TextGraphics graphics = getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(3, 0, "1UP             HIGH SCORE              2UP", SGR.BOLD);
        graphics.putString(14, 5, "CHARACTER / NICKNAME", SGR.BOLD);
        graphics.putString(21, 26, "10 PTS", SGR.BOLD);
        graphics.putString(21, 27, "50 PTS", SGR.BOLD);
        graphics.putString(5, 1, "00", SGR.BOLD);
        graphics.putString(0, 35, "CREDIT 0", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FF1400"));
        graphics.putString(13, 7, "# -SHADOW    \"BLINKY\" ", SGR.BOLD);
        graphics.putString(20, 18, "#", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC2FF"));
        graphics.putString(12, 33, "@ 1980 MIDWAY MFG. CO.", SGR.BOLD);
        graphics.putString(13, 9, "# -SPEEDY    \"PINKY\" ", SGR.BOLD);
        graphics.putString(22, 18, "#", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#00F9FF"));
        graphics.putString(13, 11, "# -BASHFUL   \"INKY\" ", SGR.BOLD);
        graphics.putString(24, 18, "#", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC55B"));
        graphics.putString(13, 13, "# -POKEY     \"CLYDE\" ", SGR.BOLD);
        graphics.putString(26, 18, "#", SGR.BOLD);
        graphics.putString(18, 27, "*", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFF100"));
        graphics.putString(18, 18, ">", SGR.BOLD);
        graphics.putString(18, 26, ".", SGR.BOLD);
        getScreen().refresh();
    }

    @Override
    public void inicialScreen() throws IOException {
        getScreen().clear();
        TextGraphics graphics = getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(3, 0, "1UP             HIGH SCORE              2UP", SGR.BOLD);
        graphics.putString(5, 1, "00", SGR.BOLD);
        graphics.putString(3, 35, "CREDIT 1", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC55B"));
        graphics.putString(15, 15, "PUSH START BUTTON", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#00F9FF"));
        graphics.putString(17, 19, "1 PLAYER ONLY", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFCEC0"));
        graphics.putString(11, 23, "BONUS PAC-MAN FOR 1000 PTS", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC2FF"));
        graphics.putString(13, 27, "@ 1980 MIDWAY MFG. CO.", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FF1400"));
        graphics.putString(40, 35, "O", SGR.BOLD);

        getScreen().refresh();
    }

    @Override
    public void endScreen(boolean winner, GameStats gameStats) throws IOException {
        getScreen().clear();
        TextGraphics graphics = getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(3, 0, "1UP             HIGH SCORE              2UP", SGR.BOLD);
        graphics.putString(5, 1, "00", SGR.BOLD);
        graphics.putString(0, 35, "CREDIT 3", SGR.BOLD);

        graphics.putString(30, 15, String.valueOf(gameStats.getScore()), SGR.BOLD);
        graphics.putString(30, 17, String.valueOf(gameStats.getEatenCoins()), SGR.BOLD);
        graphics.putString(30, 19, String.valueOf(gameStats.getEatenGhosts()), SGR.BOLD);
        graphics.putString(30, 21, String.valueOf(gameStats.getEatenPP()), SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC2FF"));
        graphics.putString(12, 33, "@ 1980 MIDWAY MFG. CO.", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC55B"));
        if (winner) {
            graphics.putString(16, 10, "CONGRATULATIONS", SGR.BOLD);
            graphics.putString(20, 11, "YOU WON", SGR.BOLD);
        } else {
            graphics.putString(19, 10, "GAME OVER", SGR.BOLD);
            graphics.putString(13, 11, "BETTER LUCK NEXT TIME", SGR.BOLD);
        }

        graphics.putString(15, 15, "SCORE", SGR.BOLD);
        graphics.putString(15, 17, "COINS", SGR.BOLD);
        graphics.putString(15, 19, "GHOSTS", SGR.BOLD);
        graphics.putString(15, 21, "POWERPELLETS", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#00F9FF"));
        graphics.putString(15, 26, "PUSH START BUTTON", SGR.BOLD);

        getScreen().refresh();
    }

    @Override
    public void pauseScreen(int i) throws IOException {
        getScreen().clear();
        TextGraphics graphics = getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(3, 0, "1UP             HIGH SCORE              2UP", SGR.BOLD);
        graphics.putString(5, 1, "00", SGR.BOLD);
        graphics.putString(0, 35, "CREDIT 2", SGR.BOLD);
        graphics.putString(21, 26, "10 PTS", SGR.BOLD);
        graphics.putString(21, 27, "50 PTS", SGR.BOLD);

        graphics.putString(19, 10, "Continue", SGR.BOLD);
        graphics.putString(19, 12, "Exit", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FF1400"));
        if (i == 0) graphics.putString(17, 10, ">", SGR.BOLD);
        else graphics.putString(17, 12, ">", SGR.BOLD);


        graphics.setForegroundColor(TextColor.Factory.fromString("#FFF100"));
        graphics.putString(19, 26, ".", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC55B"));
        graphics.putString(19, 27, "*", SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC2FF"));
        graphics.putString(12, 33, "@ 1980 MIDWAY MFG. CO.", SGR.BOLD);

        getScreen().refresh();
    }
}