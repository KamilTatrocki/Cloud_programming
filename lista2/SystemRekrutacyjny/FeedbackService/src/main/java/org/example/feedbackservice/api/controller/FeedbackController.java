package org.example.feedbackservice.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.feedbackservice.application.command.SubmitFeedbackCommand;
import org.example.feedbackservice.application.mediator.Mediator;
import org.example.feedbackservice.application.query.GetFeedbackQuery;
import org.example.feedbackservice.domain.model.Feedback;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final Mediator mediator;

    @PostMapping
    public ResponseEntity<Map<String, Object>> submitFeedback(
            @RequestParam("candidateId") Long candidateId,
            @RequestParam("interviewId") Long interviewId,
            @RequestParam("technicalFeedback") String technicalFeedback,
            @RequestParam("substantiveEvaluation") String substantiveEvaluation,
            @RequestParam("decision") String decision) {

        log.info("Received feedback submission for candidateId={}, interviewId={}, decision={}",
                candidateId, interviewId, decision);

        SubmitFeedbackCommand command = SubmitFeedbackCommand.builder()
                .candidateId(candidateId)
                .interviewId(interviewId)
                .technicalFeedback(technicalFeedback)
                .substantiveEvaluation(substantiveEvaluation)
                .decision(decision)
                .build();

        Long feedbackId = mediator.send(command);

        return ResponseEntity.ok(Map.of(
                "message", "Feedback submitted successfully",
                "feedbackId", feedbackId
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedback(@PathVariable Long id) {
        GetFeedbackQuery query = GetFeedbackQuery.builder()
                .feedbackId(id)
                .build();

        Feedback feedback = mediator.send(query);
        return ResponseEntity.ok(feedback);
    }
}
