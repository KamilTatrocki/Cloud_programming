package org.example.hrservice.infrastructure.persistence;

import org.example.hrservice.application.port.HrDecisionRepository;
import org.example.hrservice.domain.model.HrDecision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaHrDecisionRepository extends JpaRepository<HrDecision, Long>, HrDecisionRepository {
}
