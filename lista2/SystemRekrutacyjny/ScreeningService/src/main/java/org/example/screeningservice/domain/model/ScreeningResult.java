package org.example.screeningservice.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.screeningservice.domain.enums.ScreeningStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "screening_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreeningResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long candidateId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScreeningStatus result;

    @Column(length = 1000)
    private String notes;

    @Column(nullable = false)
    private LocalDateTime screenedAt;
}
