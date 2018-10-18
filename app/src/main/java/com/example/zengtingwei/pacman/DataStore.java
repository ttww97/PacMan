package com.example.zengtingwei.pacman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class DataStore {
    public ArrayList<String> loadData(String filePath){
        try{
            BufferedReader b_reader = new BufferedReader(new FileReader(filePath));
            String line;
            ArrayList<String> scores = new ArrayList<>();
            while((line = b_reader.readLine()) != null){
                String[] dataline  = line.split(" ");
                scores.add( dataline[0]);
            }
            b_reader.close();
            return scores;
        }
        catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return null;
    }

    public void saveData(String score,String filePath){
        try{BufferedWriter b_writer = new BufferedWriter(new FileWriter(filePath));
            System.out.println(score);
            b_writer.write(score);
            b_writer.newLine();
            b_writer.close();
        }
        catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }


}
