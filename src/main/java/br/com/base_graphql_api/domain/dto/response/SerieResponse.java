package br.com.base_graphql_api.domain.dto.response;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;

import java.util.List;

public record SerieResponse(
        Long idSerie,
        String nome,
        String descricao,
        GeneroResponse genero,
        List<TemporadaResponse> temporadas,
        SituacaoEnum situacao
) {
}
