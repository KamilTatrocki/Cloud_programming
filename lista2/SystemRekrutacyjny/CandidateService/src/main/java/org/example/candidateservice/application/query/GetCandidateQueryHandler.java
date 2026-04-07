package org.example.candidateservice.application.query;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.candidateservice.application.mediator.QueryHandler;
import org.example.candidateservice.application.port.CandidateRepository;
import org.example.candidateservice.domain.model.Candidate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetCandidateQueryHandler implements QueryHandler<GetCandidateQuery, Candidate> {

    private final CandidateRepository candidateRepository;

    @Override
    public Candidate handle(GetCandidateQuery query) {
        log.info("Fetching candidate with ID: {}", query.getCandidateId());
        return candidateRepository.findById(query.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + query.getCandidateId()));
    }
}
