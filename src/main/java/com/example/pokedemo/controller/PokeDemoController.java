package com.example.pokedemo.controller;

import com.example.pokedemo.controller.exception.BadRequestException;
import com.example.pokedemo.dto.Pokemon;
import com.example.pokedemo.service.PokeDemoFinderService;
import com.example.pokedemo.utils.ApplicationConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/pokedemo")
@Tag(name = ApplicationConstants.POKE_TITLE, description = ApplicationConstants.POKE_DESCRIPTION)
public class PokeDemoController {

    private final PokeDemoFinderService pokeDemoFinderService;

    @GetMapping()
    @Operation(summary = ApplicationConstants.POKE_GET)
    public List<Pokemon> findPokemonsWithFilters(
            @Parameter(name =  "sort", example = "height", required = true)
            @QueryParam("sort") String sort,
            @Parameter(name =  "sortOrder", example = "desc", required = true)
            @QueryParam("sortOrder")String sortOrder,
            @Parameter(name =  "size", example = "5")
            @QueryParam("size") Integer size) {
        if (sort == null || sort.isEmpty() || sort.isBlank())
            throw new BadRequestException(ApplicationConstants.POKE_SORT_REQUIRED);
        if (sortOrder == null || sortOrder.isEmpty() || sortOrder.isBlank())
            throw new BadRequestException(ApplicationConstants.POKE_SORT_ORDER_REQUIRED);
        return this.pokeDemoFinderService.findAllWithFilter(sort, sortOrder, size);
    }
}
