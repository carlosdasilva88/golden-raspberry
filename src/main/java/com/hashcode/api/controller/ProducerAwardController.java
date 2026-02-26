package com.hashcode.api.controller;

import com.hashcode.api.response.ProducerAwardResponse;
import com.hashcode.application.usecase.GetProducerAwardIntervalUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerAwardController {

    private final GetProducerAwardIntervalUseCase getProducerAwardIntervalUseCase;

    public ProducerAwardController(GetProducerAwardIntervalUseCase getProducerAwardIntervalUseCase) {
        this.getProducerAwardIntervalUseCase = getProducerAwardIntervalUseCase;
    }

    @GetMapping("/producers/interval")
    public ProducerAwardResponse getProducerAwardIntervals() {
        return getProducerAwardIntervalUseCase.execute();
    }
}
