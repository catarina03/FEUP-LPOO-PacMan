package Model;

import Model.Elements.*;
import Controller.ReadFile;

import java.util.ArrayList;

public class Map {
    private ArrayList<String> map;
    private ArrayList<Wall> walls;
    private ArrayList<EmptySpace> emptySpaces;
    private ArrayList<Coin> coins;
    private ArrayList<PowerPellet> powerPellets;
    private ArrayList<MapComponent> mapComponents;

    public Map(ArrayList<String> map, ArrayList<Wall> walls, ArrayList<EmptySpace> emptySpaces, ArrayList<Coin> coins, ArrayList<PowerPellet> powerPellets, ArrayList<MapComponent> mapComponents) {
        this.map = map;
        this.walls = walls;
        this.emptySpaces = emptySpaces;
        this.coins = coins;
        this.powerPellets = powerPellets;
        this.mapComponents = mapComponents;
    }

    public ArrayList<MapComponent> getMapComponents() {
        return mapComponents;
    }

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

    public void setEmptySpaces(ArrayList<EmptySpace> emptySpaces) {
        this.emptySpaces = emptySpaces;
    }

    public void setCoins(ArrayList<Coin> coins) {
        this.coins = coins;
    }

    public void setMapComponents(ArrayList<MapComponent> mapComponents) {
        this.mapComponents = mapComponents;
    }

    public void setMap(ArrayList<String> map) {
        this.map = map;
    }

    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }

    public void setPowerPellets(ArrayList<PowerPellet> powerPellets) {
        this.powerPellets = powerPellets;
    }
}
