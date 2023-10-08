package com.github.golovnyakpa.hw20.server;

import com.github.golovnyakpa.hw20.conf.Conf;
import com.github.golovnyakpa.hw20.server.business.RequestsHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final ExecutorService threadPool = Executors.newFixedThreadPool(Conf.executorsNum);

    public void start() throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(Conf.port)) {
            System.out.println("Started server at port: " + Conf.port);
            while (true) {
                threadPool.submit(new SocketHandler(serverSocket.accept()));
            }
        }
    }

    private static class SocketHandler implements Runnable {
        Socket socket;

        public SocketHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader br = null;
            PrintWriter pw = null;
            try {
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                Request request = handleRequestMiddleware(br);
                String response = RequestsHandler.handleRequest(request);
                Thread.sleep(2000); // to imitate long file transfer
                pw.println(response + "\n" + Conf.EOF);
                pw.flush();
            } catch (Exception e) {
                pw.println("HTTP status code 500");
            } finally {
                pw.close();
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private Request handleRequestMiddleware(BufferedReader reader) throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while (!(line = reader.readLine()).equals(Conf.EOF)) {
                stringBuilder.append(line).append("\n");
            }

            // todo current protocol supports only uri parsing. Can be extended further
            //  to use query params, headers and so on
            String uri = (stringBuilder.toString().split("\n")[0]).replaceAll("[\\n\\r]+", "");

            return new Request(uri);
        }
    }
}
