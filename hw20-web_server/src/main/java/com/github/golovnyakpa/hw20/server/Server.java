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
                pw.println(response);
                pw.flush();
            } catch (Exception e) {
                pw.println("HTTP/1.1 500 Internal Server Error\n");
                System.out.println(e.getMessage());
            } finally {
                pw.close();
                try {
                    socket.close();
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
            var request = stringBuilder.toString().replaceAll("[\\n\\r]+", "");

            // todo current protocol supports only uri parsing. Can be extended further
            //  to use query params, headers and so on
            var startLine = (request.split("\n")[0]);
            var httpMethod = startLine.split(" ")[0];

            if (!httpMethod.equals("GET")) {
                throw new RuntimeException(
                        String.format("Server supports only GET requests, but got %s", httpMethod)
                );
            }
            var resourcePath = startLine.split(" ")[1]; // take path from "GET /path HTTP/1.1"

            return new Request(resourcePath);
        }
    }
}
