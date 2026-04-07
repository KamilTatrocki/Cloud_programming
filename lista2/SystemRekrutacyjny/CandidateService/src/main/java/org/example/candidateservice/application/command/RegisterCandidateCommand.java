package org.example.candidateservice.application.command;

import lombok.*;
import org.example.candidateservice.application.mediator.Command;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterCandidateCommand implements Command<Long> {

    private String firstName;
    private String lastName;
    private String email;
    private String fileName;
    private String fileExtension;
    private Long fileSize;
}
