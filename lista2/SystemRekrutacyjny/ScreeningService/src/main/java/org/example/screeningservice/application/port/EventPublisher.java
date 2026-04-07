package org.example.screeningservice.application.port;

public interface EventPublisher {
    void publish(Object event);
}
