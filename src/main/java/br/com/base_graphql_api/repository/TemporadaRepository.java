package br.com.base_graphql_api.repository;

import br.com.base_graphql_api.domain.entity.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporadaRepository extends JpaRepository<Temporada, Long> {

}