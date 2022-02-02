package com.example.pokedemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Pokemon implements Serializable {
    String name;
    @JsonProperty("base_experience")
    int baseExperience;
    int height;
    int weight;
}
