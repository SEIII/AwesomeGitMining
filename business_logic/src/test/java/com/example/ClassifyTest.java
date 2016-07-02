package com.example;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ClassifyTest {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            FileReader input = new FileReader(new File("valueLevel.properties"));
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        double base = Double.valueOf(properties.getProperty("base"));
        
        
    }
}
