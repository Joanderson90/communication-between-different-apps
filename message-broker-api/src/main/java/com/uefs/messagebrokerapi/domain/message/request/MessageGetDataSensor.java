package com.uefs.messagebrokerapi.domain.message.request;

import com.uefs.messagebrokerapi.domain.enums.message.MessageSensorType;
import com.uefs.messagebrokerapi.domain.enums.sensor.SensorType;
import com.uefs.messagebrokerapi.domain.message.MessageI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(MessageGetDataSensor.BEAN_ID)
@Slf4j
public class MessageGetDataSensor implements MessageI {
    public static final String BEAN_ID = "messageGetDataSensor";

    @Override
    public String handleMessage(String message) {
        return "";
    }

    @Override
    public String getMessage(SensorType sensorType) {
        return MessageSensorType.GET_DATA.getValueMessage() + "," + sensorType;

    }

    @Override
    public String getMessage(SensorType sensorType, Integer valueSensitivity) {
        return "";
    }
}
