package org.example.interviewservice.infrastructure.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.interviewservice.application.command.ScheduleInterviewCommand;
import org.example.interviewservice.application.dto.ScreeningPassedEvent;
import org.example.interviewservice.application.mediator.Mediator;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScreeningPassedEventListener {

    private final Mediator mediator;

    @RabbitListener(queues = "ScreeningPassedEvent")
    public void handleScreeningPassedEvent(ScreeningPassedEvent event) {
        log.info("Received ScreeningPassedEvent: candidateId={}, result={}, notes={}",
                event.getCandidateId(), event.getScreeningResult(), event.getNotes());

        ScheduleInterviewCommand command = ScheduleInterviewCommand.builder()
                .candidateId(event.getCandidateId())
                .screeningResult(event.getScreeningResult())
                .screeningNotes(event.getNotes())
                .build();

        mediator.send(command);

        log.info("ScheduleInterview command dispatched for candidate ID: {}", event.getCandidateId());
    }
}
