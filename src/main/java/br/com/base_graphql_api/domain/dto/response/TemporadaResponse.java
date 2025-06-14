package br.com.base_graphql_api.domain.dto.response;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;

import java.util.List;

public record TemporadaResponse(
        Long idTemporada,
        Integer numero,
        List<EpisodioResponse> episodios,
        SituacaoEnum situacao
) {
}
