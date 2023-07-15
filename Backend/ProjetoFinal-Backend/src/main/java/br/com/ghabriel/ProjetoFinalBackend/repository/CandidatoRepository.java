package br.com.ghabriel.ProjetoFinalBackend.repository;

import br.com.ghabriel.ProjetoFinalBackend.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Remove um candidato pelo ID.
 *  A interface CandidatoRepository estende o JpaRepository e herda métodos como save, findById, findAll, delete...
 *  Esses métodos são usados para realizar operações de persistência no banco de dados relacionadas à entidade Candidato e seu Long ID
 */
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
}
