package br.com.base_graphql_api.service;

import br.com.base_graphql_api.domain.dto.request.GeneroRequest;
import br.com.base_graphql_api.domain.dto.response.GeneroResponse;
import br.com.base_graphql_api.domain.entity.Genero;
import br.com.base_graphql_api.exception.EntidadeNaoEncontradaException;
import br.com.base_graphql_api.mapper.GeneroMapper;
import br.com.base_graphql_api.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneroService {

    private final GeneroRepository generoRepository;

    private final GeneroMapper generoMapper;

    public List<GeneroResponse> pesquisarGeneros() {
        List<Genero> generoList = this.generoRepository.findAll();
        return generoList.stream().map(this.generoMapper::converterParaGeneroResponse).toList();
    }

    public GeneroResponse obterGenero(Long idGenero) {
        Genero genero = obterGeneroPorId(idGenero);
        return this.generoMapper.converterParaGeneroResponse(genero);
    }

    @Transactional
    public GeneroResponse criarGenero(GeneroRequest generoRequest) {
        Genero genero = this.generoMapper.converterParaGenero(generoRequest);
        genero = this.generoRepository.save(genero);
        return this.generoMapper.converterParaGeneroResponse(genero);
    }

    public Genero obterGeneroPorId(Long idGenero) {
        return this.generoRepository.findById(idGenero)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado", idGenero));
    }

}
