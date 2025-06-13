package br.com.base_graphql_api.domain.dto.request;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;

public record SerieRequest(
        String nome,
        String descricao,
        Long idGenero,
        SituacaoEnum situacao
) {
}
