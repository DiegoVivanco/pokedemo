package com.example.pokedemo.service;

import com.example.pokedemo.dto.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.example.pokedemo.PokeMocks.pokeResource;
import static com.example.pokedemo.PokeMocks.pokeResourceResults;
import static com.example.pokedemo.PokeMocks.pokemon;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class PokeDemoFinderServiceTest {

  @Mock
  private PokeDemoService pokeDemoService;

  @InjectMocks
  private PokeDemoFinderService pokeDemoFinderService;

  public PokeDemoFinderServiceTest() {
  }

  @BeforeEach
  void setUp() {}

  @Test
  @Order(1)
  void shouldReturnPokemonListObject() {
    Mockito.when(pokeDemoService.findAllResults()).thenReturn(pokeResourceResults());
    Mockito.when(pokeDemoService.findPokemonDetails(pokeResource())).thenReturn(pokemon());

    List<Pokemon> pokemons = pokeDemoFinderService.findAllWithFilter("height","asc",10);
    assertEquals(pokemon(), pokemons.get(0));
  }
}
