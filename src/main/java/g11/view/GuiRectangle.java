package g11.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import g11.model.GameStats;

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

    public GuiRectangle(int no) {
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
        setModelDraw(new ModelDrawRectangle(getScreen()));

    }

    @Override
    void readyScreen() throws IOException {
        getScreen().clear();
        TextGraphics graphics = getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(3, 0, "READY SCREEN", SGR.BOLD);
        getScreen().refresh();
    }

    @Override
    void presentationScreen() throws IOException {
        getScreen().clear();
        TextGraphics graphics = getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(3, 0, "PRESENTATION SCREEN", SGR.BOLD);
        getScreen().refresh();
    }

    @Override
    void inicialScreen() throws IOException {
        getScreen().clear();
        TextGraphics graphics = getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(3, 0, "INITIAL SCREEN", SGR.BOLD);
        getScreen().refresh();
    }

    @Override
    void endScreen(Boolean winner, GameStats gameStats) throws IOException {
        getScreen().clear();
        TextGraphics graphics = getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(3, 0, "END SCREEN", SGR.BOLD);
        getScreen().refresh();
    }

    @Override
    void pauseScreen(int i) throws IOException {
        getScreen().clear();
        TextGraphics graphics = getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(3, 0, "PAUSE SCREEN", SGR.BOLD);
        getScreen().refresh();
    }
}