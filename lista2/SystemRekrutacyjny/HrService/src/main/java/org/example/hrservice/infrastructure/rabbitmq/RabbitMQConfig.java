package org.example.hrservice.infrastructure.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.hrservice.application.dto.FeedbackSubmittedEvent;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "recruitment.exchange";

    // Queue names derived from event class names (reflection pattern)
    public static final String FEEDBACK_SUBMITTED_QUEUE = FeedbackSubmittedEvent.class.getSimpleName();

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    @Bean
    public TopicExchange recruitmentExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue feedbackSubmittedQueue() {
        return new Queue(FEEDBACK_SUBMITTED_QUEUE, true);
    }

    @Bean
    public Binding feedbackSubmittedBinding(Queue feedbackSubmittedQueue, TopicExchange recruitmentExchange) {
        return BindingBuilder.bind(feedbackSubmittedQueue)
                .to(recruitmentExchange)
                .with(FEEDBACK_SUBMITTED_QUEUE);
    }

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
