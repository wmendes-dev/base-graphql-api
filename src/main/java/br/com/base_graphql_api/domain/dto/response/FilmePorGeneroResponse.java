package br.com.base_graphql_api.domain.dto.response;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;

public record FilmePorGeneroResponse(
        Long idFilme,
        String nome,
        String descricao,
        SituacaoEnum situacao
) {
}
