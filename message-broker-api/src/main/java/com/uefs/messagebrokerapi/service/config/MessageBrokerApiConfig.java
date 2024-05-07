package com.uefs.messagebrokerapi.service.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class MessageBrokerApiConfig {

    @Value("${com.uefs.messagebrokerapi.ip-address-server-tcp}")
    private String ipAddressServerTCP;

    @Value("${com.uefs.messagebrokerapi.port-number-server-tcp}")
    private int portNumberServerTCP;

    @Value("${com.uefs.messagebrokerapi.ip-address-server-udp}")
    private String ipAddressServerUDP;

    @Value("${com.uefs.messagebrokerapi.port-number-server-udp}")
    private int portNumberServerUDP;
}
