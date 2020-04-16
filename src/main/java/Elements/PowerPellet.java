package Elements;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class PowerPellet extends Fixed {

    public PowerPellet(int x, int y) {
        super(x,y);
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW); //Should be orange
        textGraphics.setCharacter(getX(), getY(), Symbols.SPADES);
    }
}