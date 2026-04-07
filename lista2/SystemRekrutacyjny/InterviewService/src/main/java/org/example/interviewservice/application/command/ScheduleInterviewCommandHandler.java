package org.example.interviewservice.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.interviewservice.application.dto.InterviewScheduledEvent;
import org.example.interviewservice.application.mediator.CommandHandler;
import org.example.interviewservice.application.port.EventPublisher;
import org.example.interviewservice.application.port.InterviewRepository;
import org.example.interviewservice.domain.enums.InterviewStatus;
import org.example.interviewservice.domain.model.Interview;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleInterviewCommandHandler implements CommandHandler<ScheduleInterviewCommand, Void> {

    private static final List<String> RECRUITERS = List.of(
            "Anna Kowalska",
            "Piotr Nowak",
            "Marta Wiśniewska",
            "Tomasz Zając"
    );

    private final InterviewRepository interviewRepository;
    private final EventPublisher eventPublisher;

    @Override
    @Transactional
    public Void handle(ScheduleInterviewCommand command) {
        log.info("Scheduling interview for candidate ID: {}, screening result: {}",
                command.getCandidateId(), command.getScreeningResult());

        // Select a recruiter and interview date
        String recruiterName = selectRecruiter();
        LocalDateTime interviewDate = generateInterviewDate();

        log.info("Selected recruiter: {}, interview date: {}", recruiterName, interviewDate);

        // Save interview to database
        Interview interview = Interview.builder()
                .candidateId(command.getCandidateId())
                .recruiterName(recruiterName)
                .interviewDate(interviewDate)
                .status(InterviewStatus.SCHEDULED)
                .notes(String.format("Interview scheduled based on screening result: %s. Notes: %s",
                        command.getScreeningResult(), command.getScreeningNotes()))
                .build();
        interview = interviewRepository.save(interview);

        log.info("Interview saved with ID: {} for candidate ID: {}", interview.getId(), command.getCandidateId());

        // Publish InterviewScheduledEvent
        InterviewScheduledEvent event = InterviewScheduledEvent.builder()
                .candidateId(command.getCandidateId())
                .interviewId(interview.getId())
                .recruiterName(recruiterName)
                .interviewDate(interviewDate)
                .build();
        eventPublisher.publish(event);

        log.info("InterviewScheduledEvent published for candidate ID: {}", command.getCandidateId());

        return null;
    }

    private String selectRecruiter() {
        int index = new Random().nextInt(RECRUITERS.size());
        return RECRUITERS.get(index);
    }

    private LocalDateTime generateInterviewDate() {
        // Schedule interview 3-10 business days from now
        int daysAhead = 3 + new Random().nextInt(8);
        return LocalDateTime.now().plusDays(daysAhead).withHour(10).withMinute(0).withSecond(0).withNano(0);
    }
}
