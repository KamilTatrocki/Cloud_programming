package org.example.hrservice.application.query;

import lombok.*;
import org.example.hrservice.application.mediator.Query;
import org.example.hrservice.domain.model.HrDecision;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetHrDecisionQuery implements Query<HrDecision> {

    private Long hrDecisionId;
}
