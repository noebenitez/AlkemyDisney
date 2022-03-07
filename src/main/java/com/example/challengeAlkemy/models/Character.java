package com.example.challengeAlkemy.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@Entity(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String image;

    @NotBlank(message = "The character name is required.")
    private String name;

    @PositiveOrZero(message = "Age should be positive or zero.")
    private Integer age;

    @Positive(message = "Age should be a positive value.")
    private Float weight;

    private String story;

    @ManyToMany(mappedBy = "characterList", fetch = FetchType.LAZY, targetEntity = Movie.class, cascade = CascadeType.ALL)
    private List<Movie> movieList;
}
