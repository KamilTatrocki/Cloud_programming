package org.example.hrservice.infrastructure.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hrservice.application.command.ProcessHiringDecisionCommand;
import org.example.hrservice.application.dto.FeedbackSubmittedEvent;
import org.example.hrservice.application.mediator.Mediator;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FeedbackSubmittedEventListener {

    private final Mediator mediator;

    @RabbitListener(queues = "FeedbackSubmittedEvent")
    public void handleFeedbackSubmittedEvent(FeedbackSubmittedEvent event) {
        log.info("Received FeedbackSubmittedEvent: candidateId={}, feedbackId={}, decision={}",
                event.getCandidateId(), event.getFeedbackId(), event.getDecision());

        ProcessHiringDecisionCommand command = ProcessHiringDecisionCommand.builder()
                .candidateId(event.getCandidateId())
                .feedbackId(event.getFeedbackId())
                .feedbackDecision(event.getDecision())
                .technicalFeedback(event.getTechnicalFeedback())
                .substantiveEvaluation(event.getSubstantiveEvaluation())
                .build();

        mediator.send(command);

        log.info("ProcessHiringDecision command dispatched for candidate ID: {}", event.getCandidateId());
    }
}
