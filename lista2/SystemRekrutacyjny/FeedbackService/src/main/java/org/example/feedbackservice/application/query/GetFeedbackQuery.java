package org.example.feedbackservice.application.query;

import lombok.*;
import org.example.feedbackservice.application.mediator.Query;
import org.example.feedbackservice.domain.model.Feedback;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetFeedbackQuery implements Query<Feedback> {

    private Long feedbackId;
}
