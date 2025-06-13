package br.com.base_graphql_api.mapper;

import br.com.base_graphql_api.domain.dto.request.GeneroRequest;
import br.com.base_graphql_api.domain.dto.response.GeneroResponse;
import br.com.base_graphql_api.domain.entity.Genero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    @Mapping(target = "filmes", ignore = true)
    GeneroResponse converterParaGeneroResponse(Genero genero);

    Genero converterParaGenero(GeneroRequest generoRequest);

}