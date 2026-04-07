package org.example.candidateservice.application.query;

import lombok.*;
import org.example.candidateservice.application.mediator.Query;
import org.example.candidateservice.domain.model.Candidate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCandidateQuery implements Query<Candidate> {

    private Long candidateId;
}
