import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class EmptySpace extends MapComponent {
    public EmptySpace(int x, int y) {
        super(x,y);
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.putString(getX(), getY(), " ", SGR.BOLD);
    }
}