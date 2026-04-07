package org.example.candidateservice.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.candidateservice.application.command.RegisterCandidateCommand;
import org.example.candidateservice.application.mediator.Mediator;
import org.example.candidateservice.application.query.GetCandidateQuery;
import org.example.candidateservice.domain.model.Candidate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final Mediator mediator;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> registerCandidate(
            @RequestParam("file") MultipartFile file,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email) {

        String originalFilename = file.getOriginalFilename();
        String fileName = originalFilename != null && originalFilename.contains(".")
                ? originalFilename.substring(0, originalFilename.lastIndexOf('.'))
                : originalFilename;
        String fileExtension = originalFilename != null && originalFilename.contains(".")
                ? originalFilename.substring(originalFilename.lastIndexOf('.') + 1)
                : "";

        log.info("Received CV upload: fileName={}, extension={}, size={} bytes",
                fileName, fileExtension, file.getSize());

        RegisterCandidateCommand command = RegisterCandidateCommand.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .fileName(fileName)
                .fileExtension(fileExtension)
                .fileSize(file.getSize())
                .build();

        Long candidateId = mediator.send(command);

        return ResponseEntity.ok(Map.of(
                "message", "Candidate registered successfully",
                "candidateId", candidateId
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidate(@PathVariable Long id) {
        GetCandidateQuery query = GetCandidateQuery.builder()
                .candidateId(id)
                .build();

        Candidate candidate = mediator.send(query);
        return ResponseEntity.ok(candidate);
    }
}
