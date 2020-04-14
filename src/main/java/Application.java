import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class Application {
    public static void main(String[] args) {
        Game game = new Game();
        try {
            game.run();
        } catch (IOException e) {
            e.printStackTrace();
        }






    }
}
