import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Cherry extends MapComponent implements Drawable{
    public Cherry(int x, int y) {
        super(x,y);
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.ANSI.RED); //Should be orange
        textGraphics.setCharacter(getX(), getY(), Symbols.CLUB);
    }
}