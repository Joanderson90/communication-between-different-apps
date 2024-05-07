package com.uefs.messagebrokerapi.exception;

public class ServerConnectException extends RuntimeException {
    public ServerConnectException(String message) {
        super("Error connecting to the server!" + "\n" + message);
    }
}
