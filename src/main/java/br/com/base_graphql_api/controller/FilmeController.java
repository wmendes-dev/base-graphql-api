
package br.com.base_graphql_api.controller;

import br.com.base_graphql_api.domain.dto.request.FilmeRequest;
import br.com.base_graphql_api.domain.dto.response.FilmeResponse;
import br.com.base_graphql_api.service.FilmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilmeController {

    private final FilmeService filmeService;

    @QueryMapping
    public List<FilmeResponse> pesquisarFilmes() {
        return this.filmeService.pesquisarFilmes();
    }

    @QueryMapping
    public FilmeResponse obterFilme(@Argument Long idFilme) {
        return this.filmeService.obterFilme(idFilme);
    }

    @MutationMapping
    public FilmeResponse criarFilme(@Argument FilmeRequest filmeRequest) {
        return this.filmeService.criarFilme(filmeRequest);
    }

}
