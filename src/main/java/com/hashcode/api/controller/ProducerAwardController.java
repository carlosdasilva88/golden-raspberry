package com.hashcode.api.controller;

import com.hashcode.domain.producerawardinterval.model.ProducerAwardResponse;
import com.hashcode.domain.producerawardinterval.service.ProducerAwardIntervalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerAwardController {

    private final ProducerAwardIntervalService service;

    public ProducerAwardController(ProducerAwardIntervalService service) {
        this.service = service;
    }

    @GetMapping("/producers/interval")
    public ProducerAwardResponse getProducerAwardIntervals() {
        return service.executeAnalysis();
    }
}
