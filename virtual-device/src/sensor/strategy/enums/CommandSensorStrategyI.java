package sensor.strategy.enums;

import client.message.enums.MessageSensorType;
import sensor.Sensor;

public interface CommandSensorStrategyI {
    void doCommand(Sensor sensor);

    MessageSensorType messageSensorType();
}
