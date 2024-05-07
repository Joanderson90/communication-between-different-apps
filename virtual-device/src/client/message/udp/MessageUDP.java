package client.message.udp;

import client.message.MessageI;
import client.message.enums.MessageSensorType;
import sensor.Sensor;
import sensor.enums.SensorType;
import sensor.factory.SensorFactory;

import java.util.Arrays;

public class MessageUDP implements MessageI {
    @Override
    public String handleMessage(String message) {
        String[] messageArray = message.split(",");

        String messageSensor = messageArray[0];

        if (messageSensor.equals(MessageSensorType.GET_DATA.getValueMessage())) {
            String sensorType = messageArray[1];

            Sensor sensor = SensorFactory.getSensor(SensorType.valueOf(sensorType));

            return mapperSensorData(sensor);
        } else if (messageSensor.equals(MessageSensorType.IS_ON.getValueMessage())) {
            String sensorType = messageArray[1];

            Sensor sensor = SensorFactory.getSensor(SensorType.valueOf(sensorType));
            return sensor.isSensorON() ? "TRUE" : "FALSE";
        } else if (messageSensor.equals(MessageSensorType.GET_SENSITIVITY.getValueMessage())) {
            String sensorType = messageArray[1];

            Sensor sensor = SensorFactory.getSensor(SensorType.valueOf(sensorType));
            return String.valueOf(sensor.getSensorSensitivity());
        }


//        System.out.println(Arrays.toString(messageArray));
        return "DEFAULT_MESSAGE";
    }


    String mapperSensorData(Sensor sensor) {
        return String.format("%s,%s,%s",
                sensor.getData(),
                sensor.getSensorSensitivity(),
                Boolean.valueOf(sensor.isSensorON()));
    }


}
