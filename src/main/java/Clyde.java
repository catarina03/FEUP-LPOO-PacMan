import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Clyde extends MapComponent{
    public Clyde(int x, int y) {
        super(x,y);
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.ANSI.GREEN); //Should be orange
        textGraphics.setCharacter(getX(), getY(), Symbols.SINGLE_LINE_T_UP);
    }
}