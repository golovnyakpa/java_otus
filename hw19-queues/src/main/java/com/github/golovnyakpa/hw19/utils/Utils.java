package com.github.golovnyakpa.hw19.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {

    public static <T> void writeToFile(T element, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String elementStr = element.toString();

            bufferedWriter.write(elementStr);
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
