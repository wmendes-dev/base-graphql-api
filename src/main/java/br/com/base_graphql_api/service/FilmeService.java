package br.com.base_graphql_api.service;

import br.com.base_graphql_api.domain.dto.request.FilmeRequest;
import br.com.base_graphql_api.domain.dto.response.FilmeResponse;
import br.com.base_graphql_api.domain.entity.Filme;
import br.com.base_graphql_api.exception.EntidadeNaoEncontradaException;
import br.com.base_graphql_api.mapper.FilmeMapper;
import br.com.base_graphql_api.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
