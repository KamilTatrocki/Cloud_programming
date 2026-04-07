package org.example.screeningservice.application.command;

import lombok.*;
import org.example.screeningservice.application.mediator.Command;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessScreeningCommand implements Command<Void> {

    private Long candidateId;
    private String firstName;
    private String lastName;
    private String email;
    private String fileName;
}
