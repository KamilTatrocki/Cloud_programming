package org.example.hrservice.application.command;

import lombok.*;
import org.example.hrservice.application.mediator.Command;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessHiringDecisionCommand implements Command<Void> {

    private Long candidateId;
    private Long feedbackId;
    private String feedbackDecision;
    private String technicalFeedback;
    private String substantiveEvaluation;
}
