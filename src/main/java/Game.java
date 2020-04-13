import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
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
    private ArrayList<String> map;
    public Game() {
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
    }

    private void draw() throws IOException {
        ReadFile readFile = new ReadFile();
        screen.clear();
        int x = 0; // entre 0 e 27
        int y = 3; // entre 4 e 34
        TextGraphics textGraphics = screen.newTextGraphics();

        screen.setCharacter(x, y, new TextCharacter('X'));
        map = readFile.fileContent();
        for (String string : map){
            x=0;
            for (char ch : string.toCharArray()){
                if (ch == '#'){
                    textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);
                    textGraphics.putString(x, y, " ", SGR.BOLD);
                }
                else if(ch == 'e'){
                    textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
                    textGraphics.putString(x, y, " ", SGR.BOLD);
                }
                else if (ch == 'c'){
                    textGraphics.setForegroundColor(TextColor.ANSI.YELLOW);
                    textGraphics.putString(x, y, "o", SGR.BOLD);
                }
                else{
                    textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
                    textGraphics.putString(x, y, String.valueOf(ch), SGR.BOLD);
                }
                textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
                textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
                x++;
            }
            y++;
        }


        drawGameStats();
        screen.refresh();
    }

    private void drawGameStats(){
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.RED);
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.putString(9, 1, "SCORE", SGR.BOLD);
        textGraphics.putString(29, 1, "HI-SCORE", SGR.BOLD);
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.putString(13, 2, "0", SGR.BOLD);
        textGraphics.putString(31, 2, "10000", SGR.BOLD);
        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW);
        textGraphics.putString(9, 34, "00000", SGR.BOLD);
        textGraphics.setForegroundColor(TextColor.ANSI.RED);
        textGraphics.putString(35, 34, "o", SGR.BOLD);
        //screen.setCharacter(27, 23, new TextCharacter('X'));
    }

    public void run() throws IOException {
        draw();
    }

}

