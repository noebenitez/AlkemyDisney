package com.example.challengeAlkemy.models.dto;

import com.example.challengeAlkemy.models.Character;
import com.example.challengeAlkemy.models.Genre;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
public class MovieDetailsDto {

    private Integer id;
    private String image;
    private String title;
    private LocalDate releaseDate;
    private Integer rating;
    private List<CharacterDto> characterList;
    private List<GenreDto> genres;

}
