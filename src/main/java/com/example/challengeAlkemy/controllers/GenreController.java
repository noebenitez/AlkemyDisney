package com.example.challengeAlkemy.controllers;

import com.example.challengeAlkemy.models.Character;
import com.example.challengeAlkemy.models.Genre;
import com.example.challengeAlkemy.models.dto.GenreDto;
import com.example.challengeAlkemy.models.dto.MovieDto;
import com.example.challengeAlkemy.services.GenreService;
import com.example.challengeAlkemy.utils.EntityURLBuilder;
import com.example.challengeAlkemy.utils.ResponseEntityMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private static final String GENRE_PATH= "/api/genres";
    private final GenreService genreService;
    private final ConversionService conversionService;

    @Autowired
    public GenreController(GenreService genreService, ConversionService conversionService){
        this.genreService = genreService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Genre genre) {
        Genre newGenre = genreService.add(genre);
        return ResponseEntity.created(EntityURLBuilder.buildURL(GENRE_PATH, newGenre.getId())).build();
    }

    @GetMapping
    public ResponseEntity<Page<GenreDto>> getAll(Pageable pageable){
        Page p = genreService.getAll(pageable);
        Page pageDto = p.map(genre -> conversionService.convert(genre, GenreDto.class));
        return ResponseEntityMaker.response(pageDto.getContent(),pageDto);
    }
}
