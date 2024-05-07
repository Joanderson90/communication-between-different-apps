package com.uefs.messagebrokerapi.service.sensor;

import com.uefs.messagebrokerapi.domain.enums.sensor.SensorType;
import com.uefs.messagebrokerapi.rest.dto.SensorDTO;

public interface SensorServiceI {
    void turnON(SensorType sensorType);

    void turnOFF(SensorType sensorType);

    void setSensitivity(SensorType sensorType, Integer sensitivity);

    SensorDTO getSensorData(SensorType sensorType);

    void sendMessageToSensorTCPServer(String message);

    String sendMessageToSensorUDPServer(String message);

    SensorType validateSensorTypeParameter(String sensorType);

    Boolean isON(SensorType sensorType);


}


