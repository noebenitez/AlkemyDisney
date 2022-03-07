package com.example.challengeAlkemy.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String image;

    @NotBlank(message = "The name is required.")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Movie.class)
    @JoinTable(
            name = "genre_movie",
            joinColumns = {@JoinColumn(name = "genre_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    private List<Movie> movieList;
}
