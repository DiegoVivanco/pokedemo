package com.example.pokedemo.service;

import com.example.pokedemo.dto.PokeResource;
import com.example.pokedemo.dto.PokeResourceResults;
import com.example.pokedemo.dto.Pokemon;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PokeDemoFinderService {

    private final PokeDemoService pokeDemoService;

    public List<Pokemon> findAllWithFilter(String sort, String sortOrder, Integer size) {

        PokeResourceResults pokeResourceResults = pokeDemoService.findAllResults();
        List<PokeResource> pokeResources = pokeResourceResults.getResults();

        List<Pokemon> pokemonList = pokeResources.parallelStream()
                .map(pokeDemoService::findPokemonDetails).collect(Collectors.toList());

        Map<String, Comparator<Pokemon>> pokemonComparatorMap =  Map.of(
                "height", Comparator.comparingInt(Pokemon::getHeight),
                "weight", Comparator.comparingInt(Pokemon::getWeight),
                "baseExperience", Comparator.comparingInt(Pokemon::getBaseExperience)
                );

        Comparator<Pokemon> comparator = pokemonComparatorMap.containsKey(sort)
                ? pokemonComparatorMap.get(sort) : Comparator.comparing(Pokemon::getName);

        if("desc".equals(sortOrder)){
            comparator = comparator.reversed();
        }

        return pokemonList.stream()
                .sorted(comparator)
                .limit(size != null ? size : pokeResourceResults.getCount())
                .collect(Collectors.toList());
    }
}
