package Elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class PowerPellet extends Fixed {

    public PowerPellet(int x, int y) {
        super(x,y);
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFA500")); //Should be orange
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.setCharacter(getX(), getY(), Symbols.SPADES);
    }
}