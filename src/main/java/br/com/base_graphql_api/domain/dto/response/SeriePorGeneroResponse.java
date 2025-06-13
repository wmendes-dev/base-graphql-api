package br.com.base_graphql_api.domain.dto.response;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;

public record SeriePorGeneroResponse(
        Long idSerie,
        String nome,
        String descricao,
        SituacaoEnum situacao
) {
}
