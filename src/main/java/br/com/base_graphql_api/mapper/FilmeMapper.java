package br.com.base_graphql_api.mapper;

import br.com.base_graphql_api.domain.dto.request.FilmeRequest;
import br.com.base_graphql_api.domain.dto.response.FilmeResponse;
import br.com.base_graphql_api.domain.entity.Filme;
import br.com.base_graphql_api.domain.entity.Genero;
import br.com.base_graphql_api.service.GeneroService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class FilmeMapper {

    @Autowired
    private GeneroService generoService;

//    public abstract FilmePesquisaResponse converterParaFilmePesquisaResponse(Filme filme);

    public abstract FilmeResponse converterParaFilmeResponse(Filme filme);

    @Mapping(target = "genero", expression = "java(converterParaGenero(filmeRequest.idGenero()))")
    public abstract Filme converterParaFilme(FilmeRequest filmeRequest);

    @Mapping(target = "genero", expression = "java(converterParaGenero(filmeRequest.idGenero()))")
    public abstract void converterParaFilme(@MappingTarget Filme filme, FilmeRequest filmeRequest);

    public Genero converterParaGenero(Long idGenero) {
        return this.generoService.obterGeneroPorId(idGenero);
    }

}