package br.com.ghabriel.ProjetoFinalBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Vaga {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vagaID;
    private String descricao;
    private String cargo;
    private String gestor;
    private BigDecimal salario;

    @OneToOne
    @JoinColumn(name = "oferta_id")
    private Oferta oferta;

    @ManyToMany
    @JoinTable(
            name = "vaga_candidato",
            joinColumns = @JoinColumn(name = "vaga_id"),
            inverseJoinColumns = @JoinColumn(name = "candidato_id")
    )
    private List<Candidato> candidatos;
}
