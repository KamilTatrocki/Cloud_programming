package org.example.screeningservice.application.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreeningPassedEvent implements Serializable {

    private Long candidateId;
    private String screeningResult;
    private String notes;
}
