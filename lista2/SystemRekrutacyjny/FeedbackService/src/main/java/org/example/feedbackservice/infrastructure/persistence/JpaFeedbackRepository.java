package org.example.feedbackservice.infrastructure.persistence;

import org.example.feedbackservice.application.port.FeedbackRepository;
import org.example.feedbackservice.domain.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFeedbackRepository extends JpaRepository<Feedback, Long>, FeedbackRepository {
}
