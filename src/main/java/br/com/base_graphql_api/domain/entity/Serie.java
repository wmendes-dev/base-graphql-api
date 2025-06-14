package br.com.base_graphql_api.domain.entity;

import br.com.base_graphql_api.domain.enums.SituacaoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "TB_SERIE")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SERIE")
    private Long idSerie;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "ID_GENERO", nullable = false)
    private Genero genero;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Temporada> temporadas;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoEnum situacao;

    public Serie() {
        this.situacao = SituacaoEnum.ATIVO;
    }

}
