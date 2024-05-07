package client.message.tcp;

import client.message.MessageI;
import client.message.enums.MessageSensorType;
import sensor.Sensor;
import sensor.enums.SensorType;
import sensor.factory.SensorFactory;

public class MessageTCP implements MessageI {
    @Override
    public String handleMessage(String message) {
        String[] messageArray = message.split(",");

        String messageSensor = messageArray[0];

        if (messageSensor.equals(MessageSensorType.TURN_ON.getValueMessage())) {
            Sensor sensor = getSensor(messageArray[1]);
            sensor.turnOn();

        } else if (messageSensor.equals(MessageSensorType.TURN_OFF.getValueMessage())) {
            Sensor sensor = getSensor(messageArray[1]);
            sensor.turnOff();

        } else if (messageSensor.equals(MessageSensorType.SET_SENSITIVITY.getValueMessage())) {
            String sensitivityValue = messageArray[2];

            Sensor sensor = getSensor(messageArray[1]);
            sensor.setSensorSensitivity(Integer.parseInt(sensitivityValue));
        }

        return "OK";
    }

    private static Sensor getSensor(String sensorType) {
        return SensorFactory.getSensor(SensorType.valueOf(sensorType));
    }
}
