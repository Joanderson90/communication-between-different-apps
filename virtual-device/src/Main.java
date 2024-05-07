import sensor.SensorMenu;
import server.sockets.tcp.SensorServerTCP;
import server.sockets.udp.SensorServerUDP;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new SensorMenu().start();
        new SensorServerUDP().start();
        new SensorServerTCP().start(4545);
    }
}