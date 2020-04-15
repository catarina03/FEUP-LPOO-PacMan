import Elements.*;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;

public class Map {
    private ArrayList<String> map;
    private ArrayList<Wall> walls;
    private ArrayList<EmptySpace> emptySpaces;
    private ArrayList<Coin> coins;
    private ArrayList<PowerPellet> powerPellets;

    public Map() {
        ReadFile readFile = new ReadFile();
        map = new ArrayList<>();
        walls = new ArrayList<>();
        emptySpaces = new ArrayList<>();
        coins = new ArrayList<>();
        powerPellets = new ArrayList<>();

        int x; // entre 0 e 27
        int y = 3; // entre 4 e 34

        map = readFile.fileContent();
        for (String string : map){
            x=0;
            for (char ch : string.toCharArray()){
                if (ch == '#'){
                    walls.add(new Wall(x, y));
                }
                else if(ch == 'e'){
                    emptySpaces.add(new EmptySpace(x, y));
                }
                else if (ch == 'c'){
                    coins.add( new Coin(x, y));
                }
                else if (ch == 'B'){
                    Blinky blinky = new Blinky(x, y);
                }
                else if (ch == 'I'){
                    Inky inky = new Inky(x, y);
                }
                else if (ch == 'P'){
                    Pinky pinky = new Pinky(x, y);
                }
                else if (ch == 'K'){
                    Clyde clyde = new Clyde(x, y);
                }
                else if (ch == '$'){
                    powerPellets.add(new PowerPellet(x, y));
                }
                else if(ch == 'M'){
                    Cherry cherry = new Cherry(x, y);
                }
                x++;
            }
            y++;
        }
    }

    public void draw(TextGraphics textGraphics){
        int x;
        int y=3;

        /* TODO: Ter este ciclo for a funcionar
        for (Elements.MapComponent mapc : mapComponents){
            mapc.draw();
        }*/

        for (String string : map){
            x=0;
            for (char ch : string.toCharArray()){
                if (ch == '#'){
                    Wall wall = new Wall(x, y);
                    wall.draw(textGraphics);
                }
                else if(ch == 'e'){
                    EmptySpace emptySpace = new EmptySpace(x, y);
                    emptySpace.draw(textGraphics);
                }
                else if (ch == 'c'){
                    Coin coin = new Coin(x, y);
                    coin.draw(textGraphics);
                }
                else if (ch == 'B'){
                    Blinky blinky = new Blinky(x, y);
                    blinky.draw(textGraphics);
                }
                else if (ch == 'I'){
                    Inky inky = new Inky(x, y);
                    inky.draw(textGraphics);
                }
                else if (ch == 'P'){
                    Pinky pinky = new Pinky(x, y);
                    pinky.draw(textGraphics);
                }
                else if (ch == 'K'){
                    Clyde clyde = new Clyde(x, y);
                    clyde.draw(textGraphics);
                }
                else if (ch == '$'){
                    PowerPellet powerPellet = new PowerPellet(x, y);
                    powerPellet.draw(textGraphics);
                }
                else if (ch == 'M'){
                    Cherry cherry = new Cherry(x, y);
                    cherry.draw(textGraphics);
                }
                else{
                    textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
                    textGraphics.putString(x, y, String.valueOf(ch), SGR.BOLD);
                }
                textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
                textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
                x++;
            }
            y++;
        }
    }
}
