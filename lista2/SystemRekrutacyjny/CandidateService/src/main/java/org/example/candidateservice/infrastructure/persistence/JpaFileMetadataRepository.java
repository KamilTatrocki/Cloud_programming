package org.example.candidateservice.infrastructure.persistence;

import org.example.candidateservice.application.port.FileMetadataRepository;
import org.example.candidateservice.domain.model.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFileMetadataRepository extends JpaRepository<FileMetadata, Long>, FileMetadataRepository {
}
