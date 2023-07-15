package br.com.ghabriel.ProjetoFinalBackend.services.impl;

import br.com.ghabriel.ProjetoFinalBackend.model.Candidato;
import br.com.ghabriel.ProjetoFinalBackend.repository.CandidatoRepository;
import br.com.ghabriel.ProjetoFinalBackend.services.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de serviços que implementa a interface CandidatoService. */
@Service
public class CandidatoServiceImpl implements CandidatoService {

    /**
     * Anotação Autowired é usada para injetar e as dependências do repositório do candidato nessa classe de serviço.
     */
    @Autowired
    private CandidatoRepository candidatoRepository;

    /**
     * Salva um novo candidato no sistema.
     * @param candidato O objeto Candidato a ser salvo.
     * @return O candidato salvo.
     */
    @Override
    public Candidato salvarCandidato(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    /**
     * Atualiza as informações de um candidato existente.
     * @param candidatoID O ID do candidato a ser atualizado.
     * @param candidato O objeto Candidato com as informações atualizadas.
     */
    @Override
    public void atualizarCandidato(Long candidatoID, Candidato candidato) {
        //verificado se o user está no banco
        candidatoRepository.findById(candidatoID)
                .orElseThrow(()
                        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidato não existe no banco com o ID : " + candidatoID));

        candidato.setCandidatoID(candidatoID);

        candidatoRepository.save(candidato);
        System.out.println("Candidato atualizado com sucesso!");
    }

    /**
     * Obtém um candidato pelo seu ID.
     * @param candidatoID O ID do candidato a ser obtido.
     * @return O candidato encontrado.
     */
    @Override
    public Candidato obterCandidatoID(Long candidatoID) {
        return candidatoRepository.findById(candidatoID).get();
    }

    /**
     * Lista todos os candidatos cadastrados no sistema.
     * @return A lista de candidatos.
     */
    @Override
    public List<Candidato> listarCandidatos() {
        return new ArrayList<>(candidatoRepository.findAll());
    }

    /**
     * Remove um candidato pelo seu ID.
     * @param candidatoID O ID do candidato a ser removido.
     */
    @Override
    public void removerCandidato(Long candidatoID) {
        Candidato candidatoRemovido = new Candidato();
        candidatoRemovido.setCandidatoID(candidatoID);

        candidatoRepository.delete(candidatoRemovido);
    }
}
