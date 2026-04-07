package org.example.interviewservice.application.query;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.interviewservice.application.mediator.QueryHandler;
import org.example.interviewservice.application.port.InterviewRepository;
import org.example.interviewservice.domain.model.Interview;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetInterviewQueryHandler implements QueryHandler<GetInterviewQuery, Interview> {

    private final InterviewRepository interviewRepository;

    @Override
    public Interview handle(GetInterviewQuery query) {
        log.info("Fetching interview with ID: {}", query.getInterviewId());
        return interviewRepository.findById(query.getInterviewId())
                .orElseThrow(() -> new RuntimeException("Interview not found with ID: " + query.getInterviewId()));
    }
}
