
package br.com.base_graphql_api.resolver;

import br.com.base_graphql_api.domain.dto.request.TemporadaRequest;
import br.com.base_graphql_api.domain.dto.response.TemporadaResponse;
import br.com.base_graphql_api.service.TemporadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TemporadaResolver {

    private final TemporadaService temporadaService;

    @QueryMapping
    public List<TemporadaResponse> pesquisarTemporadas() {
        return this.temporadaService.pesquisarTemporadas();
    }

    @QueryMapping
    public TemporadaResponse obterTemporada(@Argument Long idTemporada) {
        return this.temporadaService.obterTemporada(idTemporada);
    }

    @MutationMapping
    public TemporadaResponse criarTemporada(@Argument TemporadaRequest temporadaRequest) {
        return this.temporadaService.criarTemporada(temporadaRequest);
    }

    @MutationMapping
    public TemporadaResponse atualizarTemporada(@Argument Long idTemporada, @Argument TemporadaRequest temporadaRequest) {
        return this.temporadaService.atualizarTemporada(idTemporada, temporadaRequest);
    }

}
