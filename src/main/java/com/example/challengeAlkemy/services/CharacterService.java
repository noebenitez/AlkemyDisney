package com.example.challengeAlkemy.services;

import com.example.challengeAlkemy.exceptions.CharacterNotExistsException;
import com.example.challengeAlkemy.models.Character;
import com.example.challengeAlkemy.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    public Character add(Character character){
        return characterRepository.save(character);
    }

    public Page getAll(Specification<Character> characterSpecification, Pageable pageable) {
        return characterRepository.findAll(characterSpecification, pageable);
    }

    public Character getById(Integer id) throws CharacterNotExistsException {
        return characterRepository.findById(id)
                .orElseThrow(CharacterNotExistsException::new);
    }

    public void update(Integer id, Character updated) throws CharacterNotExistsException {
        Character character = this.getById(id);
        character.setImage(updated.getImage());
        character.setName(updated.getName());
        character.setAge(updated.getAge());
        character.setWeight(updated.getWeight());
        character.setStory(updated.getStory());
        character.setMovieList(character.getMovieList());

        characterRepository.save(character);
    }

    public void delete(Integer id) throws CharacterNotExistsException {
        if (characterRepository.existsById(id)){
            characterRepository.deleteById(id);
        }else{
            throw new CharacterNotExistsException();
        }
    }
}
