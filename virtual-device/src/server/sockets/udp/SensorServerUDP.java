package server.sockets.udp;

import client.message.udp.MessageUDP;

import java.net.*;
import java.io.*;

public class SensorServerUDP extends Thread {
    protected DatagramSocket socket;
    protected boolean running;
    protected byte[] buf;

    public SensorServerUDP() throws IOException {
        socket = new DatagramSocket(5555);
    }

    @Override
    public void run() {
        running = true;

        while (running) {
            try {
                buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);

                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                String received = new String(packet.getData(), 0, packet.getLength()).trim();

                MessageUDP messageUDP = new MessageUDP();
                buf = messageUDP.handleMessage(received).getBytes();

                System.out.println("Packet received: " + received);
                if (received.equals("end")) {
                    System.out.println("Closing...");
                    running = false;
                    continue;
                }

                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);

            } catch (IOException e) {
                e.printStackTrace();
                running = false;
            }
        }

        socket.close();
    }
}
