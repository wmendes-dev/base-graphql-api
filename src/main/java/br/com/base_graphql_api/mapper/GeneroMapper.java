package br.com.base_graphql_api.mapper;

import br.com.base_graphql_api.domain.dto.request.GeneroRequest;
import br.com.base_graphql_api.domain.dto.response.GeneroResponse;
import br.com.base_graphql_api.domain.entity.Genero;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

//    GeneroPesquisaResponse converterParaGeneroPesquisaResponse(Genero genero);
//
    GeneroResponse converterParaGeneroResponse(Genero genero);

    Genero converterParaGenero(GeneroRequest generoRequest);

//    void converterParaGenero(@MappingTarget Genero genero, GeneroRequest generoRequest);

}