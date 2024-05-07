package sensor.strategy.enums.command;

import client.message.enums.MessageSensorType;
import sensor.Sensor;
import sensor.strategy.enums.CommandSensorStrategyI;


public class CommandSensorTurnONStrategy implements CommandSensorStrategyI {
    @Override
    public void doCommand(Sensor sensor) {
        sensor.turnOn();
    }

    @Override
    public MessageSensorType messageSensorType() {
        return null;
    }
}
