package com.example.pokedemo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PokeResourceResults implements Serializable {
    private int count;
    private String next;
    private String previous;
    private List<PokeResource> results;
}
