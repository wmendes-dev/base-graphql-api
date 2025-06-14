package br.com.base_graphql_api.domain.dto.response;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;

import java.util.List;

public record GeneroResponse(
        Long idGenero,
        String nome,
        List<FilmePorGeneroResponse> filmes,
        List<SeriePorGeneroResponse> series,
        SituacaoEnum situacao
) {
}
