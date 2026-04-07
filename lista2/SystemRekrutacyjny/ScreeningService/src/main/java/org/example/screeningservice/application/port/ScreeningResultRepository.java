package org.example.screeningservice.application.port;

import org.example.screeningservice.domain.model.ScreeningResult;

import java.util.Optional;

public interface ScreeningResultRepository {

    ScreeningResult save(ScreeningResult screeningResult);

    Optional<ScreeningResult> findById(Long id);
}
