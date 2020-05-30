package g11.view;

import g11.model.GameData;
import g11.model.elements.MapComponent;

public interface ModelDraw {
    void drawElement(MapComponent element);

    void drawGate(MapComponent element);

    void drawPacMan(GameData gameData);

    void drawGameStats(GameData gameData);

    void drawGhost(GameData gameData);

    void drawCherry(MapComponent element);

    void drawWall(MapComponent element);

    void drawCoin(MapComponent element);

    void drawEmptySpace(MapComponent element);

    void drawPowerPellet(MapComponent element);
}
