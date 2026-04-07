package org.example.screeningservice.application.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateRegisteredEvent implements Serializable {

    private Long candidateId;
    private String firstName;
    private String lastName;
    private String email;
    private String fileName;
}
