
package br.com.base_graphql_api.resolver;

import br.com.base_graphql_api.domain.dto.request.SerieRequest;
import br.com.base_graphql_api.domain.dto.response.SerieResponse;
import br.com.base_graphql_api.service.SerieService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SerieResolver {

    private final SerieService serieService;

    @QueryMapping
    public List<SerieResponse> pesquisarSeries() {
        return this.serieService.pesquisarSeries();
    }

    @QueryMapping
    public SerieResponse obterSerie(@Argument Long idSerie) {
        return this.serieService.obterSerie(idSerie);
    }

    @MutationMapping
    public SerieResponse criarSerie(@Argument SerieRequest serieRequest) {
        return this.serieService.criarSerie(serieRequest);
    }

    @MutationMapping
    public SerieResponse atualizarSerie(@Argument Long idSerie, @Argument SerieRequest serieRequest) {
        return this.serieService.atualizarSerie(idSerie, serieRequest);
    }

}
