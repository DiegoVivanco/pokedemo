package com.example.pokedemo.service;


import com.example.pokedemo.controller.exception.ExternalServiceError;
import com.example.pokedemo.controller.exception.ExternalServiceException;
import com.example.pokedemo.dto.PokeResource;
import com.example.pokedemo.dto.PokeResourceResults;
import com.example.pokedemo.dto.Pokemon;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Slf4j
public class PokeDemoService {

    @Autowired
    private final RestTemplate template;

    @Cacheable(cacheNames = "poke_all_results")
    public PokeResourceResults findAllResults() {
        String urlRequest = null;
        try {
            urlRequest = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=1000";
            ResponseEntity<PokeResourceResults> response = template.getForEntity(urlRequest, PokeResourceResults.class);
            return response.getBody();
        } catch (HttpServerErrorException e) {
            throw new ExternalServiceException(e.getResponseBodyAsString(),
                    new ExternalServiceError("findAllResults", urlRequest, e.getResponseBodyAsString()));
        } catch (Exception e) {
            throw new ExternalServiceException(e.getMessage(),
                    new ExternalServiceError("findAllResults", urlRequest, e.getMessage()));
        }
    }

    @Cacheable(cacheNames = "pokemon", key = "#pokeResource.getName()")
    public Pokemon findPokemonDetails(PokeResource pokeResource) {
        try {
            ResponseEntity<Pokemon> results = template.getForEntity(pokeResource.getUrl(), Pokemon.class);
            return results.getBody();
        } catch (HttpServerErrorException e) {
            throw new ExternalServiceException(e.getResponseBodyAsString(),
                    new ExternalServiceError("findPokemonDetails", pokeResource.getUrl(), e.getResponseBodyAsString()));
        } catch (Exception e) {
            throw new ExternalServiceException(e.getMessage(),
                    new ExternalServiceError("findPokemonDetails", pokeResource.getUrl(), e.getMessage()));
        }
    }
}
