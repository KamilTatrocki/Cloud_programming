package org.example.screeningservice.infrastructure.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.screeningservice.application.command.ProcessScreeningCommand;
import org.example.screeningservice.application.dto.CandidateRegisteredEvent;
import org.example.screeningservice.application.mediator.Mediator;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CandidateRegisteredEventListener {

    private final Mediator mediator;

    @RabbitListener(queues = "CandidateRegisteredEvent")
    public void handleCandidateRegisteredEvent(CandidateRegisteredEvent event) {
        log.info("Received CandidateRegisteredEvent: candidateId={}, name={} {}, email={}, file={}",
                event.getCandidateId(), event.getFirstName(), event.getLastName(),
                event.getEmail(), event.getFileName());

        ProcessScreeningCommand command = ProcessScreeningCommand.builder()
                .candidateId(event.getCandidateId())
                .firstName(event.getFirstName())
                .lastName(event.getLastName())
                .email(event.getEmail())
                .fileName(event.getFileName())
                .build();

        mediator.send(command);

        log.info("Screening command dispatched for candidate ID: {}", event.getCandidateId());
    }
}
