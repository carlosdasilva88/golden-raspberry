package com.hashcode.percistence.movie.repository;

import com.hashcode.percistence.movie.entity.MovieListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieListRepository extends JpaRepository<MovieListEntity, Long> {
}
