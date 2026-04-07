package org.example.feedbackservice.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.feedbackservice.domain.enums.FeedbackDecision;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedbacks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long candidateId;

    @Column(nullable = false)
    private Long interviewId;

    @Column(nullable = false, length = 2000)
    private String technicalFeedback;

    @Column(nullable = false, length = 2000)
    private String substantiveEvaluation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FeedbackDecision decision;

    @Column(nullable = false)
    private LocalDateTime submittedAt;
}
