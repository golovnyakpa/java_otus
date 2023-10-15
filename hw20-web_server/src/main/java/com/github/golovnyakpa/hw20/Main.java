package com.github.golovnyakpa.hw20;

import com.github.golovnyakpa.hw20.server.Server;

public class Main {
    public static void main(String[] args) throws Exception {
        var server = new Server();
        server.start();
    }
}
