package g11.view;

import com.googlecode.lanterna.TerminalSize;
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

    }

    @Override
    void presentationScreen() throws IOException {

    }

    @Override
    void inicialScreen() throws IOException {

    }

    @Override
    void endScreen(Boolean winner, GameStats gameStats) throws IOException {

    }

    @Override
    void pauseScreen(int i) throws IOException {

    }
}