package com.example.challengeAlkemy.controllers.converters;

import com.example.challengeAlkemy.models.Character;
import com.example.challengeAlkemy.models.Genre;
import com.example.challengeAlkemy.models.dto.CharacterDto;
import com.example.challengeAlkemy.models.dto.GenreDto;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GenreToGenreDTOConverter implements Converter<Genre, GenreDto> {

    private final ModelMapper modelMapper;

    public GenreToGenreDTOConverter(final ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public GenreDto convert(Genre source) {
        return this.modelMapper.map(source, GenreDto.class);
    }
}
