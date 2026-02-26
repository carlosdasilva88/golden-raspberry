package com.hashcode.domain.producerawardinterval.service;

import com.hashcode.domain.movie.model.MovieList;
import com.hashcode.domain.movie.service.MovieListService;
import com.hashcode.domain.producerawardinterval.model.ProducerAward;
import com.hashcode.domain.producerawardinterval.model.ProducerAwardFactory;
import com.hashcode.domain.producerawardinterval.model.ProducerAwardResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProducerAwardIntervalService {

    @Autowired
    MovieListService movieListService;

    public ProducerAwardResponse executeAnalysis() {
        List<MovieList> movies = movieListService.getAllMovies();
        Map<String, List<MovieList>> producersWithMultipleWins = this.groupProducersWithMultipleWins(movies);
        List<ProducerAward> producersAward = calculateIntervals(producersWithMultipleWins);
        return buildProducersAwardResult(producersAward);
    }

    private ProducerAwardResponse buildProducersAwardResult(List<ProducerAward> producersAward) {
        List<ProducerAward> min = new ArrayList<>(getProducersWithMinInterval(producersAward));
        List<ProducerAward> max = new ArrayList<>(getProducersWithMaxInterval(producersAward));
        return new ProducerAwardResponse(min, max);
    }

    private List<ProducerAward> getProducersWithMinInterval(List<ProducerAward> producersAward) {
        int minInterval = producersAward.stream()
                .mapToInt(ProducerAward::getInterval)
                .min()
                .orElse(0);

        return producersAward.stream()
                .filter(p -> p.getInterval() == minInterval)
                .toList();
    }

    private List<ProducerAward> getProducersWithMaxInterval(List<ProducerAward> producersAward) {
        int maxInterval = producersAward.stream()
                .mapToInt(ProducerAward::getInterval)
                .max()
                .orElse(0);

        return producersAward.stream()
                .filter(p -> p.getInterval() == maxInterval)
                .toList();
    }

    private Map<String, List<MovieList>> groupProducersWithMultipleWins(List<MovieList> movies) {
        return movies.stream()
                .filter(movie -> "yes".equalsIgnoreCase(movie.winner()))
                .collect(Collectors.groupingBy(MovieList::producers))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .sorted(Comparator.comparing(MovieList::year))
                                .toList()
                ));
    }

    private List<ProducerAward>  calculateIntervals(Map<String, List<MovieList>> winners) {
        List<ProducerAward> result = new ArrayList<>();
        winners.forEach((producer, movies) -> {
            for (int i = 1; i < movies.size(); i++) {
                int previousYear = movies.get(i - 1).year();
                int currentYear = movies.get(i).year();
                int interval = currentYear - previousYear;
                result.add(ProducerAwardFactory.build(producer,interval,previousYear,currentYear)
                );
            }
        });
        return result;
    }
}
