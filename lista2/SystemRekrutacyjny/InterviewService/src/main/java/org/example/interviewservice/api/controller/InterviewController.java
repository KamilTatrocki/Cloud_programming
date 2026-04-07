package org.example.interviewservice.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.interviewservice.application.mediator.Mediator;
import org.example.interviewservice.application.query.GetInterviewQuery;
import org.example.interviewservice.domain.model.Interview;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor
public class InterviewController {

    private final Mediator mediator;

    @GetMapping("/{id}")
    public ResponseEntity<Interview> getInterview(@PathVariable Long id) {
        GetInterviewQuery query = GetInterviewQuery.builder()
                .interviewId(id)
                .build();

        Interview interview = mediator.send(query);
        return ResponseEntity.ok(interview);
    }
}
