package br.com.base_graphql_api.domain.entity;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "TB_TEMPORADA")
public class Temporada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TEMPORADA")
    private Long idTemporada;

    @Column(name = "NUMERO", nullable = false)
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "ID_SERIE", nullable = false)
    private Serie serie;

    @OneToMany(mappedBy = "temporada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Episodio> episodios;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoEnum situacao;

    public Temporada() {
        this.situacao = SituacaoEnum.ATIVO;
    }

}
