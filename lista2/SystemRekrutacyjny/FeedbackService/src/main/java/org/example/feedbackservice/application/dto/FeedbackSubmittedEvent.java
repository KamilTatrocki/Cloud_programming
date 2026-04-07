package org.example.feedbackservice.application.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackSubmittedEvent implements Serializable {

    private Long candidateId;
    private Long feedbackId;
    private String decision;
    private String technicalFeedback;
    private String substantiveEvaluation;
}
