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
public class Candidato {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidatoID;
    private String nome;
    private Integer idade;
    private String cpf;
    private String email;
    private String telefone;
    private boolean habilitado = true;

    @ManyToMany
    @JoinTable(
            name = "candidato_oferta",
            joinColumns = @JoinColumn(name = "candidato_id"),
            inverseJoinColumns = @JoinColumn(name = "oferta_id")
    )
    private List<Oferta> ofertas;

    @ManyToMany(mappedBy = "candidatos")
    private List<Vaga> vagas;


}

