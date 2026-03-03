package com.hashcode.api.controller;

import com.hashcode.api.response.ProducerAwardResponse;
import com.hashcode.application.usecase.GetProducerAwardIntervalUseCase;
import com.hashcode.application.usecase.LoadAwardFromCsvUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ProducerAwardController {

    private final GetProducerAwardIntervalUseCase getProducerAwardIntervalUseCase;
    private final LoadAwardFromCsvUseCase loadAwardFromCsvUseCase;

    public ProducerAwardController(GetProducerAwardIntervalUseCase getProducerAwardIntervalUseCase, LoadAwardFromCsvUseCase loadAwardFromCsvUseCase) {
        this.getProducerAwardIntervalUseCase = getProducerAwardIntervalUseCase;
        this.loadAwardFromCsvUseCase = loadAwardFromCsvUseCase;
    }

    @GetMapping("/producers/award-interval")
    public ProducerAwardResponse getProducerAwardIntervals() {
        return getProducerAwardIntervalUseCase.execute();
    }

}
