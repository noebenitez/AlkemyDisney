package com.example.challengeAlkemy.repositories;

import com.example.challengeAlkemy.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>, JpaSpecificationExecutor<Movie> {

    public List<Movie> findMovieByReleaseDate(final LocalDate releaseDate);
}
