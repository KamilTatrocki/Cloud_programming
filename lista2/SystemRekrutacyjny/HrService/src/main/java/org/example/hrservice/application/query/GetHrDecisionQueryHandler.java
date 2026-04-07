package org.example.hrservice.application.query;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hrservice.application.mediator.QueryHandler;
import org.example.hrservice.application.port.HrDecisionRepository;
import org.example.hrservice.domain.model.HrDecision;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetHrDecisionQueryHandler implements QueryHandler<GetHrDecisionQuery, HrDecision> {

    private final HrDecisionRepository hrDecisionRepository;

    @Override
    public HrDecision handle(GetHrDecisionQuery query) {
        log.info("Fetching HR decision with ID: {}", query.getHrDecisionId());
        return hrDecisionRepository.findById(query.getHrDecisionId())
                .orElseThrow(() -> new RuntimeException("HR decision not found with ID: " + query.getHrDecisionId()));
    }
}
