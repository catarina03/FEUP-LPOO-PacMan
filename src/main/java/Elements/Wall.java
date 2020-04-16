package Elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Wall extends Fixed {
    public Wall(int x, int y) {
        super(x,y);
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);
        textGraphics.putString(getX(), getY(), " ", SGR.BOLD);
    }
}
