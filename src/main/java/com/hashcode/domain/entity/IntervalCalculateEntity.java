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

    public List<ProducerAwardWithIntervalDto> getProducersInterval(boolean findMaxInterval) {
         int reference = findMaxInterval ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            List<ProducerAwardWithIntervalDto> result = new ArrayList<>();
            for (ProducerAwardWithIntervalDto producer : producerAwardWithIntervalDtoIntevalList) {
                int current = producer.interval();
                if ((findMaxInterval && current > reference) || (!findMaxInterval && current < reference)) {
                    reference = current;
                    result.clear();
                    result.add(producer);
                } else if (current == reference) {
                    result.add(producer);
                }
            }
            return result;
    }
}
