package br.com.ghabriel.ProjetoFinalBackend.repository;

import br.com.ghabriel.ProjetoFinalBackend.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A interface VagaRepository estende o JpaRepository e herda métodos como save, findById, findAll, delete...
 * Esses métodos são usados para realizar operações de persistência no banco de dados relacionadas à entidade Vaga.
 */
public interface VagaRepository extends JpaRepository<Vaga, Long> {
}
