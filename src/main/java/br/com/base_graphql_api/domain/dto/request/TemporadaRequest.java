package br.com.base_graphql_api.domain.dto.request;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;

import java.util.List;

public record TemporadaRequest(
        Integer numero,
        Long idSerie,
        List<EpisodioRequest> episodios,
        SituacaoEnum situacao
) {
}
