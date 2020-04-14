import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import javafx.stage.Screen;

public class PacMan {
    int x;
    int y;
    char direction;

    public PacMan(int x, int y) {
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

    public void moveUp(){
        this.y = getY()-1;
    }

    public void moveDown(){
        this.y = getY()+1;
    }

    public void moveRight(){
        this.x = getX()+1;
    }

    public void moveLeft(){
        this.x = getX()-1;
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.ANSI.YELLOW);
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(), getY()), "0");
    }
}
