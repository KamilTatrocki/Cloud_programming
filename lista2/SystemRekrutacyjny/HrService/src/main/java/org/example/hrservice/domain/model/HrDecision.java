package org.example.hrservice.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.hrservice.domain.enums.HiringDecision;

import java.time.LocalDateTime;

@Entity
@Table(name = "hr_decisions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HrDecision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long candidateId;

    @Column(nullable = false)
    private Long feedbackId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HiringDecision finalDecision;

    @Column(length = 2000)
    private String notes;

    @Column(nullable = false)
    private LocalDateTime decidedAt;
}
