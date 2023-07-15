package br.com.ghabriel.ProjetoFinalBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidade Rol (permissão) dos usuários.
 */
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    @Id
    private Long rolId;
    private String rolNome;

    /**
     * Relacionamento um pra muitos entre a Role(permissão) e os usuários.
     * Utiliza um conjunto (Set) para evitar duplicatas e permitir a busca eficiente de associações.
     * ele verifica se o objeto já existe com base em seu valor de hash e no método equals().
     * Isso garante que não haja duplicatas na lista de associações entre as Roles e os Usuários.
     */
    @OneToMany
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

}
