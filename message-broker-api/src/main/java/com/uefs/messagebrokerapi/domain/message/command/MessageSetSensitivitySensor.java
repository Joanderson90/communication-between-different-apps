package com.uefs.messagebrokerapi.domain.message.command;

import com.uefs.messagebrokerapi.domain.enums.message.MessageSensorType;
import com.uefs.messagebrokerapi.domain.enums.sensor.SensorType;
import com.uefs.messagebrokerapi.domain.message.MessageI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(MessageSetSensitivitySensor.BEAN_ID)
@Slf4j
public class MessageSetSensitivitySensor implements MessageI {
    public static final String BEAN_ID = "messageSetSensitivitySensor";

    @Override
    public String handleMessage(String message) {
        return "";
    }

    @Override
    public String getMessage(SensorType sensorType) {
        return "";
    }

    @Override
    public String getMessage(SensorType sensorType, Integer valueSensitivity) {
        return MessageSensorType.SET_SENSITIVITY.getValueMessage() + "," + sensorType + "," + valueSensitivity.toString();
    }
}
