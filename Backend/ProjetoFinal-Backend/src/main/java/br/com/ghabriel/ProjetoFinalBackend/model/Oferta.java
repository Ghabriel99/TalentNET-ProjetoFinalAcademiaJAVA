package br.com.ghabriel.ProjetoFinalBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Oferta {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ofertaID;
    private String descricao;

    @ManyToMany(mappedBy = "ofertas")
    private List<Candidato> candidatos;

    @OneToOne(mappedBy = "oferta")
    private Vaga vaga;

}

