package edu.wit.mobileapp.partythyme;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class PlantFileHelper {
    String filename;
    public PlantFileHelper(Context context)
    {
        filename = "/data/data/" + context.getApplicationContext().getPackageName() + "/MyPlants.txt";
    }

    public boolean removePlant(String pName, String pNick){
        File inputFile = new File(filename);
        File tempFile = new File(filename + "_temp");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = pName + "," + pNick;
            String currentLine;
            Log.v("myApp", lineToRemove);

            boolean onlyRemoveOne = false; //If the user has 2 plants with the same name and nickname, only remove one of them
            while((currentLine = reader.readLine()) != null){
                String trimmedLine = currentLine.trim();
                if(!trimmedLine.equals(lineToRemove) || onlyRemoveOne){
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                else
                {
                    onlyRemoveOne = true;
                }
            }
            writer.close();
            reader.close();
            inputFile.delete();
            return tempFile.renameTo(inputFile);
        }
        catch(Exception e){
            return false;
        }
    }
}
