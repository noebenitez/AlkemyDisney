package com.example.challengeAlkemy.controllers.converters;

import com.example.challengeAlkemy.models.Movie;
import com.example.challengeAlkemy.models.dto.MovieDto;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MovieToMovieDTOConverter implements Converter<Movie, MovieDto> {

    private final ModelMapper modelMapper;

    public MovieToMovieDTOConverter(final ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public MovieDto convert(Movie movie) {
        return this.modelMapper.map(movie, MovieDto.class);
    }

}
