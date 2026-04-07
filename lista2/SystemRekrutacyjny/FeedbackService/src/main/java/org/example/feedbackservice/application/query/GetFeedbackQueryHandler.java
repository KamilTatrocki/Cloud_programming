package org.example.feedbackservice.application.query;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.feedbackservice.application.mediator.QueryHandler;
import org.example.feedbackservice.application.port.FeedbackRepository;
import org.example.feedbackservice.domain.model.Feedback;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetFeedbackQueryHandler implements QueryHandler<GetFeedbackQuery, Feedback> {

    private final FeedbackRepository feedbackRepository;

    @Override
    public Feedback handle(GetFeedbackQuery query) {
        log.info("Fetching feedback with ID: {}", query.getFeedbackId());
        return feedbackRepository.findById(query.getFeedbackId())
                .orElseThrow(() -> new RuntimeException("Feedback not found with ID: " + query.getFeedbackId()));
    }
}
