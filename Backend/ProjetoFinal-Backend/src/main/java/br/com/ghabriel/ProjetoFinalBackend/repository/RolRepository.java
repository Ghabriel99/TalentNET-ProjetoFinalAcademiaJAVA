package br.com.ghabriel.ProjetoFinalBackend.repository;

import br.com.ghabriel.ProjetoFinalBackend.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A interface RolRepository estende o JpaRepository e herda métodos como save, findById, findAll, delete...
 * Esses métodos são usados para realizar operações de persistência no banco de dados relacionadas à entidade Rol e seu Long ID.
 */
public interface RolRepository extends JpaRepository<Rol, Long> {
}
