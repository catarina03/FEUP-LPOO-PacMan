package Elements;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

/**
 * Blue Elements.Ghost
 * Personality : Try to position themselves in front of PacMan
 */
public class Inky extends Ghost implements Drawable{
    public Inky(int x, int y) {
        super(x,y);
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.ANSI.CYAN);
        textGraphics.setCharacter(getX(), getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
    }
}