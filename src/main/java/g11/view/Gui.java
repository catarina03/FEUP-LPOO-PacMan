package g11.view;

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

public class Gui {
    private Terminal terminal;
    private Screen screen;
    private ModelDraw modelDraw;

    public Gui() {
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
            Terminal terminal = defaultTerminalFactory.createTerminal();

            //Terminal terminal = new SwingTerminal(terminalsize, TerminalEmulatorDeviceConfiguration.getDefault(), fontConfig, TerminalEmulatorColorConfiguration.getDefault());*/

            //terminal.enterPrivateMode();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (IOException | FontFormatException | IllegalArgumentException e) {
            //Handle exception
            e.printStackTrace();
            //System.out.println("Error in SquareFont");
        }
        /*try {
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
        */
        /*
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font., new File("A.ttf")));
        } catch (IOException| FontFormatException e) {
            //Handle exception
        }
        */
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

        modelDraw.drawPacMan(gameData);
        modelDraw.drawGameStats(gameData);

        screen.refresh();
    }

}
