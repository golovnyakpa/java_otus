package com.github.golovnyakpa.hw20.server.business;

import com.github.golovnyakpa.hw20.conf.Conf;
import com.github.golovnyakpa.hw20.server.Request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RequestsHandler {
    public static String handleRequest(Request req) {
        String filePath = Conf.uriToFileMapping.get(req.uri());
        if (filePath != null) {
            return "HTTP status code 200\n" + readFileToString(filePath);
        } else {
            return "HTTP status code 404";
        }
    }

    private static String readFileToString(String fileName) {
        byte[] encodedBytes;
        try {
            encodedBytes = Files.readAllBytes(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }
}
