package com.example.pokedemo;

import com.example.pokedemo.dto.PokeResource;
import com.example.pokedemo.dto.PokeResourceResults;
import com.example.pokedemo.dto.Pokemon;

import java.util.Collections;

public class PokeMocks {

    public static PokeResource pokeResource() {
        PokeResource pokeResource = new PokeResource();
        pokeResource.setUrl("/pokemon/1");
        pokeResource.setName("pruebas");
        return pokeResource;
    }

    public static PokeResourceResults pokeResourceResults() {
        PokeResourceResults pokeResourceResults = new PokeResourceResults();
        pokeResourceResults.setResults(Collections.singletonList(pokeResource()));
        return pokeResourceResults;
    }

    public static Pokemon pokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("pokemon");
        return pokemon;
    }

}
