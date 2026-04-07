package org.example.hrservice.application.port;

import org.example.hrservice.domain.model.HrDecision;

import java.util.Optional;

public interface HrDecisionRepository {

    HrDecision save(HrDecision hrDecision);

    Optional<HrDecision> findById(Long id);
}
