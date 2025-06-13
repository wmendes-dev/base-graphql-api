package br.com.base_graphql_api.domain.dto.response;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;

public record FilmeResponse(
        Long idFilme,
        String nome,
        String descricao,
        GeneroResponse genero,
        SituacaoEnum situacao
) {
}
