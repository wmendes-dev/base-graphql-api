package br.com.base_graphql_api.controller;

import br.com.base_graphql_api.domain.dto.request.GeneroRequest;
import br.com.base_graphql_api.domain.dto.response.GeneroResponse;
import br.com.base_graphql_api.service.GeneroService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @QueryMapping
    public GeneroResponse obterGenero(@Argument Long idGenero) {
        return this.generoService.obterGenero(idGenero);
    }

    @MutationMapping
    public GeneroResponse criarGenero(@Argument GeneroRequest generoRequest) {
        return this.generoService.criarGenero(generoRequest);
    }

}
