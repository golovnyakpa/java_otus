package com.github.golovnyakpa.hw20.client;

import com.github.golovnyakpa.hw20.conf.Conf;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) {
        System.out.println("Start time: " + LocalDateTime.now());
        var threadPool = Executors.newFixedThreadPool(4);
        threadPool.submit(sendSocketRequest("/tolstoy"));
        threadPool.submit(sendSocketRequest("/wilde"));
        threadPool.submit(sendSocketRequest("/pushkin"));
        threadPool.shutdown();
    }


    private static Runnable sendSocketRequest(String uri) {
        return () -> {
            Socket socket;
            try {
                socket = new Socket(Conf.host, Conf.port);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try (
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                pw.println(uri + "\n" + Conf.EOF);
                pw.flush();
                String line;
                while (!(line = br.readLine()).equals(Conf.EOF)) {
                    System.out.println(line);
                }
                System.out.println(LocalDateTime.now() + ": Got response");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
