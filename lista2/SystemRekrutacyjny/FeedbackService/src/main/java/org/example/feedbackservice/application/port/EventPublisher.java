package org.example.feedbackservice.application.port;

public interface EventPublisher {
    void publish(Object event);
}
