package com.example.challengeAlkemy.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "movies")
public class Movie {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String image;

    @NotBlank(message = "The title is required.")
    private String title;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Min(value = 0, message = "The rating cannot be less than 0.")
    @Max(value = 5, message = "The rating cannot be greater than 5.")
    private Integer rating;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Character.class)
    @JoinTable(name = "movies_characters",
            joinColumns = {
                    @JoinColumn(name = "movie_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "character_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private List<Character> characterList;

    @ManyToMany(mappedBy = "movieList", fetch = FetchType.LAZY, targetEntity = Genre.class, cascade = CascadeType.ALL)
    private List<Genre> genres;
}
