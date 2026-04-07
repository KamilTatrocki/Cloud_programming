package org.example.interviewservice.infrastructure.persistence;

import org.example.interviewservice.application.port.InterviewRepository;
import org.example.interviewservice.domain.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaInterviewRepository extends JpaRepository<Interview, Long>, InterviewRepository {
}
