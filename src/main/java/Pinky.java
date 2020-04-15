import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Pinky extends MapComponent implements Drawable{

    public Pinky(int x, int y) {
        super(x,y);
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.ANSI.MAGENTA);
        textGraphics.setCharacter(getX(), getY(), Symbols.TRIANGLE_UP_POINTING_BLACK);
    }
}