package org.example.candidateservice.infrastructure.persistence;

import org.example.candidateservice.application.port.CandidateRepository;
import org.example.candidateservice.domain.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCandidateRepository extends JpaRepository<Candidate, Long>, CandidateRepository {
}
