package client.message.enums;

public enum MessageSensorType {
    TURN_ON("TURN ON"),
    TURN_OFF("TURN OFF"),
    SET_SENSITIVITY("SET SENSITIVITY"),
    GET_SENSITIVITY("GET SENSITIVITY"),
    GET_DATA("GET DATA"),
    IS_ON("IS ON");


    public final String valueMessage;

    MessageSensorType(String value) {
        valueMessage = value;
    }

    public String getValueMessage() {
        return valueMessage;
    }

}
