package com.example.challengeAlkemy.services;

import com.example.challengeAlkemy.models.Character;
import com.example.challengeAlkemy.models.Genre;
import com.example.challengeAlkemy.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    public Genre add(Genre genre){
        return genreRepository.save(genre);
    }

    public Page getAll(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }
}
