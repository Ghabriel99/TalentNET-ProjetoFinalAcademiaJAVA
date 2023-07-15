package br.com.ghabriel.ProjetoFinalBackend.repository;

import br.com.ghabriel.ProjetoFinalBackend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A interface UsuarioRepository estende o JpaRepository e herda métodos como save, findById, findAll, delete...
 * Esses métodos são usados para realizar operações de persistência no banco de dados relacionadas à entidade Usuario e seu Long ID.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca um usuário pelo nome de usuário (username).
     * @param username O nome de usuário a ser buscado.
     * @return O usuário encontrado ou nulo caso não seja encontrado.
     */
    public Usuario findByUsername (String username);
}
