package com.github.golovnyakpa.hw10.conf;

import org.jetbrains.annotations.NotNull;

public class Conf {
    public static final String jsonDttmFmt = "MM-dd-yyyy HH:mm:ss";
    public static final String sourceFile = readEnv("SOURCE_FILE_PATH");
    public static final String outFilePath = readEnv("OUT_FILE_PATH");

    private static String readEnv(@NotNull String env) {
        String res = System.getenv(env);
        if (res != null)
            return res;
        else
            throw new IllegalArgumentException(String.format("Env variable %s is not set", env));
    }
}
