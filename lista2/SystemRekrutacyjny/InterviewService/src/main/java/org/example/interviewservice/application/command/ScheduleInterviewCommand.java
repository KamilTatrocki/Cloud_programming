package org.example.interviewservice.application.command;

import lombok.*;
import org.example.interviewservice.application.mediator.Command;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleInterviewCommand implements Command<Void> {

    private Long candidateId;
    private String screeningResult;
    private String screeningNotes;
}
