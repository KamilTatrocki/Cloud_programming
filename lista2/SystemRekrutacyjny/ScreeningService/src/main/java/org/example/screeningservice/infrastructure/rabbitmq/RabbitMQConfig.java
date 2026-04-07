package org.example.screeningservice.infrastructure.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.screeningservice.application.dto.CandidateRegisteredEvent;
import org.example.screeningservice.application.dto.ScreeningPassedEvent;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "recruitment.exchange";

    // Queue names derived from event class names (reflection pattern)
    public static final String CANDIDATE_REGISTERED_QUEUE = CandidateRegisteredEvent.class.getSimpleName();
    public static final String SCREENING_PASSED_QUEUE = ScreeningPassedEvent.class.getSimpleName();

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
    public Queue candidateRegisteredQueue() {
        return new Queue(CANDIDATE_REGISTERED_QUEUE, true);
    }

    @Bean
    public Queue screeningPassedQueue() {
        return new Queue(SCREENING_PASSED_QUEUE, true);
    }

    @Bean
    public Binding candidateRegisteredBinding(Queue candidateRegisteredQueue, TopicExchange recruitmentExchange) {
        return BindingBuilder.bind(candidateRegisteredQueue)
                .to(recruitmentExchange)
                .with(CANDIDATE_REGISTERED_QUEUE);
    }

    @Bean
    public Binding screeningPassedBinding(Queue screeningPassedQueue, TopicExchange recruitmentExchange) {
        return BindingBuilder.bind(screeningPassedQueue)
                .to(recruitmentExchange)
                .with(SCREENING_PASSED_QUEUE);
    }

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
