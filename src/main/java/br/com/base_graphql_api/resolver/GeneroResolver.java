package br.com.base_graphql_api.resolver;

import br.com.base_graphql_api.domain.dto.request.GeneroRequest;
import br.com.base_graphql_api.domain.dto.response.FilmePorGeneroResponse;
import br.com.base_graphql_api.domain.dto.response.GeneroResponse;
import br.com.base_graphql_api.service.FilmeService;
import br.com.base_graphql_api.service.GeneroService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class GeneroResolver {

    private final GeneroService generoService;

    private final FilmeService filmeService;

    @QueryMapping
    public List<GeneroResponse> pesquisarGeneros() {
        return this.generoService.pesquisarGeneros();
    }

    @QueryMapping
    public GeneroResponse obterGenero(@Argument Long idGenero) {
        return this.generoService.obterGenero(idGenero);
    }

    @MutationMapping
    public GeneroResponse criarGenero(@Argument GeneroRequest generoRequest) {
        return this.generoService.criarGenero(generoRequest);
    }

    @MutationMapping
    public GeneroResponse atualizarGenero(@Argument Long idGenero, @Argument GeneroRequest generoRequest) {
        return this.generoService.atualizarGenero(idGenero, generoRequest);
    }

    @BatchMapping(field = "filmes")
    public Map<GeneroResponse, List<FilmePorGeneroResponse>> mapearFilmesPorGenero(List<GeneroResponse> generoResponseList) {
        return this.filmeService.mapearFilmesPorGenero(generoResponseList);
    }

}
