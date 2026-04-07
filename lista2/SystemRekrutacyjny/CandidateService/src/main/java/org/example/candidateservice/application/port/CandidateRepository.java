package org.example.candidateservice.application.port;

import org.example.candidateservice.domain.model.Candidate;

import java.util.Optional;

public interface CandidateRepository {

    Candidate save(Candidate candidate);

    Optional<Candidate> findById(Long id);
}
