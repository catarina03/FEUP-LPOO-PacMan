import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import javafx.stage.Screen;

public class PacMan {
    int x;
    int y;
    char direction;

    public PacMan(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = 'W';
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
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

    public void moveDirection(){
        switch (direction){
            case 'N':
                moveUp();
                break;
            case 'E':
                moveRight();
                break;
            case 'S':
                moveDown();
                break;
            case 'W':
                moveLeft();
                break;
        }
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.ANSI.YELLOW);
        graphics.enableModifiers(SGR.BOLD);
        switch (direction){
            case 'N':
                graphics.setCharacter(getX(), getY(), Symbols.ARROW_UP);
                break;
            case 'E':
                graphics.setCharacter(getX(), getY(), Symbols.ARROW_RIGHT);
                break;
            case 'S':
                graphics.setCharacter(getX(), getY(), Symbols.ARROW_DOWN);
                break;
            case 'W':
                graphics.setCharacter(getX(), getY(), Symbols.ARROW_LEFT);
                break;
        }
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.enableModifiers(SGR.BOLD);
    }

    public void processKey(KeyStroke key){
        switch (key.getKeyType()){
            case ArrowUp:
                this.setDirection('N');
                break;
            case ArrowDown:
                this.setDirection('S');
                break;
            case ArrowLeft:
                this.setDirection('W');
                break;
            case ArrowRight:
                this.setDirection('E');
                break;
            default:
                break;
        }
    }

}
