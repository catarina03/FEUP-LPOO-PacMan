package g11.view;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import g11.model.GameData;
import g11.model.GameStats;
import g11.model.elements.MapComponent;

import java.io.IOException;

public abstract class Gui {
    private Screen screen;
    private ModelDraw modelDraw;

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void setModelDraw(ModelDraw modelDraw) {
        this.modelDraw = modelDraw;
    }

    public void startScreen() throws IOException {
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    public void close() throws Throwable {
        if (screen != null) {
            screen.close();
        }
    }

    public KeyStroke getKeyStroke() throws IOException {
        return screen.readInput();
    }

    public MoveEnumeration getMove() throws IOException {
        // Ler Esc para sair de ciclo
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke != null) {
            switch (keyStroke.getKeyType()) {
                case ArrowUp:
                    return MoveEnumeration.UP;
                case ArrowDown:
                    return MoveEnumeration.DOWN;
                case ArrowLeft:
                    return MoveEnumeration.LEFT;
                case ArrowRight:
                    return MoveEnumeration.RIGHT;
                case Escape:
                case EOF:
                    return MoveEnumeration.ESC;
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

    public abstract void readyScreen() throws IOException;

    public abstract void presentationScreen() throws IOException;

    public abstract void inicialScreen() throws IOException;

    public abstract void endScreen(boolean winner, GameStats gameStats) throws IOException;

    public abstract void pauseScreen(int i) throws IOException;
}
