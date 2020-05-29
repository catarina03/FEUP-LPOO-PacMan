package g11.controller;

import g11.model.elements.*;
import g11.model.Map;
import g11.model.Position;
import g11.model.elements.ghosts.Blinky;
import g11.model.elements.ghosts.Clyde;
import g11.model.elements.ghosts.Inky;
import g11.model.elements.ghosts.Pinky;
import g11.model.elements.map.*;

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
    private int highScore;

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

        boolean highScoreRead = false;

        Position blinkyPos = null, inkyPos = null, pinkyPos = null, clydePos = null;
        Position blinkyTarget = null, inkyTarget = null, pinkyTarget = null, clydeTarget = null;
        int x; // entre 0 e 27
        int y = 3; // entre 4 e 34

        map = readFile.fileContent();
        for (String string : map) {
            if (!highScoreRead) {
                this.highScore = Integer.parseInt(string);
                highScoreRead = true;
            } else {
                x = 0;
                for (char ch : string.toCharArray()) {
                    switch (ch) {
                        case '#':
                            Wall wall = new Wall(x, y);
                            walls.add(wall);
                            mapComponents.add(wall);
                            break;
                        case 'e':
                            EmptySpace emptySpace = new EmptySpace(x, y);
                            emptySpaces.add(emptySpace);
                            mapComponents.add(emptySpace);
                            break;
                        case 'c':
                            Coin coin = new Coin(x, y);
                            coins.add(coin);
                            mapComponents.add(coin);
                            break;
                        case 'B':
                            blinkyPos = new Position(x, y);
                            // Depois de o Blinky sair do sitio tem de ficar um espaço vazio atrás
                            emptySpace = new EmptySpace(x, y);
                            emptySpaces.add(emptySpace);
                            mapComponents.add(emptySpace);
                            break;
                        case 'I':
                            inkyPos = new Position(x, y);
                            emptySpace = new EmptySpace(x, y);
                            emptySpaces.add(emptySpace);
                            mapComponents.add(emptySpace);
                            break;
                        case 'P':
                            pinkyPos = new Position(x, y);
                            emptySpace = new EmptySpace(x, y);
                            emptySpaces.add(emptySpace);
                            mapComponents.add(emptySpace);
                            break;
                        case 'K':
                            clydePos = new Position(x, y);
                            emptySpace = new EmptySpace(x, y);
                            emptySpaces.add(emptySpace);
                            mapComponents.add(emptySpace);
                            break;
                        case '$':
                            PowerPellet powerPellet = new PowerPellet(x, y);
                            powerPellets.add(powerPellet);
                            mapComponents.add(powerPellet);
                            break;
                        case 'M':
                            pacManposition = new Position(x, y);
                            emptySpace = new EmptySpace(x, y);
                            emptySpaces.add(emptySpace);
                            mapComponents.add(emptySpace);
                            break;
                        case 'g':
                            Gate gate = new Gate(x, y);
                            gates.add(gate);
                            mapComponents.add(gate);
                            break;
                        case 'x':
                            unturnable.add(new Position(x, y));
                            emptySpace = new EmptySpace(x, y);
                            emptySpaces.add(emptySpace);
                            mapComponents.add(emptySpace);
                            break;
                        case 'o':
                            unturnable.add(new Position(x, y));
                            coin = new Coin(x, y);
                            coins.add(coin);
                            mapComponents.add(coin);
                            break;
                        case 'Q':
                            pinkyTarget = new Position(x, y - 3);
                            wall = new Wall(x, y);
                            walls.add(wall);
                            mapComponents.add(wall);
                            break;
                        case 'W':
                            blinkyTarget = new Position(x, y - 3);
                            wall = new Wall(x, y);
                            walls.add(wall);
                            mapComponents.add(wall);
                            break;
                        case 'A':
                            clydeTarget = new Position(x, y + 1);
                            wall = new Wall(x, y);
                            walls.add(wall);
                            mapComponents.add(wall);
                            break;
                        case 'S':
                            inkyTarget = new Position(x, y + 1);
                            wall = new Wall(x, y);
                            walls.add(wall);
                            mapComponents.add(wall);
                            break;
                        case 'T':
                            Moveable.xValueTP = x;
                            break;
                    }
                    x++;
                }
                y++;
            }
        }
        Blinky blinky = new Blinky(blinkyPos, blinkyTarget);
        mapComponents.add(blinky);
        ghosts.add(blinky);

        Inky inky = new Inky(inkyPos, inkyTarget);
        mapComponents.add(inky);
        ghosts.add(inky);

        Pinky pinky = new Pinky(pinkyPos, pinkyTarget);
        mapComponents.add(pinky);
        ghosts.add(pinky);

        Clyde clyde = new Clyde(clydePos, clydeTarget);
        mapComponents.add(clyde);
        ghosts.add(clyde);
    }

    public Position getPacManposition() {
        return pacManposition;
    }

    public PacMan startingPacMan() {
        return new PacMan(pacManposition);
    }

    public int getHighScore() {
        return highScore;
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
