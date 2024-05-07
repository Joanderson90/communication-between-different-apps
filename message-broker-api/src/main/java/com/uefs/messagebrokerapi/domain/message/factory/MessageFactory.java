package com.uefs.messagebrokerapi.domain.message.factory;

import com.uefs.messagebrokerapi.domain.message.MessageI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MessageFactory {

    private final Map<String, MessageI> messageMap;

    public MessageI getMessageProvider(String messageType) {
        return messageMap.get(messageType);
    }
}
