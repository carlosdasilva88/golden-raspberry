package com.hashcode.movie.persistence.repository;

import com.hashcode.movie.persistence.entity.MovieListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieListRepository extends JpaRepository<MovieListEntity, Long> {
}
