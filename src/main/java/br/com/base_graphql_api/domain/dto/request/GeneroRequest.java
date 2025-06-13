package br.com.base_graphql_api.domain.dto.request;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;

public record GeneroRequest(
        String nome,
        SituacaoEnum situacao
) {
}
