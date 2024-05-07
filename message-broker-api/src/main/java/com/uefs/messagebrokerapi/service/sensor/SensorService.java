package com.uefs.messagebrokerapi.service.sensor;

import com.uefs.messagebrokerapi.client.tcp.SensorClientTCP;
import com.uefs.messagebrokerapi.client.udp.SensorClientUDP;
import com.uefs.messagebrokerapi.domain.enums.message.MessageBeanID;
import com.uefs.messagebrokerapi.domain.enums.sensor.SensorType;
import com.uefs.messagebrokerapi.domain.message.factory.MessageFactory;
import com.uefs.messagebrokerapi.exception.SensorNotMappedException;
import com.uefs.messagebrokerapi.exception.ServerConnectException;
import com.uefs.messagebrokerapi.rest.dto.SensorDTO;
import com.uefs.messagebrokerapi.service.config.MessageBrokerApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class SensorService implements SensorServiceI {
    @Autowired
    private MessageFactory messageFactory;

    @Autowired
    private MessageBrokerApiConfig messageBrokerApiConfig;

    @Override
    public void turnON(SensorType sensorType) {
        String messageTCP = messageFactory
                .getMessageProvider(MessageBeanID.MESSAGE_TURN_ON_SENSOR.getValueMessage())
                .getMessage(sensorType);


        sendMessageToSensorTCPServer(messageTCP);
    }

    @Override
    public void turnOFF(SensorType sensorType) {
        String messageTCP = messageFactory
                .getMessageProvider(MessageBeanID.MESSAGE_TURN_OFF_SENSOR.getValueMessage())
                .getMessage(sensorType);


        sendMessageToSensorTCPServer(messageTCP);
    }


    @Override
    public void setSensitivity(SensorType sensorType, Integer sensitivity) {
        String messageTCP = messageFactory
                .getMessageProvider(MessageBeanID.MESSAGE_SET_SENSITIVITY.getValueMessage())
                .getMessage(sensorType, sensitivity);


        sendMessageToSensorTCPServer(messageTCP);
    }

    @Override
    public SensorDTO getSensorData(SensorType sensorType) {
        String messageUDP = messageFactory
                .getMessageProvider(MessageBeanID.MESSAGE_GET_DATA.getValueMessage())
                .getMessage(sensorType);

        String responseMessage = sendMessageToSensorUDPServer(messageUDP);

        try {
            String[] messageArray = responseMessage.split(",");

            Double dataSensor = Double.valueOf(messageArray[0]);
            Double sensitivitySensor = Double.valueOf(messageArray[1]);
            Boolean isOnSensor = Boolean.valueOf(messageArray[2]);

            return SensorDTO.builder()
                    .data(dataSensor)
                    .sensitivity(sensitivitySensor)
                    .isON(isOnSensor)
                    .dateGeneratedData(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
                    .build();

        } catch (NumberFormatException ex) {
            throw new RuntimeException(ex.getMessage());
        }


    }

    @Override
    public void sendMessageToSensorTCPServer(String message) {
        try {
            SensorClientTCP clientTCP = new SensorClientTCP();

            clientTCP.startConnection(
                    messageBrokerApiConfig.getIpAddressServerTCP(),
                    messageBrokerApiConfig.getPortNumberServerTCP());

            clientTCP.sendMessage(message);

            clientTCP.stopConnection();
        } catch (Exception e) {
            throw new ServerConnectException(e.getMessage());
        }
    }

    @Override
    public String sendMessageToSensorUDPServer(String message) {
        try {
            SensorClientUDP clientUDP = new SensorClientUDP(
                    messageBrokerApiConfig.getIpAddressServerUDP(),
                    messageBrokerApiConfig.getPortNumberServerUDP());

            String responseMessageFromServer = clientUDP.sendMessage(message);
            clientUDP.close();

            return responseMessageFromServer;

        } catch (Exception e) {
            throw new ServerConnectException(e.getMessage());
        }
    }

    @Override
    public SensorType validateSensorTypeParameter(String sensorType) {
        try {
            return SensorType.valueOf(sensorType);
        } catch (IllegalArgumentException e) {
            throw new SensorNotMappedException(String.format("Sensor: %s not mapped!", sensorType));
        }
    }

    @Override
    public Boolean isON(SensorType sensorType) {
        String messageUDP = messageFactory
                .getMessageProvider(MessageBeanID.MESSAGE_IS_ON.getValueMessage())
                .getMessage(sensorType);

        String responseMessage = sendMessageToSensorUDPServer(messageUDP);

        return Boolean.valueOf(responseMessage);
    }


}
