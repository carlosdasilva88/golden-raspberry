package com.hashcode.percistence.movie.factory;

import com.hashcode.domain.movie.model.MovieList;
import com.hashcode.percistence.movie.entity.MovieListEntity;

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
            movieListEntity.setWinner((!movies.winner().isBlank() && movies.winner().equalsIgnoreCase("yes")));
            movieList.add(movieListEntity);
        }
        return movieList;
    }

    public static List<MovieList> buildEntityToModel(List<MovieListEntity> movieListEntity) {
        List<MovieList> movieList = new ArrayList<>();
        for(MovieListEntity movies : movieListEntity) {
            String isWinner = movies.getWinner() ? "yes": "";
            movieList.add(new MovieList(movies.getYear(), movies.getTitle(), movies.getStudios(), movies.getProducers(), isWinner));
        }
        return movieList;
    }
}
