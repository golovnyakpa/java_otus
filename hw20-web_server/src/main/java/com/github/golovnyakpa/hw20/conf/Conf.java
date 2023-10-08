package com.github.golovnyakpa.hw20.conf;

import java.io.File;
import java.util.Map;

public class Conf {

    private static String getFilesBasePath() {
        String filesBasePath = System.getenv("FILES_BASE_PATH");
        if (filesBasePath == null) {
            throw new RuntimeException("Files base configuration not specified");
        }
        return filesBasePath;
    }

    private static final String filesBasePath = getFilesBasePath();

    public static String host = "localhost";
    public static int port = 4444;
    public static int executorsNum = 8;

    public static String EOF = "EOF";

    public static Map<String, String> uriToFileMapping = Map.of(
            "/dostoevsky", Conf.filesBasePath + File.separator + "fyodor_dostoevsky.txt",
            "/wilde", Conf.filesBasePath + File.separator + "oscar_wilde.txt",
            "/tolstoy", Conf.filesBasePath + File.separator + "favourites/leo_tolstoy.txt"
    );
}
