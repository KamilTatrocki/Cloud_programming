package org.example.interviewservice.application.port;

public interface EventPublisher {
    void publish(Object event);
}
