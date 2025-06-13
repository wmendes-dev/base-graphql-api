package br.com.base_graphql_api.domain.dto.response;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;

public record GeneroResponse(
        Long idGenero,
        String nome,
        SituacaoEnum situacao
) {
}
