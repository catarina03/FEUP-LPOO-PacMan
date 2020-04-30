package g11.controller;

import g11.model.*;
import g11.model.Elements.*;
import g11.view.Gui;

import java.util.ArrayList;

public class Game {
    private Gui gui;
    private GameData gameData;
    private Boolean running;
    private MapReader mapReader;
    private Gui.MOVE lastmove;

    public Game() {
        gui = new Gui();
        mapReader = new MapReader("mapv2.txt");
        gameData = new GameData(new GameStats(0),
                                mapReader.startingPacMan(),
                                mapReader.ghostList(),
                                mapReader.getMap());
        lastmove = Gui.MOVE.LEFT;
    }

    public void run() throws Throwable {
        running = true;
        long startTime = System.currentTimeMillis();
        boolean alreadyin = false;
        // ciclo de jogo
        while(running) {
            Gui.MOVE temp = gui.getMove();
            if (temp != null)
                lastmove = temp;
            processKey(lastmove);
            // taxa de atualização
            if ((System.currentTimeMillis() - startTime) % 200 == 0){
                // como entra mais do que uma vez a cada milissegundo, só vai atualizar uma vez
                if (!alreadyin){
                    Update(gameData);
                    gui.draw(gameData);
                    alreadyin = true;
                }
            }
            else{
                // assim que sair do milissegundo em que dá refresh, avisa que pode dar refresh outra vez
                alreadyin = false;
            }
        }
    }

    private void Update(GameData gameData) {

        // Can Pacman move to next position?
            // Pacman's next position?
            // Is a wall in the position pacman is about to move to?
        // yes -> update position
        // no  -> don't update
        if (!checkWallColison(pacManNextPosition())){
            coinColison(gameData.getPacMan().getPosition());
            gameData.update();
        }
    }

    private Position pacManNextPosition(){
        Position position = gameData.getPacMan().getPosition();
        Orientation orientation = gameData.getPacMan().getOrientation();
        switch (orientation){
            case UP:
                return position.up();
            case DOWN:
                return position.down();
            case LEFT:
                return position.left();
            case RIGHT:
                return position.right();
        }
        return position;
    }

    private boolean checkWallColison(Position pmnextpos){
        ArrayList<Wall> walls = gameData.getMap().getWalls();
        for(Wall wall : walls){
            if (wall.getPosition().equals(pmnextpos)) return true;
        }
        return false;
    }

    private boolean coinColison(Position pos){
        ArrayList<Coin> coins = gameData.getMap().getCoins();
        for(Coin coin : coins){
            if (coin.getPosition().equals(pos)){
                ArrayList<EmptySpace> emptySpace = gameData.getMap().getEmptySpaces();
                ArrayList<MapComponent> components = gameData.getMap().getMapComponents();

                emptySpace.add(new EmptySpace(coin.getX(), coin.getY()));
                components.add(new EmptySpace(coin.getX(), coin.getY()));
                coins.remove(coin);
                components.remove(coin);

                gameData.getMap().setCoins(coins);
                gameData.getMap().setEmptySpaces(emptySpace);
                gameData.getMap().setMapComponents(components);

                GameStats stats = gameData.getGameStats();
                stats.setScore(stats.getScore() + 1);
                gameData.setGameStats(stats);

                return true;
            }
        }
        return false;
    }

    public void processKey(Gui.MOVE move) throws Throwable {

        if (move != null){
            switch (move){
                case ESC:
                    gui.close();
                    running = false;
                    break;
                case UP:
                    // check if can change position
                    if (!checkWallColison(gameData.getPacMan().getPosition().up()))
                        gameData.getPacMan().setOrientation(Orientation.UP);
                    break;
                case DOWN:
                    if (!checkWallColison(gameData.getPacMan().getPosition().down()))
                        gameData.getPacMan().setOrientation(Orientation.DOWN);
                    break;
                case LEFT:
                    if (!checkWallColison(gameData.getPacMan().getPosition().left()))
                        gameData.getPacMan().setOrientation(Orientation.LEFT);
                    break;
                case RIGHT:
                    if (!checkWallColison(gameData.getPacMan().getPosition().right()))
                        gameData.getPacMan().setOrientation(Orientation.RIGHT);
                    break;
            }
        }
    }



}

