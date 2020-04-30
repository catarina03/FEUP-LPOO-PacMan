package g11.controller;

import g11.model.*;
import g11.model.Elements.*;
import g11.view.Gui;

import java.util.ArrayList;

public class CollisionChecker {
    public CollisionChecker() {
    }

    public boolean collide(Position poselem1, Position poselem2){
        return poselem1.equals(poselem2);
    }

    /**
     * - If you want to check if PacMan will colide with a wall in the next iteration in case he moves forward
     * you call this function with Gui.MOVE ESC
     * - If you want to check if PacMan can make a turn (change in orientation) without coliding with a wall and getting
     * stuck you pass in Gui.MOVE the direction to which PacMan is trying to turn
     * @return true in case of collision
     */
    public boolean checkWallCollision(GameData gameData, Gui.MOVE direction){
        if (direction == Gui.MOVE.ESC){
            direction = orientationToMove(gameData.getPacMan().getOrientation());
        }
        Position pacmannextpos = new Position(0,0);
        switch (direction){
            case UP:
                pacmannextpos = gameData.getPacMan().getPosition().up();
                break;
            case DOWN:
                pacmannextpos = gameData.getPacMan().getPosition().down();
                break;
            case LEFT:
                pacmannextpos = gameData.getPacMan().getPosition().left();
                break;
            case RIGHT:
                pacmannextpos = gameData.getPacMan().getPosition().right();
                break;
        }

        ArrayList<Wall> walls = gameData.getMap().getWalls();
        for(Wall wall : walls){
            if (collide(wall.getPosition(), pacmannextpos)) return true;
        }
        return false;
    }

    public Gui.MOVE orientationToMove(Orientation orientation){
        switch (orientation){
            case UP:
                return Gui.MOVE.UP;
            case DOWN:
                return Gui.MOVE.DOWN;
            case LEFT:
                return Gui.MOVE.LEFT;
            case RIGHT:
                return Gui.MOVE.RIGHT;
        }
        return Gui.MOVE.ESC;
    }

    public GameData updateCoinCollison(GameData gameData) {
        ArrayList<Coin> coins = gameData.getMap().getCoins();
        Coin toremove = null;
        for(Coin coin : coins){
            if (collide(coin.getPosition(), gameData.getPacMan().getPosition())){
                toremove = coin;
                break;
            }
        }
        if (toremove != null) {
            ArrayList<EmptySpace> emptySpace = gameData.getMap().getEmptySpaces();
            ArrayList<MapComponent> components = gameData.getMap().getMapComponents();

            emptySpace.add(new EmptySpace(toremove.getX(), toremove.getY()));
            components.add(new EmptySpace(toremove.getX(), toremove.getY()));
            coins.remove(toremove);
            components.remove(toremove);

            gameData.getMap().setCoins(coins);
            gameData.getMap().setEmptySpaces(emptySpace);
            gameData.getMap().setMapComponents(components);

            GameStats stats = gameData.getGameStats();
            stats.setScore(stats.getScore() + 1);
            gameData.setGameStats(stats);
        }
        return gameData;
    }

}
