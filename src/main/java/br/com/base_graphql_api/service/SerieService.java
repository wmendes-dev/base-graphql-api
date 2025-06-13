package br.com.base_graphql_api.service;

import br.com.base_graphql_api.domain.dto.request.SerieRequest;
import br.com.base_graphql_api.domain.dto.response.SeriePorGeneroResponse;
import br.com.base_graphql_api.domain.dto.response.SerieResponse;
import br.com.base_graphql_api.domain.dto.response.GeneroResponse;
import br.com.base_graphql_api.domain.entity.Serie;
import br.com.base_graphql_api.exception.EntidadeNaoEncontradaException;
import br.com.base_graphql_api.mapper.SerieMapper;
import br.com.base_graphql_api.repository.SerieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SerieService {

    private final SerieRepository serieRepository;

    private final SerieMapper serieMapper;

    public List<SerieResponse> pesquisarSeries() {
        List<Serie> serieList = this.serieRepository.findAll();
        return serieList.stream().map(this.serieMapper::converterParaSerieResponse).toList();
    }

    public SerieResponse obterSerie(Long idSerie) {
        Serie serie = obterSeriePorId(idSerie);
        return this.serieMapper.converterParaSerieResponse(serie);
    }

    @Transactional
    public SerieResponse criarSerie(SerieRequest serieRequest) {
        Serie serie = this.serieMapper.converterParaSerie(serieRequest);
        serie = this.serieRepository.save(serie);
        return this.serieMapper.converterParaSerieResponse(serie);
    }

    @Transactional
    public SerieResponse atualizarSerie(Long idSerie, SerieRequest serieRequest) {
        Serie serie = obterSeriePorId(idSerie);
        this.serieMapper.converterParaSerie(serie, serieRequest);
        serie = this.serieRepository.save(serie);
        return this.serieMapper.converterParaSerieResponse(serie);
    }

    public Serie obterSeriePorId(Long idSerie) {
        return this.serieRepository.findById(idSerie)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Serie não encontrado", idSerie));
    }

    public Map<GeneroResponse, List<SeriePorGeneroResponse>> mapearSeriesPorGenero(List<GeneroResponse> generoResponseList) {
        List<Long> idGeneroList = generoResponseList.stream()
                .map(GeneroResponse::idGenero)
                .toList();

        List<Serie> serieList = this.serieRepository.findByGeneroIdGeneroIn(idGeneroList);

        Map<Long, List<Serie>> serieListPorIdGenero = serieList.stream()
                .collect(Collectors.groupingBy(f -> f.getGenero().getIdGenero()));

        return generoResponseList.stream()
                .collect(Collectors.toMap(
                        genero -> genero,
                        genero -> serieListPorIdGenero.getOrDefault(genero.idGenero(), Collections.emptyList())
                                .stream()
                                .map(this.serieMapper::converterParaSeriePorGeneroResponse)
                                .toList()
                ));
    }

}
