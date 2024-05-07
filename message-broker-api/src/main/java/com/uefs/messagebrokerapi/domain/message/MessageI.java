package com.uefs.messagebrokerapi.domain.message;

import com.uefs.messagebrokerapi.domain.enums.sensor.SensorType;

public interface MessageI {
    String handleMessage(String message);

    String getMessage(SensorType sensorType);

    String getMessage(SensorType sensorType, Integer valueSensitivity);

}
