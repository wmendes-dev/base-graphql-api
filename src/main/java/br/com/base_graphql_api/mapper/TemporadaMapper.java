package br.com.base_graphql_api.mapper;

import br.com.base_graphql_api.domain.dto.request.EpisodioRequest;
import br.com.base_graphql_api.domain.dto.request.TemporadaRequest;
import br.com.base_graphql_api.domain.dto.response.TemporadaResponse;
import br.com.base_graphql_api.domain.entity.Episodio;
import br.com.base_graphql_api.domain.entity.Serie;
import br.com.base_graphql_api.domain.entity.Temporada;
import br.com.base_graphql_api.service.SerieService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TemporadaMapper {

    @Autowired
    private SerieService serieService;

    public abstract TemporadaResponse converterParaTemporadaResponse(Temporada temporada);

    @Mapping(target = "serie", expression = "java(converterParaSerie(temporadaRequest.idSerie()))")
    @Mapping(target = "episodios", expression = "java(converterParaEpisodioList(temporada, temporadaRequest.episodios()))")
    public abstract Temporada converterParaTemporada(TemporadaRequest temporadaRequest);

    @Mapping(target = "serie", expression = "java(converterParaSerie(temporadaRequest.idSerie()))")
    @Mapping(target = "episodios", expression = "java(converterParaEpisodioList(temporada, temporadaRequest.episodios()))")
    public abstract void converterParaTemporada(@MappingTarget Temporada temporada, TemporadaRequest temporadaRequest);

    public Serie converterParaSerie(Long idSerie) {
        return this.serieService.obterSeriePorId(idSerie);
    }

    public List<Episodio> converterParaEpisodioList(Temporada temporada, List<EpisodioRequest> episodioRequestList) {
        if (episodioRequestList == null) return Collections.emptyList();

        List<Episodio> episodioList = new ArrayList<>();
        for (EpisodioRequest episodioRequest : episodioRequestList) {
            Episodio episodio = converterParaEpisodio(episodioRequest);
            episodio.setTemporada(temporada);
            episodioList.add(episodio);
        }

        return episodioList;
    }

    public abstract Episodio converterParaEpisodio(EpisodioRequest episodioRequest);

}