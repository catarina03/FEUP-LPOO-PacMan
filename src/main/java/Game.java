import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalFactory;

import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private Screen screen;
    private Terminal terminal;
    private Map map;
    private GameStats gameStats;
    private PacMan pacMan;

    public Game() {
        try {
            TerminalSize terminalsize = new TerminalSize(50,36);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalsize);
            terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            map = new Map();
            gameStats = new GameStats(7);
            pacMan = new PacMan(25,26);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        map.draw(screen.newTextGraphics());
        gameStats.draw(screen.newTextGraphics());
        pacMan.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() throws IOException {
        long startTime = System.currentTimeMillis();
        boolean alreadyin = false;
        // ciclo de jogo
        while(true) {
            // Ler Esc para sair de ciclo
            KeyStroke keyStroke = screen.pollInput();
            if(keyStroke != null ){
                if(keyStroke.getKeyType() == KeyType.Escape || keyStroke.getKeyType() == KeyType.EOF)
                    break;
                else
                    pacMan.processKey(keyStroke);
            }
            // taxa de atualização
            if ((System.currentTimeMillis() - startTime) % 250 == 0){
                // como entra mais do que uma vez a cada milissegundo, só vai atualizar uma vez
                if (!alreadyin){
                    pacMan.moveDirection();
                    draw();
                    alreadyin = true;
                }
            }
            else{
                // assim que sair do milissegundo em que dá refresh, avisa que pode dar refresh outra vez
                alreadyin = false;
            }

        }
        if (screen != null)
            screen.close();
    }

}

