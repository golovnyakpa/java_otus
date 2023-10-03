package com.github.golovnyakpa.hw19.conf;

import java.io.File;

public class Conf {

    private static final String projectRoot = System.getProperty("user.dir");
    private static final String deletedName = "deleted.txt";
    private static final String addedName = "added.txt";
    private static final String currModule = "hw19-queues"; // very bad

    public static String deletedPath = projectRoot + File.separator + currModule + File.separator + deletedName;
    public static String addedPath = projectRoot + File.separator + currModule + File.separator + addedName;

    public static int eventsQueueCapacity = 256;
}
