package com.example.challengeAlkemy.controllers.converters;

import com.example.challengeAlkemy.models.Character;
import com.example.challengeAlkemy.models.Movie;
import com.example.challengeAlkemy.models.dto.CharacterDto;
import com.example.challengeAlkemy.models.dto.MovieDetailsDto;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MovieToMovieDetailsDTOConverter implements Converter<Movie, MovieDetailsDto> {

    private final ModelMapper modelMapper;

    public MovieToMovieDetailsDTOConverter(final ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public MovieDetailsDto convert(Movie source) {
        return this.modelMapper.map(source, MovieDetailsDto.class);
    }

}
