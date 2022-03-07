package com.example.challengeAlkemy.services;

import com.example.challengeAlkemy.exceptions.MovieNotExistsException;
import com.example.challengeAlkemy.models.Character;
import com.example.challengeAlkemy.models.Movie;
import com.example.challengeAlkemy.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public Movie add(Movie movie) {
        Movie parsedMovie = Movie.builder()
                .id(movie.getId())
                .image(movie.getImage())
                .title(movie.getTitle())
                .releaseDate(LocalDate.parse(movie.getReleaseDate().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .rating(movie.getRating())
                .characterList(movie.getCharacterList())
                .genres(movie.getGenres()).build();

        return movieRepository.save(parsedMovie);
    }

    public Page<Movie> getAll(Specification<Movie> spec, Pageable pageable) {
        return movieRepository.findAll(spec, pageable);
    }

    public Page<Movie> getAllSorted(Pageable pageable, String direction){
        return movieRepository.findAll(
                PageRequest.of(pageable.getPageNumber(),
                        pageable.getPageSize(),
                        Sort.by(
                                direction.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, "releaseDate")));
    }

    public Movie getById(Integer id) throws MovieNotExistsException {
        return movieRepository.findById(id)
                .orElseThrow(MovieNotExistsException::new);
    }

    public void update(Integer id, Movie updated) throws MovieNotExistsException {
        Movie movie = getById(id);
        movie.setImage(updated.getImage());
        movie.setTitle(updated.getTitle());
        movie.setRating(updated.getRating());
        movie.setReleaseDate(updated.getReleaseDate());
        movie.setGenres(updated.getGenres());
        movie.setCharacterList(updated.getCharacterList());

        movieRepository.save(movie);
    }

    public void delete(Integer id) throws MovieNotExistsException {
        if(movieRepository.existsById(id)){
            movieRepository.deleteById(id);
        }else{
            throw new MovieNotExistsException();
        }
    }
}
