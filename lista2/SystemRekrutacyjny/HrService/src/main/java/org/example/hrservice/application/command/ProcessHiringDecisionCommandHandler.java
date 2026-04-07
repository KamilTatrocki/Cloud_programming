package org.example.hrservice.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hrservice.application.mediator.CommandHandler;
import org.example.hrservice.application.port.HrDecisionRepository;
import org.example.hrservice.domain.enums.HiringDecision;
import org.example.hrservice.domain.model.HrDecision;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessHiringDecisionCommandHandler implements CommandHandler<ProcessHiringDecisionCommand, Void> {

    private final HrDecisionRepository hrDecisionRepository;

    @Override
    @Transactional
    public Void handle(ProcessHiringDecisionCommand command) {
        log.info("Processing final hiring decision for candidate ID: {}, feedbackDecision: {}",
                command.getCandidateId(), command.getFeedbackDecision());

        // Map feedback decision to final hiring decision
        HiringDecision finalDecision = mapToHiringDecision(command.getFeedbackDecision());

        String notes = String.format(
                "Final HR decision based on feedback. Technical feedback: '%s'. Substantive evaluation: '%s'. Decision: %s.",
                command.getTechnicalFeedback(),
                command.getSubstantiveEvaluation(),
                finalDecision.name()
        );

        // Save HR decision to database
        HrDecision hrDecision = HrDecision.builder()
                .candidateId(command.getCandidateId())
                .feedbackId(command.getFeedbackId())
                .finalDecision(finalDecision)
                .notes(notes)
                .decidedAt(LocalDateTime.now())
                .build();
        hrDecision = hrDecisionRepository.save(hrDecision);

        log.info("HR decision saved with ID: {} for candidate ID: {}, final decision: {}",
                hrDecision.getId(), command.getCandidateId(), finalDecision);

        return null;
    }

    private HiringDecision mapToHiringDecision(String feedbackDecision) {
        if (feedbackDecision == null) {
            return HiringDecision.ON_HOLD;
        }
        return switch (feedbackDecision.toUpperCase()) {
            case "HIRE" -> HiringDecision.HIRED;
            case "REJECT" -> HiringDecision.REJECTED;
            default -> HiringDecision.ON_HOLD;
        };
    }
}
