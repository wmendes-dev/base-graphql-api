package br.com.base_graphql_api.repository;

import br.com.base_graphql_api.domain.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

    List<Serie> findByGeneroIdGeneroIn(List<Long> idGeneroList);

}