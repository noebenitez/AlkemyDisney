package com.example.challengeAlkemy.controllers;

import com.example.challengeAlkemy.exceptions.MovieNotExistsException;
import com.example.challengeAlkemy.models.Character;
import com.example.challengeAlkemy.models.Movie;
import com.example.challengeAlkemy.models.dto.MovieDetailsDto;
import com.example.challengeAlkemy.models.dto.MovieDto;
import com.example.challengeAlkemy.services.MovieService;
import com.example.challengeAlkemy.utils.EntityURLBuilder;
import com.example.challengeAlkemy.utils.ResponseEntityMaker;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private static final String MOVIES_PATH = "/api/movies";
    private final MovieService movieService;
    private final ConversionService conversionService;

    @Autowired
    public MovieController(MovieService movieService, ConversionService conversionService){
        this.movieService = movieService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Movie movie) {
        Movie newMovie = movieService.add(movie);
        return ResponseEntity.created(EntityURLBuilder.buildURL(MOVIES_PATH, newMovie.getId())).build();
    }

    @GetMapping
    public ResponseEntity<Page<Character>> getAll(
            @And({
                    @Spec(path = "title", spec = Equal.class),
                    @Spec(path = "genre", spec = Equal.class)
            }) Specification<Movie> movieSpecification,
            Pageable pageable, @RequestParam(value = "order", required = false) String direction){

        Page p;
        if (direction == null)
            p = movieService.getAll(movieSpecification, pageable);
        else
            p = movieService.getAllSorted(pageable, direction);

        Page pageDto = p.map(movie -> conversionService.convert(movie, MovieDto.class));
        return ResponseEntityMaker.response(pageDto.getContent(), pageDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Movie updated) throws MovieNotExistsException {
        movieService.update(id, updated);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws MovieNotExistsException {
        movieService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailsDto> getDetails(@PathVariable Integer id) throws MovieNotExistsException {
        return ResponseEntity.ok(conversionService.convert(movieService.getById(id), MovieDetailsDto.class));
    }
}
