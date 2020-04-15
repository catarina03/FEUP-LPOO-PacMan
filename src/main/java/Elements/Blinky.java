package Elements;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

/**
 * Red Elements.Ghost
 * Personality : Direct Chase to Elements.PacMan
 */
public class Blinky extends Ghost implements Drawable {
    public Blinky(int x, int y) {
        super(x,y);
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.ANSI.RED);
        textGraphics.setCharacter(getX(), getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
    }
}