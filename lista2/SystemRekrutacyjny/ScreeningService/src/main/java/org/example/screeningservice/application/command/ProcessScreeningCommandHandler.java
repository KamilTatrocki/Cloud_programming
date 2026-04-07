package org.example.screeningservice.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.screeningservice.application.dto.ScreeningPassedEvent;
import org.example.screeningservice.application.mediator.CommandHandler;
import org.example.screeningservice.application.port.EventPublisher;
import org.example.screeningservice.application.port.ScreeningResultRepository;
import org.example.screeningservice.domain.enums.ScreeningStatus;
import org.example.screeningservice.domain.model.ScreeningResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessScreeningCommandHandler implements CommandHandler<ProcessScreeningCommand, Void> {

    private final ScreeningResultRepository screeningResultRepository;
    private final EventPublisher eventPublisher;

    @Override
    @Transactional
    public Void handle(ProcessScreeningCommand command) {
        log.info("Starting automated screening for candidate ID: {} ({} {})",
                command.getCandidateId(), command.getFirstName(), command.getLastName());

        // Simulate automated profile analysis
        ScreeningStatus status = performAutomatedAnalysis(command);
        String notes = generateAnalysisNotes(command, status);

        log.info("Screening analysis completed: candidateId={}, result={}", command.getCandidateId(), status);

        // Save screening result
        ScreeningResult result = ScreeningResult.builder()
                .candidateId(command.getCandidateId())
                .result(status)
                .notes(notes)
                .screenedAt(LocalDateTime.now())
                .build();
        screeningResultRepository.save(result);

        log.info("Screening result saved with ID: {}", result.getId());

        // Publish ScreeningPassedEvent
        ScreeningPassedEvent event = ScreeningPassedEvent.builder()
                .candidateId(command.getCandidateId())
                .screeningResult(status.name())
                .notes(notes)
                .build();
        eventPublisher.publish(event);

        log.info("ScreeningPassedEvent published for candidate ID: {}", command.getCandidateId());

        return null;
    }

    private ScreeningStatus performAutomatedAnalysis(ProcessScreeningCommand command) {
        // Simulated analysis — always passes
        log.info("Performing automated analysis for CV: {}", command.getFileName());
        return ScreeningStatus.PASSED;
    }

    private String generateAnalysisNotes(ProcessScreeningCommand command, ScreeningStatus status) {
        return String.format("Automated screening for candidate %s %s (email: %s). " +
                        "CV file '%s' analyzed. Result: %s. Profile meets minimum requirements.",
                command.getFirstName(), command.getLastName(), command.getEmail(),
                command.getFileName(), status.name());
    }
}
