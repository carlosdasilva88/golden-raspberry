package com.hashcode.repository;

import com.hashcode.entity.MovieListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieListRepository extends JpaRepository<MovieListEntity, Long> {
}
