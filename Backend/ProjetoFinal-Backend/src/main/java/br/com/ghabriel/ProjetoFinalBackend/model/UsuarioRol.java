package br.com.ghabriel.ProjetoFinalBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioRolId;

    /**
     * Relacionamento Muitos pra um, com a entidade Usuario.
     * Cada UsuarioRol(permissão) está associado a um único Usuario.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    /**
     * Relacionamento Muitos pra um, com a entidade Rol.
     * Cada UsuarioRol está associado a uma única Rol.
     */
    @ManyToOne
    private Rol rol;

}
