package com.uefs.messagebrokerapi.domain.enums.message;

import lombok.Getter;

@Getter
public enum MessageBeanID {
    MESSAGE_TURN_ON_SENSOR("messageTurnOnSensor"),

    MESSAGE_TURN_OFF_SENSOR("messageTurnOffSensor"),

    MESSAGE_SET_SENSITIVITY("messageSetSensitivitySensor"),

    MESSAGE_GET_DATA("messageGetDataSensor"),

    MESSAGE_IS_ON("messageIsOnSensor");


    public final String valueMessage;

    MessageBeanID(String value) {
        valueMessage = value;
    }

}
