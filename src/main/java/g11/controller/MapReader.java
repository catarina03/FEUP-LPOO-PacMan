package g11.controller;

import g11.model.elements.*;
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
    private final ArrayList<Gate> gates;
    private final ArrayList<MapComponent> mapComponents;
    private final ArrayList<Ghost> ghosts;
    private final ArrayList<Position> unturnable;
    private Position pacManposition;

    public MapReader(ReadFile readFile) {
        map = new ArrayList<>();
        walls = new ArrayList<>();
        emptySpaces = new ArrayList<>();
        coins = new ArrayList<>();
        powerPellets = new ArrayList<>();
        gates = new ArrayList<>();
        mapComponents = new ArrayList<>();
        ghosts = new ArrayList<>();
        unturnable = new ArrayList<>();

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
                    Blinky blinky = readFile.getFile().getName().equals(new String("mapv1.txt")) ?
                        new Blinky(x, y, new Position(25,0)) :
                        new Blinky(x, y, new Position(45,2));
                    mapComponents.add(blinky);
                    ghosts.add(blinky);
                    // Depois de o Blinky sair do sitio tem de ficar um espaço vazio atrás
                    EmptySpace emptySpace = new EmptySpace(x, y);
                    emptySpaces.add(emptySpace);
                    mapComponents.add(emptySpace);
                }
                else if (ch == 'I'){
                    Inky inky = readFile.getFile().getName().equals(new String("mapv1.txt")) ?
                            new Inky(x, y, new Position(27,34)) :
                            new Inky(x, y, new Position(49,34));
                    mapComponents.add(inky);
                    ghosts.add(inky);
                    EmptySpace emptySpace = new EmptySpace(x, y);
                    emptySpaces.add(emptySpace);
                    mapComponents.add(emptySpace);
                }
                else if (ch == 'P'){
                    Pinky pinky = readFile.getFile().getName().equals(new String("mapv1.txt")) ?
                            new Pinky(x, y, new Position(2,0)) :
                            new Pinky(x, y, new Position(3,2));
                    mapComponents.add(pinky);
                    ghosts.add(pinky);
                    EmptySpace emptySpace = new EmptySpace(x, y);
                    emptySpaces.add(emptySpace);
                    mapComponents.add(emptySpace);
                }
                else if (ch == 'K'){
                    Clyde clyde = readFile.getFile().getName().equals(new String("mapv1.txt")) ?
                            new Clyde(x, y, new Position(0,34)) :
                            new Clyde(x, y, new Position(0,34));
                    mapComponents.add(clyde);
                    ghosts.add(clyde);
                    EmptySpace emptySpace = new EmptySpace(x, y);
                    emptySpaces.add(emptySpace);
                    mapComponents.add(emptySpace);
                }
                else if (ch == '$'){
                    PowerPellet powerPellet = new PowerPellet(x,y);
                    powerPellets.add(powerPellet);
                    mapComponents.add(powerPellet);
                }
                else if(ch == 'M'){
                    pacManposition = new Position(x,y);
                    EmptySpace emptySpace = new EmptySpace(x, y);
                    emptySpaces.add(emptySpace);
                    mapComponents.add(emptySpace);
                }
                else if (ch == 'g') {
                    Gate gate = new Gate(x, y);
                    gates.add(gate);
                    mapComponents.add(gate);
                } else if (ch == 'x') {
                    unturnable.add(new Position(x, y));
                    EmptySpace emptySpace = new EmptySpace(x, y);
                    emptySpaces.add(emptySpace);
                    mapComponents.add(emptySpace);
                } else if (ch == 'o') {
                    Coin coin = new Coin(x, y);
                    coins.add(coin);
                    mapComponents.add(coin);
                    unturnable.add(new Position(x, y));
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
        return new Map(map, walls, emptySpaces, coins, powerPellets, gates, mapComponents, unturnable);
    }

    public List<Ghost> ghostList() {
        return ghosts;
    }

    public void setPacManposition(Position pacManposition) {
        this.pacManposition = pacManposition;
    }
}
