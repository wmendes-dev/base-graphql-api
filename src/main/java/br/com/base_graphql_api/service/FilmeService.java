package br.com.base_graphql_api.service;

import br.com.base_graphql_api.domain.dto.request.FilmeRequest;
import br.com.base_graphql_api.domain.dto.response.FilmePorGeneroResponse;
import br.com.base_graphql_api.domain.dto.response.FilmeResponse;
import br.com.base_graphql_api.domain.dto.response.GeneroResponse;
import br.com.base_graphql_api.domain.entity.Filme;
import br.com.base_graphql_api.exception.EntidadeNaoEncontradaException;
import br.com.base_graphql_api.mapper.FilmeMapper;
import br.com.base_graphql_api.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;

    private final FilmeMapper filmeMapper;

    public List<FilmeResponse> pesquisarFilmes() {
        List<Filme> filmeList = this.filmeRepository.findAll();
        return filmeList.stream().map(this.filmeMapper::converterParaFilmeResponse).toList();
    }

    public FilmeResponse obterFilme(Long idFilme) {
        Filme filme = obterFilmePorId(idFilme);
        return this.filmeMapper.converterParaFilmeResponse(filme);
    }

    @Transactional
    public FilmeResponse criarFilme(FilmeRequest filmeRequest) {
        Filme filme = this.filmeMapper.converterParaFilme(filmeRequest);
        filme = this.filmeRepository.save(filme);
        return this.filmeMapper.converterParaFilmeResponse(filme);
    }

    public Filme obterFilmePorId(Long idFilme) {
        return this.filmeRepository.findById(idFilme)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme não encontrado", idFilme));
    }

    public Map<GeneroResponse, List<FilmePorGeneroResponse>> mapearFilmesPorGenero(List<GeneroResponse> generoResponseList) {
        List<Long> idGeneroList = generoResponseList.stream()
                .map(GeneroResponse::idGenero)
                .toList();

        List<Filme> filmeList = this.filmeRepository.findByGeneroIdGeneroIn(idGeneroList);

        Map<Long, List<Filme>> filmeListPorIdGenero = filmeList.stream()
                .collect(Collectors.groupingBy(f -> f.getGenero().getIdGenero()));

        return generoResponseList.stream()
                .collect(Collectors.toMap(
                        genero -> genero,
                        genero -> filmeListPorIdGenero.getOrDefault(genero.idGenero(), Collections.emptyList())
                                .stream()
                                .map(this.filmeMapper::converterParaFilmePorGeneroResponse)
                                .toList()
                ));
    }

}
