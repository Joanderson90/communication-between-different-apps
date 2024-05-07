package com.uefs.messagebrokerapi.client.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class SensorClientUDP extends Thread {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;
    private byte[] buf;
    private final int TIME_OUT_IN_MILLISECONDS = 30000;

    public SensorClientUDP(String ipServer, int portServer) {
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName(ipServer);
            port = portServer;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String sendMessage(String msg) {
        DatagramPacket packet = null;
        try {
            buf = new byte[1024];
            buf = msg.getBytes();
            packet = new DatagramPacket(buf, buf.length, address, port);

            socket.send(packet);
            socket.setSoTimeout(TIME_OUT_IN_MILLISECONDS);

            packet = new DatagramPacket(buf, buf.length);
            System.out.println("Wating data...");

            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String dataReceive = new String(packet.getData(), 0, packet.getLength()).trim();

        System.out.println("Data received: " + dataReceive);

        return dataReceive;
    }

    public void close() {
        socket.close();
    }

}
