package br.com.base_graphql_api.mapper;

import br.com.base_graphql_api.domain.dto.request.SerieRequest;
import br.com.base_graphql_api.domain.dto.response.SeriePorGeneroResponse;
import br.com.base_graphql_api.domain.dto.response.SerieResponse;
import br.com.base_graphql_api.domain.entity.Genero;
import br.com.base_graphql_api.domain.entity.Serie;
import br.com.base_graphql_api.service.GeneroService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class SerieMapper {

    @Autowired
    private GeneroService generoService;

    public abstract SerieResponse converterParaSerieResponse(Serie serie);

    public abstract SeriePorGeneroResponse converterParaSeriePorGeneroResponse(Serie serie);

    @Mapping(target = "genero", expression = "java(converterParaGenero(serieRequest.idGenero()))")
    public abstract Serie converterParaSerie(SerieRequest serieRequest);

    @Mapping(target = "genero", expression = "java(converterParaGenero(serieRequest.idGenero()))")
    public abstract void converterParaSerie(@MappingTarget Serie serie, SerieRequest serieRequest);

    public Genero converterParaGenero(Long idGenero) {
        return this.generoService.obterGeneroPorId(idGenero);
    }

}