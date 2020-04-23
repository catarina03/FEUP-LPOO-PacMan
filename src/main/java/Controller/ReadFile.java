package Controller;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
    private File myObj;
    private Scanner myReader;

    public ReadFile(){
        try {
            myObj = new File("mapv2.txt");
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<String> fileContent(){
        ArrayList<String> arrayList= new ArrayList<>();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            arrayList.add(data);
        }
        myReader.close();
        return arrayList;
    }

}