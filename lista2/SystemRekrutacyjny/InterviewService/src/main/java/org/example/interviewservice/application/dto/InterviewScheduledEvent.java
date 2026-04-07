package org.example.interviewservice.application.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewScheduledEvent implements Serializable {

    private Long candidateId;
    private Long interviewId;
    private String recruiterName;
    private LocalDateTime interviewDate;
}
