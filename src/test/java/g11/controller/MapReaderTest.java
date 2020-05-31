package g11.controller;

import g11.model.Position;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
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
        File file = Mockito.mock(File.class);

        ArrayList<String> fileContent = new ArrayList<>();
        fileContent.add("100");
        fileContent.add("#eccBI#");
        fileContent.add("#$PKMg");
        Mockito.when(readFile.fileContent()).thenReturn(fileContent);
        Mockito.when(file.getName()).thenReturn("mapv1.txt");
        Mockito.when(readFile.getFile()).thenReturn(file);

        MapReader mapReader = new MapReader(readFile);
        assertEquals(3, mapReader.getMap().getWalls().size());
        assertEquals(2, mapReader.getMap().getCoins().size());
        assertEquals(6, mapReader.getMap().getEmptySpaces().size());
        assertEquals(1, mapReader.getMap().getPowerPellets().size());
        assertEquals(1, mapReader.getMap().getGates().size());
        assertEquals(4, mapReader.ghostList().size());
        assertEquals(17, mapReader.getMap().getMapComponents().size());
        assertEquals(4, mapReader.getPacManposition().getX());
        assertEquals(4, mapReader.getPacManposition().getY());
    }
}

