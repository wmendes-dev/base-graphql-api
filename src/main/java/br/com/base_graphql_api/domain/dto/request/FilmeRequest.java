package br.com.base_graphql_api.domain.dto.request;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;

public record FilmeRequest(
        String nome,
        String descricao,
        Long idGenero,
        SituacaoEnum situacao
) {
}
