package br.com.base_graphql_api.mapper;

import br.com.base_graphql_api.domain.dto.request.EpisodioRequest;
import br.com.base_graphql_api.domain.dto.request.SerieRequest;
import br.com.base_graphql_api.domain.dto.request.TemporadaRequest;
import br.com.base_graphql_api.domain.dto.response.SeriePorGeneroResponse;
import br.com.base_graphql_api.domain.dto.response.SerieResponse;
import br.com.base_graphql_api.domain.entity.Episodio;
import br.com.base_graphql_api.domain.entity.Genero;
import br.com.base_graphql_api.domain.entity.Serie;
import br.com.base_graphql_api.domain.entity.Temporada;
import br.com.base_graphql_api.service.GeneroService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SerieMapper {

    @Autowired
    private GeneroService generoService;

    public abstract SerieResponse converterParaSerieResponse(Serie serie);

    public abstract SeriePorGeneroResponse converterParaSeriePorGeneroResponse(Serie serie);

    @Mapping(target = "genero", expression = "java(converterParaGenero(serieRequest.idGenero()))")
    @Mapping(target = "temporadas", expression = "java(converterParaTemporadaList(serie, serieRequest.temporadas()))")
    public abstract Serie converterParaSerie(SerieRequest serieRequest);

    public void converterParaSerie(Serie serie, SerieRequest serieRequest) {
        if (serieRequest == null) return;

        serie.setNome(serieRequest.nome());
        serie.setDescricao(serieRequest.descricao());
        serie.setGenero(converterParaGenero(serieRequest.idGenero()));
        serie.setTemporadas(converterParaTemporadaList(serie, serieRequest.temporadas()));
    }

    public Genero converterParaGenero(Long idGenero) {
        return this.generoService.obterGeneroPorId(idGenero);
    }

    public List<Temporada> converterParaTemporadaList(Serie serie, List<TemporadaRequest> temporadaRequestList) {
        if (temporadaRequestList == null) return Collections.emptyList();

        List<Temporada> temporadaList = new ArrayList<>();
        for (TemporadaRequest temporadaRequest : temporadaRequestList) {
            Temporada temporada = converterParaTemporada(temporadaRequest);
            temporada.setSerie(serie);
            temporada.setEpisodios(converterParaEpisodioList(temporada, temporadaRequest.episodios()));
            temporadaList.add(temporada);
        }

        return temporadaList;
    }

    @Mapping(target = "episodios", expression = "java(converterParaEpisodioList(temporada, temporadaRequest.episodios()))")
    public abstract Temporada converterParaTemporada(TemporadaRequest temporadaRequest);

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