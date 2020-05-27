package g11.controller;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ReadFileTest {

    @Test(expected = IllegalArgumentException.class)
    public void fileNotFoundTest(){
        String test = "test.txt";

        ReadFile readFile = new ReadFile(test);
    }

    @Test
    public void fileContentTest(){
        String map = "mapv2.txt";

        ReadFile readFile = new ReadFile(map);
        ArrayList result = readFile.fileContent();
        assertEquals(31, result.size());
    }
}
