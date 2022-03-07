package com.example.challengeAlkemy.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class GenreDto {
    private String image;
    private String name;
    private List<MovieDto> movieList;
}
