package Others;

import Elements.*;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;

public class Map {
    private ArrayList<String> map;
    private ArrayList<Wall> walls;
    private ArrayList<EmptySpace> emptySpaces;
    private ArrayList<Coin> coins;
    private ArrayList<PowerPellet> powerPellets;
    private ArrayList<MapComponent> mapComponents;

    public ArrayList<String> getMap() {
        return map;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public ArrayList<EmptySpace> getEmptySpaces() {
        return emptySpaces;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public ArrayList<PowerPellet> getPowerPellets() {
        return powerPellets;
    }

    public Map() {
        ReadFile readFile = new ReadFile();
        map = new ArrayList<>();
        walls = new ArrayList<>();
        emptySpaces = new ArrayList<>();
        coins = new ArrayList<>();
        powerPellets = new ArrayList<>();
        mapComponents = new ArrayList<>();

        int x; // entre 0 e 27
        int y = 3; // entre 4 e 34

        // TODO : não está a printar ghosts nem cerejas, no problem for now
        map = readFile.fileContent();
        for (String string : map){
            x=0;
            for (char ch : string.toCharArray()){
                if (ch == '#'){
                    Wall wall = new Wall(x,y);
                    walls.add(wall);
                    mapComponents.add(wall);
                }
                else if(ch == 'e'){
                    EmptySpace emptySpace = new EmptySpace(x, y);
                    emptySpaces.add(emptySpace);
                    mapComponents.add(emptySpace);
                }
                else if (ch == 'c'){
                    Coin coin = new Coin(x,y);
                    coins.add( coin);
                    mapComponents.add(coin);
                }
                else if (ch == 'B'){
                    Blinky blinky = new Blinky(x, y);
                    mapComponents.add(blinky);
                }
                else if (ch == 'I'){
                    Inky inky = new Inky(x, y);
                    mapComponents.add(inky);
                }
                else if (ch == 'P'){
                    Pinky pinky = new Pinky(x, y);
                    mapComponents.add(pinky);
                }
                else if (ch == 'K'){
                    Clyde clyde = new Clyde(x, y);
                    mapComponents.add(clyde);
                }
                else if (ch == '$'){
                    PowerPellet powerPellet = new PowerPellet(x,y);
                    powerPellets.add(powerPellet);
                    mapComponents.add(powerPellet);
                }
                else if(ch == 'M'){
                    Cherry cherry = new Cherry(x, y);
                    mapComponents.add(cherry);
                }
                x++;
            }
            y++;
        }
    }

    public void draw(TextGraphics textGraphics){
        for (MapComponent mp : mapComponents) mp.draw(textGraphics);
    }
}
