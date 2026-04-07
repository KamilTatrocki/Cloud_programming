package org.example.feedbackservice.application.command;

import lombok.*;
import org.example.feedbackservice.application.mediator.Command;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmitFeedbackCommand implements Command<Long> {

    private Long candidateId;
    private Long interviewId;
    private String technicalFeedback;
    private String substantiveEvaluation;
    private String decision;
}
