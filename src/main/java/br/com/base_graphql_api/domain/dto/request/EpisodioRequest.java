package br.com.base_graphql_api.domain.dto.request;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;

public record EpisodioRequest(
        Long idEpisodio,
        Integer numero,
        String nome,
        String descricao,
        SituacaoEnum situacao
) {
}
