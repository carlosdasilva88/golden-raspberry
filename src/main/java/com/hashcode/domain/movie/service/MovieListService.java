package com.hashcode.domain.movie.service;

import com.hashcode.domain.movie.model.MovieList;
import com.hashcode.percistence.movie.factory.MovieListEntityFactory;
import com.hashcode.percistence.movie.repository.MovieListRepository;
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

    public List<MovieList> getAllMovies() {
        return MovieListEntityFactory.buildEntityToModel(repository.findAll());
    }
}
