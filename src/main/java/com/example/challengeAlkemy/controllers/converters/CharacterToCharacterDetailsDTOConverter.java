package com.example.challengeAlkemy.controllers.converters;

import com.example.challengeAlkemy.models.Character;
import com.example.challengeAlkemy.models.dto.CharacterDetailsDto;
import com.example.challengeAlkemy.models.dto.CharacterDto;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CharacterToCharacterDetailsDTOConverter implements Converter<Character, CharacterDetailsDto> {

    private final ModelMapper modelMapper;

    public CharacterToCharacterDetailsDTOConverter(final ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public CharacterDetailsDto convert(Character source) {
        return this.modelMapper.map(source, CharacterDetailsDto.class);
    }
}
