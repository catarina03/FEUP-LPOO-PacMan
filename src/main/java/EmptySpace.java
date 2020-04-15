import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class EmptySpace {
    int x;
    int y;

    public EmptySpace(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.putString(x, y, " ", SGR.BOLD);
    }
}