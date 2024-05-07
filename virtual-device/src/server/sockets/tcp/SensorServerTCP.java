package server.sockets.tcp;

import client.message.tcp.MessageTCP;

import java.net.*;
import java.io.*;

public class SensorServerTCP {
    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            
            while (true)
                new SensorClientHandler(serverSocket.accept()).start();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }

    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class SensorClientHandler extends Thread {
        private final Socket clientSocket;

        public SensorClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                System.out.println("Client connect: " + clientSocket.getInetAddress().getHostAddress());

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    MessageTCP messageTCP = new MessageTCP();


                    if (".".equals(inputLine)) {
                        out.println("bye");
                        break;
                    }

                    out.println(messageTCP.handleMessage(inputLine));
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }


}