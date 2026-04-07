package org.example.interviewservice.application.port;

import org.example.interviewservice.domain.model.Interview;

import java.util.Optional;

public interface InterviewRepository {

    Interview save(Interview interview);

    Optional<Interview> findById(Long id);
}
