package com.hashcode.domain.entity;

import com.hashcode.application.dto.ProducerAwardDto;
import com.hashcode.application.dto.ProducerAwardWithIntervalDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IntervalCalculateEntity {

    private final List<ProducerAwardWithIntervalDto> producerAwardWithIntervalDtoIntevalList = new ArrayList<>();

    public void calculateInterval(Map<String, List<ProducerAwardDto>> producersWithMultipleWins) {
        producersWithMultipleWins.forEach((producer, movies) -> {
            for (int i = 1; i < movies.size(); i++) {
                int previousYear = movies.get(i - 1).year();
                int currentYear = movies.get(i).year();
                int interval = currentYear - previousYear;
                producerAwardWithIntervalDtoIntevalList.add(new ProducerAwardWithIntervalDto(producer,interval,previousYear,currentYear));
            }
        });
    }

    public List<ProducerAwardWithIntervalDto> getProducersWithMinInterval() {
        int minInterval = producerAwardWithIntervalDtoIntevalList.stream()
                .mapToInt(ProducerAwardWithIntervalDto::interval)
                .min()
                .orElse(0);

        return producerAwardWithIntervalDtoIntevalList.stream()
                .filter(p -> p.interval() == minInterval)
                .toList();
    }

    public List<ProducerAwardWithIntervalDto> getProducersWithMaxInterval() {
        int maxInterval = producerAwardWithIntervalDtoIntevalList.stream()
                .mapToInt(ProducerAwardWithIntervalDto::interval)
                .max()
                .orElse(0);

        return producerAwardWithIntervalDtoIntevalList.stream()
                .filter(p -> p.interval() == maxInterval)
                .toList();
    }
}
