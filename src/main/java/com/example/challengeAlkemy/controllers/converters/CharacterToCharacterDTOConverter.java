package com.example.challengeAlkemy.controllers.converters;

import com.example.challengeAlkemy.models.Character;
import com.example.challengeAlkemy.models.dto.CharacterDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CharacterToCharacterDTOConverter implements Converter<Character, CharacterDto> {

    private final ModelMapper modelMapper;

    public CharacterToCharacterDTOConverter(final ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public CharacterDto convert(Character source) {
        return this.modelMapper.map(source, CharacterDto.class);
    }
}
