package com.hashcode.movie.persistence.factory;

import com.hashcode.movie.persistence.entity.MovieListEntity;
import com.hashcode.movie.domain.model.MovieList;

import java.util.ArrayList;
import java.util.List;

public final class MovieListEntityFactory {

    public static List<MovieListEntity> build(List<MovieList> movieListModel) {
        List<MovieListEntity> movieList = new ArrayList<>();
        for(MovieList movies : movieListModel) {
            MovieListEntity movieListEntity = new MovieListEntity();
            movieListEntity.setYear(movies.year());
            movieListEntity.setTitle(movies.title());
            movieListEntity.setStudios(movies.studios());
            movieListEntity.setProducers(movies.producers());
            movieListEntity.setWinner(movies.winner());
            movieList.add(movieListEntity);
        }
        return movieList;
    }
}
