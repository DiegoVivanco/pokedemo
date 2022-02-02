package com.example.pokedemo.controller;

import com.example.pokedemo.dto.Pokemon;
import com.example.pokedemo.service.PokeDemoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.example.pokedemo.PokeMocks.pokeResourceResults;
import static com.example.pokedemo.PokeMocks.pokemon;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

// Test de Integraciòn
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PokeDemoControllerTest {

  @MockBean
  private PokeDemoService pokeDemoService;

  @Autowired
  private TestRestTemplate restTemplate;

  private HttpEntity httpEntity;

  public PokeDemoControllerTest() {
  }

  @BeforeEach
  void setUp() {}

  @Test
  @Order(1)
  void whenGetIsCalled_shouldReturnPokemonList() {
    // given
    BDDMockito.given(pokeDemoService.findAllResults()).willReturn(pokeResourceResults());
    BDDMockito.given(pokeDemoService.findPokemonDetails(any())).willReturn(pokemon());
    // when
    ResponseEntity<Pokemon[]> pokemonResponse =
        restTemplate.exchange("/api/v1/pokedemo?sort=height&sortOrder=desc&size=5", HttpMethod.GET, httpEntity, Pokemon[].class);
    // then
    assertThat(pokemonResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}
