import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Inky extends MapComponent{
    public Inky(int x, int y) {
        super(x,y);
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.ANSI.CYAN);
        textGraphics.setCharacter(getX(), getY(), Symbols.SINGLE_LINE_T_DOUBLE_UP);
    }
}