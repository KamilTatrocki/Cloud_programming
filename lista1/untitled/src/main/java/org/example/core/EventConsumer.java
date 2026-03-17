package org.example.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.BaseEvent;


import java.io.IOException;
import java.util.function.Consumer;

public class EventConsumer<T extends BaseEvent> extends BaseConnector<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public EventConsumer(Class<T> eventClass, Consumer<T> messageHandler) throws IOException {
        super(eventClass);
//        logMethodCall("startConsuming", eventClass.getName());

        channel.basicConsume(queueName, true, (consumerTag, delivery) -> {
//            logMethodCall("onMessageReceived", consumerTag);
            try {
                T event = objectMapper.readValue(delivery.getBody(), eventClass);
                messageHandler.accept(event);
            } catch (Exception e) {
                logger.error("Error processing message", e);
            }
        }, consumerTag -> {});
    }
}