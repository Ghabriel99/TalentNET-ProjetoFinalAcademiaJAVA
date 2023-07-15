package br.com.ghabriel.ProjetoFinalBackend.services;
import br.com.ghabriel.ProjetoFinalBackend.model.Candidato;

import java.util.List;

/**
 * Interface de serviços para o Candidato.
 * Qualquer classe que implemente essa interface precisa fornecer a implementação desses métodos para lidar com as operações de salvar, buscar, atualizar e remover candidatos.
 */
public interface CandidatoService {

    /**
     * Service para salvar um novo candidato no sistema.
     * @param candidato O candidato a ser salvo.
     * @return O candidato salvo.
     */
    Candidato salvarCandidato(Candidato candidato);

    /**
     * Service para atualizar um candidato existente.
     * @param candidatoID O ID do candidato a ser atualizado.
     * @param candidato O candidato com as informações atualizadas.
     */
    void atualizarCandidato (Long candidatoID, Candidato candidato);

    /**
     * Service para obter um candidato pelo seu ID.
     * @param candidatoID O ID do candidato a ser buscado.
     * @return O candidato encontrado ou null se não for encontrado.
     */
    Candidato obterCandidatoID(Long candidatoID);

    /**
     * Service para listar todos os candidatos.
     * @return Uma lista contendo todos os candidatos.
     */
    List<Candidato> listarCandidatos();

    /**
     * Service para remover um candidato pelo seu ID.
     * @param candidatoID O ID do candidato a ser removido.
     */
    void removerCandidato(Long candidatoID);
}
