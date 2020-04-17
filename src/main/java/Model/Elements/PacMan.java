package Model.Elements;

import com.googlecode.lanterna.input.KeyStroke;

public class PacMan extends Moveable{
    char direction;

    public PacMan(int x, int y) {
        super(x,y);
        this.direction = 'W';
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void moveUp(){ setY(getY()-1); }

    public void moveDown(){
        setY(getY()+1);
    }

    public void moveRight(){
        setX(getX()+1);
    }

    public void moveLeft(){
        setX(getX()-1);
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

    /*
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

     */

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
