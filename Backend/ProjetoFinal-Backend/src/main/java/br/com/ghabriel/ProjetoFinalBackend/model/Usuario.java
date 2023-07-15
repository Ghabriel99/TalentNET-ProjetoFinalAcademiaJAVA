package br.com.ghabriel.ProjetoFinalBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa a entidade de Usuário no sistema.
 */
@Entity  //mapear a classe Usuário para uma tabela no banco de dados, entidade persistente.
@Data // gera métodos getters, setters, toString...
@NoArgsConstructor //gera um construtor sem argumentos para a classe
@AllArgsConstructor  //gera um cons trutor com todos argumentos para inicializar todos os campos da classe.
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "usuarios")  // define o nome da tabela como usuarios no banco de dados.
public class Usuario implements UserDetails {

    /**
     * Chave primária Id AutoIncrement do Usuario;.
     */
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nome;
    private String email;
    private String telefone;
    private boolean enabled = true;
    private String perfil;

    /**
     * Cardinalidade um pra muitos entre Usuario e UsuarioRol
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
    @JsonIgnore
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    /**
     * Retorna a lista de autoridades (roles) do usuário.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> autoridades = new HashSet<>();
        this.usuarioRoles.forEach(usuarioRol -> {
            autoridades.add(new Authority(usuarioRol.getRol().getRolNome()));
        });
        return autoridades;
    }

    /**
     * Retorna a senha do usuário.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Retorna o username cadastrado do usuário.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Define o username do usuário.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Define a senha do usuário.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * retorna true para a conta não expirada.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * retorna true para a conta não bloqueada.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Verifica se as credenciais do usuário não estão expiradas.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Verifica se o usuário está habilitado.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Define o perfil do usuário.
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }


    /**
     * Retorna a lista de associações entre o usuário e as sua roles.
     */
    public Set<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }

}
