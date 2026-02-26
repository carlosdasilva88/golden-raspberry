package com.hashcode.application.usecase;

import com.hashcode.application.dto.ProducerAwardDto;
import com.hashcode.domain.entity.IntervalCalculateEntity;
import com.hashcode.application.dto.ProducerAwardWithIntervalDto;
import com.hashcode.api.response.ProducerAwardResponse;
import com.hashcode.infrastructure.persistence.produceraward.ProducerAwardModelFactory;
import com.hashcode.infrastructure.persistence.produceraward.ProducerAwardRepository;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GetProducerAwardIntervalUseCase {

    private final ProducerAwardRepository producerAwardRepository;

    public GetProducerAwardIntervalUseCase(ProducerAwardRepository repository, ProducerAwardRepository producerAwardRepository) {
        this.producerAwardRepository = producerAwardRepository;
    }

    public ProducerAwardResponse execute() {
        IntervalCalculateEntity intervalCalculate = new IntervalCalculateEntity();
        List<ProducerAwardDto> producerAwardDtoList = getAllProducersAward();
        Map<String, List<ProducerAwardDto>> producersWithMultipleWins = this.groupProducersWithMultipleWins(producerAwardDtoList);
        intervalCalculate.calculateInterval(producersWithMultipleWins);
        return buildProducersAwardResult(intervalCalculate.getProducersWithMinInterval(), intervalCalculate.getProducersWithMaxInterval());
    }

    public List<ProducerAwardDto> getAllProducersAward() {
        return ProducerAwardModelFactory.buildEntityToModel(producerAwardRepository.findAll());
    }

    private ProducerAwardResponse buildProducersAwardResult(List<ProducerAwardWithIntervalDto> producersWithMinInterval, List<ProducerAwardWithIntervalDto> producersWithMaxInterval) {
        return new ProducerAwardResponse(producersWithMinInterval, producersWithMaxInterval);
    }

    private Map<String, List<ProducerAwardDto>> groupProducersWithMultipleWins(List<ProducerAwardDto> movies) {
        return movies.stream()
                .filter(ProducerAwardDto::winner)
                .collect(Collectors.groupingBy(ProducerAwardDto::producers))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .sorted(Comparator.comparing(ProducerAwardDto::year))
                                .toList()
                ));
    }
}
