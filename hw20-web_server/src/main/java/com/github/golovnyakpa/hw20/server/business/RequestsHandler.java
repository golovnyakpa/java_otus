package com.github.golovnyakpa.hw20.server.business;

import com.github.golovnyakpa.hw20.conf.Conf;
import com.github.golovnyakpa.hw20.server.Request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RequestsHandler {

    private final static String contentType = "Content-Type: text/html; charset=utf-8\n";
    private final static String head = "<head></head>\n";

    public static String handleRequest(Request req) {
        String filePath = Conf.uriToFileMapping.get(req.uri());
        if (filePath != null) {
            return buildOkHttpResponse(readFileToString(filePath));
        } else {
            return "HTTP/1.1 404 Not Found\n";
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

    private static String buildOkHttpResponse(String text) {
        var respStart = "HTTP/1.1 200 OK\n" + contentType + "\n" + head + "<body>\n";
        var respBody = Arrays
                .stream(text.split("\n"))
                .map(line -> "<p>" + line + "</p>")
                .collect(Collectors.joining("\n"));
        var respEnd = "</body>";
        return respStart + respBody + respEnd + Conf.EOF;
    }

}
