package com.hashcode.service;

import com.hashcode.repository.MovieListRepository;
import com.hashcode.factory.MovieListEntityFactory;
import com.hashcode.model.MovieList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieListService {

    private static final Logger log = LoggerFactory.getLogger(MovieListService.class);

    @Autowired
    MovieListRepository repository;

    public void save(List<MovieList> movieList) {
        repository.saveAll(MovieListEntityFactory.build(movieList));
        log.info("Dados do CSV persistidos com sucesso.");
    }
}
