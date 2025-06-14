package br.com.base_graphql_api.domain.dto.response;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;

public record EpisodioResponse(
        Long idEpisodio,
        Integer numero,
        String nome,
        String descricao,
        SituacaoEnum situacao
) {
}
