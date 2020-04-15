import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

/**
 * Orange Ghost
 * Personality : Switching between direct Chase to PacMan and fleeing
 */
public class Clyde extends MapComponent implements Drawable{
    public Clyde(int x, int y) {
        super(x,y);
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFA500")); //Should be orange
        textGraphics.setCharacter(getX(), getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
    }
}