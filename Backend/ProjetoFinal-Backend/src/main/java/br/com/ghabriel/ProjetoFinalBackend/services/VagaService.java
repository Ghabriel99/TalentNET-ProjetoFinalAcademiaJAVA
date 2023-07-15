package br.com.ghabriel.ProjetoFinalBackend.services;

import br.com.ghabriel.ProjetoFinalBackend.model.Vaga;

import java.util.List;

/**
 * Interface de serviços para a Vaga.
 * Qualquer classe que implemente essa interface precisa fornecer a implementação desses métodos para lidar com as operações de salvar, buscar, atualizar e remover vagas.
 */
public interface VagaService {

    /**
     * Service para salvar uma nova vaga no sistema.
     * @param vaga A vaga para ser salva.
     */
    Vaga salvarVaga (Vaga vaga);


    /**
     * Service para atualizar uma vaga existente pelo seu ID.
     * @param vaga A vaga com as informações atualizadas.
     */
    void atualizarVaga (Long vagaID,Vaga vaga);


    /**
     * Service para listar todas as vagas.
     * @return Uma lista contendo todas as vagas.
     */
    List<Vaga> listarVagas();


    /**
     * Service para obter uma vaga pelo seu ID.
     * @return A vaga encontrada ou null se não for encontrada.
     */
    Vaga obterVagaID (Long vagaID);


    /**
     * Service para deletar uma vaga pelo seu ID.
     */
    void deletarVaga (Long vagaID);
}
