package com.uefs.messagebrokerapi.exception;

public class ServerSensitivityLimitException extends RuntimeException {
    public ServerSensitivityLimitException() {
        super("Please, set a sensitivity between 1 and 99!");
    }
}
