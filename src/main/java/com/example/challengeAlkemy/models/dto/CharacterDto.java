package com.example.challengeAlkemy.models.dto;

import com.example.challengeAlkemy.models.Character;
import com.example.challengeAlkemy.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterDto {

    private String image;
    private String name;

}
