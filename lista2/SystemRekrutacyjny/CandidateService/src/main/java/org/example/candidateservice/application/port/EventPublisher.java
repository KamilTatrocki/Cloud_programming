package org.example.candidateservice.application.port;

public interface EventPublisher {
    void publish(Object event);
}
