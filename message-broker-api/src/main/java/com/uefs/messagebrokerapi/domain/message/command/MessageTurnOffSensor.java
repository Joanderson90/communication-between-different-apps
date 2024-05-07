package com.uefs.messagebrokerapi.domain.message.command;

import com.uefs.messagebrokerapi.domain.enums.message.MessageSensorType;
import com.uefs.messagebrokerapi.domain.enums.sensor.SensorType;
import com.uefs.messagebrokerapi.domain.message.MessageI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(MessageTurnOffSensor.BEAN_ID)
@Slf4j
public class MessageTurnOffSensor implements MessageI {
    public static final String BEAN_ID = "messageTurnOffSensor";

    @Override
    public String handleMessage(String message) {
        return "";
    }

    @Override
    public String getMessage(SensorType sensorType) {
        return MessageSensorType.TURN_OFF.getValueMessage() + "," + sensorType;
    }

    @Override
    public String getMessage(SensorType sensorType, Integer valueSensitivity) {
        return "";
    }
}
