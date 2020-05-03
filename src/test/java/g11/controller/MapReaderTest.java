package g11.controller;

import g11.model.Position;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MapReaderTest {

    @Test
    public void pacmanPositionTest(){
        ReadFile readFile = Mockito.mock(ReadFile.class);
        MapReader mapReader = new MapReader(readFile);

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(10);
        Mockito.when(position.getY()).thenReturn(11);
        mapReader.setPacManposition(position);

        assertEquals(10, mapReader.startingPacMan().getX());
        assertEquals(11, mapReader.startingPacMan().getY());
    }

    @Test
    public void creationTest(){
        ReadFile readFile = Mockito.mock(ReadFile.class);

        ArrayList<String> file = new ArrayList<>();
        file.add("#eccBI#");
        file.add("#$PKM#");
        Mockito.when(readFile.fileContent()).thenReturn(file);

        MapReader mapReader = new MapReader(readFile);
        assertEquals(4, mapReader.getMap().getWalls().size());
        assertEquals(2, mapReader.getMap().getCoins().size());
        assertEquals(1, mapReader.getMap().getEmptySpaces().size());
        assertEquals(1, mapReader.getMap().getPowerPellets().size());
        assertEquals(4, mapReader.ghostList().size());
        assertEquals(12, mapReader.getMap().getMapComponents().size());
        assertEquals(4, mapReader.getPacManposition().getX());
        assertEquals(4, mapReader.getPacManposition().getY());
    }
}

