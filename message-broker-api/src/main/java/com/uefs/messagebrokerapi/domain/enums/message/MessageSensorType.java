package com.uefs.messagebrokerapi.domain.enums.message;

import lombok.Getter;

@Getter
public enum MessageSensorType {
    TURN_ON("TURN ON"),
    TURN_OFF("TURN OFF"),
    SET_SENSITIVITY("SET SENSITIVITY"),
    GET_DATA("GET DATA"),
    IS_ON("IS ON");


    public final String valueMessage;

    MessageSensorType(String value) {
        valueMessage = value;
    }

}
