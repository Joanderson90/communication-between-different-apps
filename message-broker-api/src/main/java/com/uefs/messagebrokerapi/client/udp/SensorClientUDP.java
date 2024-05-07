package com.uefs.messagebrokerapi.client.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SensorClientUDP extends Thread {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;
    private byte[] buf;

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
