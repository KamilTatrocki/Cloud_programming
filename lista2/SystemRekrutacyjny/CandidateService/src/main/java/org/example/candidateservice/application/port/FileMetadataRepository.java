package org.example.candidateservice.application.port;

import org.example.candidateservice.domain.model.FileMetadata;

import java.util.Optional;

public interface FileMetadataRepository {

    FileMetadata save(FileMetadata fileMetadata);

    Optional<FileMetadata> findById(Long id);
}
