package g11.view;

import g11.model.elements.*;
import g11.model.GameData;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Gui {
    private Terminal terminal;
    private Screen screen;
    private ModelDraw modelDraw;

    public Gui() {
        try {
            TerminalSize terminalsize = new TerminalSize(50,36);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalsize);
            terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary

        } catch (IOException e) {
            e.printStackTrace();
        }
        modelDraw = new ModelDraw(screen);
    }

    public Gui(int no) {
        try {
            TerminalSize terminalsize = new TerminalSize(50,36);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalsize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        modelDraw = new ModelDraw(screen);
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setModelDraw(ModelDraw modelDraw) {
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

    public enum MOVE {UP, DOWN, LEFT, RIGHT, ESC}

    public MOVE getMove() throws IOException {
        // Ler Esc para sair de ciclo
        KeyStroke keyStroke = screen.pollInput();
        if(keyStroke != null ){
            switch (keyStroke.getKeyType()){
                case ArrowUp:
                    return MOVE.UP;
                case ArrowDown:
                    return MOVE.DOWN;
                case ArrowLeft:
                    return MOVE.LEFT;
                case ArrowRight:
                    return MOVE.RIGHT;
                case Escape:
                case EOF:
                    return MOVE.ESC;
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

}