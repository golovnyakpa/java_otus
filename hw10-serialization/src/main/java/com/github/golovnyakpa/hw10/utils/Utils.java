package com.github.golovnyakpa.hw10.utils;

import com.github.golovnyakpa.hw10.conf.Conf;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Utils {

    static public final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat(Conf.jsonDttmFmt)
            .setPrettyPrinting()
            .create();

    public static String readFileToString(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("Read file %s error occurred: %s", fileName, e.getMessage())
            );
        }
    }

    public static void writeStringToFile(String s, Path file) {
        byte[] strToBytes = s.getBytes();
        try {
            Files.write(file, strToBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Can't write to file %s\n%s", file, e));
        }
    }

    public static Path buildPath(String path, String fileName) {
        return Paths.get(path, fileName);
    }
}
