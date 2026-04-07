package org.example.feedbackservice.infrastructure.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.feedbackservice.application.port.EventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitEventPublisher implements EventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(Object event) {
        // Use reflection to derive the routing key (queue name) from the event class name
        String routingKey = event.getClass().getSimpleName();
        log.info("Publishing event to exchange='{}', routingKey='{}': {}",
                RabbitMQConfig.EXCHANGE_NAME, routingKey, event);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, routingKey, event);
    }
}
