package controller;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class ReadFile {
    private File myObj;

    // get file from classpath, resources folder
    ReadFile(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            myObj = new File(resource.getFile());
        }

    }

    public ArrayList<String> fileContent() {
        ArrayList<String> arrayList = new ArrayList<>();
        try (FileReader reader = new FileReader(myObj);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                arrayList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}