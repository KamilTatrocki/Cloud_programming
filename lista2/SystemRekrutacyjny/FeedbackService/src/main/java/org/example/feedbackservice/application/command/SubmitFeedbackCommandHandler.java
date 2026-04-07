package org.example.feedbackservice.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.feedbackservice.application.dto.FeedbackSubmittedEvent;
import org.example.feedbackservice.application.mediator.CommandHandler;
import org.example.feedbackservice.application.port.EventPublisher;
import org.example.feedbackservice.application.port.FeedbackRepository;
import org.example.feedbackservice.domain.enums.FeedbackDecision;
import org.example.feedbackservice.domain.model.Feedback;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubmitFeedbackCommandHandler implements CommandHandler<SubmitFeedbackCommand, Long> {

    private final FeedbackRepository feedbackRepository;
    private final EventPublisher eventPublisher;

    @Override
    @Transactional
    public Long handle(SubmitFeedbackCommand command) {
        log.info("Submitting feedback for candidate ID: {}, interview ID: {}, decision: {}",
                command.getCandidateId(), command.getInterviewId(), command.getDecision());

        FeedbackDecision decision;
        try {
            decision = FeedbackDecision.valueOf(command.getDecision().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.warn("Unknown decision '{}', defaulting to HOLD", command.getDecision());
            decision = FeedbackDecision.HOLD;
        }

        // Save feedback to database
        Feedback feedback = Feedback.builder()
                .candidateId(command.getCandidateId())
                .interviewId(command.getInterviewId())
                .technicalFeedback(command.getTechnicalFeedback())
                .substantiveEvaluation(command.getSubstantiveEvaluation())
                .decision(decision)
                .submittedAt(LocalDateTime.now())
                .build();
        feedback = feedbackRepository.save(feedback);

        log.info("Feedback saved with ID: {} for candidate ID: {}", feedback.getId(), command.getCandidateId());

        // Publish FeedbackSubmittedEvent
        FeedbackSubmittedEvent event = FeedbackSubmittedEvent.builder()
                .candidateId(command.getCandidateId())
                .feedbackId(feedback.getId())
                .decision(decision.name())
                .technicalFeedback(command.getTechnicalFeedback())
                .substantiveEvaluation(command.getSubstantiveEvaluation())
                .build();
        eventPublisher.publish(event);

        log.info("FeedbackSubmittedEvent published for candidate ID: {}", command.getCandidateId());

        return feedback.getId();
    }
}
