package org.example.hrservice.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hrservice.application.mediator.Mediator;
import org.example.hrservice.application.query.GetHrDecisionQuery;
import org.example.hrservice.domain.model.HrDecision;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/hr-decisions")
@RequiredArgsConstructor
public class HrController {

    private final Mediator mediator;

    @GetMapping("/{id}")
    public ResponseEntity<HrDecision> getHrDecision(@PathVariable Long id) {
        GetHrDecisionQuery query = GetHrDecisionQuery.builder()
                .hrDecisionId(id)
                .build();

        HrDecision hrDecision = mediator.send(query);
        return ResponseEntity.ok(hrDecision);
    }
}
