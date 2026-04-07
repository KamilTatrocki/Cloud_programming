package org.example.interviewservice.application.query;

import lombok.*;
import org.example.interviewservice.application.mediator.Query;
import org.example.interviewservice.domain.model.Interview;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInterviewQuery implements Query<Interview> {

    private Long interviewId;
}
