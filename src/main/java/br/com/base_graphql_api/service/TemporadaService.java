package br.com.base_graphql_api.service;

import br.com.base_graphql_api.domain.dto.request.TemporadaRequest;
import br.com.base_graphql_api.domain.dto.response.TemporadaResponse;
import br.com.base_graphql_api.domain.entity.Temporada;
import br.com.base_graphql_api.exception.EntidadeNaoEncontradaException;
import br.com.base_graphql_api.mapper.TemporadaMapper;
import br.com.base_graphql_api.repository.TemporadaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemporadaService {

    private final TemporadaRepository temporadaRepository;

    private final TemporadaMapper temporadaMapper;

    public List<TemporadaResponse> pesquisarTemporadas() {
        List<Temporada> temporadaList = this.temporadaRepository.findAll();
        return temporadaList.stream().map(this.temporadaMapper::converterParaTemporadaResponse).toList();
    }

    public TemporadaResponse obterTemporada(Long idTemporada) {
        Temporada temporada = obterTemporadaPorId(idTemporada);
        return this.temporadaMapper.converterParaTemporadaResponse(temporada);
    }

    @Transactional
    public TemporadaResponse criarTemporada(TemporadaRequest temporadaRequest) {
        Temporada temporada = this.temporadaMapper.converterParaTemporada(temporadaRequest);
        temporada = this.temporadaRepository.save(temporada);
        return this.temporadaMapper.converterParaTemporadaResponse(temporada);
    }

    @Transactional
    public TemporadaResponse atualizarTemporada(Long idTemporada, TemporadaRequest temporadaRequest) {
        Temporada temporada = obterTemporadaPorId(idTemporada);
        this.temporadaMapper.converterParaTemporada(temporada, temporadaRequest);
        temporada = this.temporadaRepository.save(temporada);
        return this.temporadaMapper.converterParaTemporadaResponse(temporada);
    }

    public Temporada obterTemporadaPorId(Long idTemporada) {
        return this.temporadaRepository.findById(idTemporada)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Temporada não encontrada", idTemporada));
    }

}
