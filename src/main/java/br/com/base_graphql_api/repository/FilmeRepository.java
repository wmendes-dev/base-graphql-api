package br.com.base_graphql_api.repository;

import br.com.base_graphql_api.domain.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findByGeneroIdGeneroIn(List<Long> idGeneroList);

}