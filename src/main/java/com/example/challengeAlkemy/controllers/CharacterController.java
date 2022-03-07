package com.example.challengeAlkemy.controllers;

import com.example.challengeAlkemy.exceptions.CharacterNotExistsException;
import com.example.challengeAlkemy.models.Character;
import com.example.challengeAlkemy.models.dto.CharacterDetailsDto;
import com.example.challengeAlkemy.models.dto.CharacterDto;
import com.example.challengeAlkemy.services.CharacterService;
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
@RequestMapping("/api/characters")
public class CharacterController {

    private static final String CHARACTER_PATH = "/api/characters";
    private final CharacterService characterService;
    private final ConversionService conversionService;

    @Autowired
    public CharacterController(CharacterService characterService, ConversionService conversionService){
        this.characterService = characterService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Character character) {
        Character newCharacter = characterService.add(character);
        return ResponseEntity.created(EntityURLBuilder.buildURL(CHARACTER_PATH, newCharacter.getId())).build();
    }

    @GetMapping
    public ResponseEntity<Page<Character>> getAll(
            @And({
                    @Spec(path = "name", spec = Equal.class),
                    @Spec(path = "age", spec = Equal.class),
                    @Spec(path = "movies", spec = Equal.class)
            }) Specification<Character> characterSpecification,
            Pageable pageable){
        Page p = characterService.getAll(characterSpecification, pageable);
        Page pageDto = p.map(character -> conversionService.convert(character, CharacterDto.class));
        return ResponseEntityMaker.response(pageDto.getContent(),pageDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Character updated) throws CharacterNotExistsException {
        characterService.update(id, updated);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws CharacterNotExistsException {
        characterService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDetailsDto> getDetails(@PathVariable Integer id) throws CharacterNotExistsException {
        return ResponseEntity.ok(conversionService.convert(characterService.getById(id), CharacterDetailsDto.class));
    }
}
