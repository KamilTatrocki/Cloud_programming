package org.example.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.BaseEvent;


import java.io.IOException;

public class EventPublisher<T extends BaseEvent> extends BaseConnector<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public EventPublisher(Class<T> eventClass) throws IOException {
        super(eventClass);
    }

    public void publish(T event) {
        logMethodCall("publish", event);
        try {
            byte[] message = objectMapper.writeValueAsBytes(event);
            channel.basicPublish("", queueName, null, message);
        } catch (IOException e) {
            logger.error("Failed to publish message", e);
        }
    }
}
