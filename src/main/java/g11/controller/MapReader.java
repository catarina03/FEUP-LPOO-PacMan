package g11.controller;

import g11.model.Elements.*;
import g11.model.Map;
import g11.model.Position;

import java.util.ArrayList;
import java.util.List;

public class MapReader {
    private ArrayList<String> map;
    private final ArrayList<Wall> walls;
    private final ArrayList<EmptySpace> emptySpaces;
    private final ArrayList<Coin> coins;
    private final ArrayList<PowerPellet> powerPellets;
    private final ArrayList<MapComponent> mapComponents;
    private final ArrayList<Ghost> ghosts;
    private Position pacManposition;

    public MapReader(ReadFile readFile) {
        map = new ArrayList<>();
        walls = new ArrayList<>();
        emptySpaces = new ArrayList<>();
        coins = new ArrayList<>();
        powerPellets = new ArrayList<>();
        mapComponents = new ArrayList<>();
        ghosts = new ArrayList<>();

        int x; // entre 0 e 27
        int y = 3; // entre 4 e 34

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
                    ghosts.add(blinky);
                }
                else if (ch == 'I'){
                    Inky inky = new Inky(x, y);
                    mapComponents.add(inky);
                    ghosts.add(inky);
                }
                else if (ch == 'P'){
                    Pinky pinky = new Pinky(x, y);
                    mapComponents.add(pinky);
                    ghosts.add(pinky);
                }
                else if (ch == 'K'){
                    Clyde clyde = new Clyde(x, y);
                    mapComponents.add(clyde);
                    ghosts.add(clyde);
                }
                else if (ch == '$'){
                    PowerPellet powerPellet = new PowerPellet(x,y);
                    powerPellets.add(powerPellet);
                    mapComponents.add(powerPellet);
                }
                else if(ch == 'M'){
                    pacManposition = new Position(x,y);
                }
                x++;
            }
            y++;
        }


    }

    public Position getPacManposition() {
        return pacManposition;
    }

    public PacMan startingPacMan() {
        return new PacMan(pacManposition);
    }

    public Map getMap() {
        return new Map(map, walls, emptySpaces, coins, powerPellets, mapComponents);
    }

    public List<Ghost> ghostList() {
        return ghosts;
    }

    public void setPacManposition(Position pacManposition) {
        this.pacManposition = pacManposition;
    }
}
