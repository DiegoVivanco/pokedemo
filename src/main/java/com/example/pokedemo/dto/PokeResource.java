package com.example.pokedemo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PokeResource implements Serializable {
    private String name;
    private String url;
}
