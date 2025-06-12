package br.com.base_graphql_api.domain.entity;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_EPISODIO")
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EPISODIO")
    private Long idEpisodio;

    @Column(name = "NUMERO", nullable = false)
    private Integer numero;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "ID_TEMPORADA", nullable = false)
    private Temporada temporada;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoEnum situacao;

    public Episodio() {
        this.situacao = SituacaoEnum.ATIVO;
    }

}
