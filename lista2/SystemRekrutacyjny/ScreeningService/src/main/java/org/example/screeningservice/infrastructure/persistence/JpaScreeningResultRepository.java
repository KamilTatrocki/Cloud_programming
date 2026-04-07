package org.example.screeningservice.infrastructure.persistence;

import org.example.screeningservice.application.port.ScreeningResultRepository;
import org.example.screeningservice.domain.model.ScreeningResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaScreeningResultRepository extends JpaRepository<ScreeningResult, Long>, ScreeningResultRepository {
}
