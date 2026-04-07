package org.example.candidateservice.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.candidateservice.application.dto.CandidateRegisteredEvent;
import org.example.candidateservice.application.mediator.CommandHandler;
import org.example.candidateservice.application.port.CandidateRepository;
import org.example.candidateservice.application.port.EventPublisher;
import org.example.candidateservice.application.port.FileMetadataRepository;
import org.example.candidateservice.domain.enums.CandidateStatus;
import org.example.candidateservice.domain.model.Candidate;
import org.example.candidateservice.domain.model.FileMetadata;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterCandidateCommandHandler implements CommandHandler<RegisterCandidateCommand, Long> {

    private final CandidateRepository candidateRepository;
    private final FileMetadataRepository fileMetadataRepository;
    private final EventPublisher eventPublisher;

    @Override
    @Transactional
    public Long handle(RegisterCandidateCommand command) {
        log.info("Registering candidate: {} {} ({})", command.getFirstName(), command.getLastName(), command.getEmail());
        log.info("File received: {}.{}", command.getFileName(), command.getFileExtension());

        // Save candidate with status NEW
        Candidate candidate = Candidate.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .email(command.getEmail())
                .status(CandidateStatus.NEW)
                .build();
        candidate = candidateRepository.save(candidate);

        log.info("Candidate saved with ID: {} and status: {}", candidate.getId(), candidate.getStatus());

        // Save file metadata
        FileMetadata fileMetadata = FileMetadata.builder()
                .fileName(command.getFileName())
                .fileExtension(command.getFileExtension())
                .fileSize(command.getFileSize())
                .uploadTimestamp(LocalDateTime.now())
                .filePath("DEFAULT/PATH")
                .candidateId(candidate.getId())
                .build();
        fileMetadataRepository.save(fileMetadata);

        log.info("File metadata saved: name={}, ext={}, size={}, path={}",
                fileMetadata.getFileName(), fileMetadata.getFileExtension(),
                fileMetadata.getFileSize(), fileMetadata.getFilePath());

        // Publish CandidateRegisteredEvent
        CandidateRegisteredEvent event = CandidateRegisteredEvent.builder()
                .candidateId(candidate.getId())
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .email(candidate.getEmail())
                .fileName(command.getFileName())
                .build();
        eventPublisher.publish(event);

        log.info("CandidateRegisteredEvent published for candidate ID: {}", candidate.getId());

        return candidate.getId();
    }
}
