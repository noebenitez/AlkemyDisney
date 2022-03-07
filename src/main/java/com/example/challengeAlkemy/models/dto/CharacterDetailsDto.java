package com.example.challengeAlkemy.models.dto;

import com.example.challengeAlkemy.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDetailsDto {

    private Integer id;
    private String image;
    private String name;
    private Integer age;
    private Float weight;
    private String story;
    private List<MovieDto> movieList;
}
