package com.example.pokedemo.service;

import com.example.pokedemo.dto.PokeResourceResults;
import com.example.pokedemo.dto.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static com.example.pokedemo.PokeMocks.pokeResource;
import static com.example.pokedemo.PokeMocks.pokeResourceResults;
import static com.example.pokedemo.PokeMocks.pokemon;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class PokeDemoServiceTest {

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private PokeDemoService pokeDemoService;

  public PokeDemoServiceTest() {
  }

  @BeforeEach
  void setUp() {}

  @Test
  @Order(1)
  void whenGetIsCalled_shouldReturnPokeResourceResultsObject() {
    Mockito
            .when(restTemplate.getForEntity(
                    "https://pokeapi.co/api/v2/pokemon?offset=0&limit=1000", PokeResourceResults.class))
          .thenReturn(new ResponseEntity(pokeResourceResults(), HttpStatus.OK));

    PokeResourceResults pokeResourceResults = pokeDemoService.findAllResults();
    assertEquals(pokeResourceResults(), pokeResourceResults);
  }

  @Test
  @Order(2)
  void whenGetIsCalled_shouldReturnPokemonObject() {
    Mockito
            .when(restTemplate.getForEntity(
                    pokeResource().getUrl(), Pokemon.class))
            .thenReturn(new ResponseEntity(pokemon(), HttpStatus.OK));

    Pokemon pokemon = pokeDemoService.findPokemonDetails(pokeResource());
    assertEquals(pokemon(), pokemon);
  }
}
