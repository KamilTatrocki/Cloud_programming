package org.example.feedbackservice.application.port;

import org.example.feedbackservice.domain.model.Feedback;

import java.util.Optional;

public interface FeedbackRepository {

    Feedback save(Feedback feedback);

    Optional<Feedback> findById(Long id);
}
