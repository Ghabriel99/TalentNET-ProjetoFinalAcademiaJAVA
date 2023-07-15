package br.com.ghabriel.ProjetoFinalBackend.services.impl;

import br.com.ghabriel.ProjetoFinalBackend.model.Vaga;
import br.com.ghabriel.ProjetoFinalBackend.repository.VagaRepository;
import br.com.ghabriel.ProjetoFinalBackend.services.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de serviços que implementa a interface VagaService.
 */
@Service
public class VagaServiceImpl implements VagaService {

    /**
     * Anotação Autowired é usada para injetar e as dependências do repositório do vaga nessa classe de serviço.
     */
    @Autowired
    private VagaRepository vagaRepository;

    /**
     * Salva uma nova vaga no sistema.
     * @param vaga A vaga a ser salva.
     * @return A vaga salva.
     */
    @Override
    public Vaga salvarVaga(Vaga vaga) {
        return vagaRepository.save(vaga);
    }

    /**
     * Atualiza as informações de uma vaga existente.
     * @param vagaID O ID da vaga a ser atualizada.
     * @param vaga A vaga com as informações atualizadas.
     */
    @Override
    public void atualizarVaga(Long vagaID, Vaga vaga) {

        vagaRepository.findById(vagaID).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga com o ID : " + vagaID + "não existe no banco!"));
        vaga.setVagaID(vagaID);

        vagaRepository.save(vaga);
        System.out.println("Vaga atualizada com sucesso");
    }

    /**
     * Lista todas as vagas cadastradas no sistema.
     * @return A lista de vagas.
     */
    @Override
    public List<Vaga> listarVagas() {
        return new ArrayList<>(vagaRepository.findAll());
    }

    /**
     * Obtém uma vaga pelo seu ID.
     * @param vagaID O ID da vaga a ser obtida.
     * @return A vaga encontrada.
     */
    @Override
    public Vaga obterVagaID(Long vagaID) {

        vagaRepository.findById(vagaID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga com o ID : " + vagaID + " não existe no banco!"));

        return vagaRepository.findById(vagaID).orElseThrow(()-> new RuntimeException("Vaga não foi encontrada"));
    }

    /**
     * Deleta uma vaga pelo seu ID.
     * @param vagaID O ID da vaga a ser deletada.
     */
    @Override
    public void deletarVaga(Long vagaID) {

        Vaga vagaR = new Vaga();
        vagaR.setVagaID(vagaID);

        vagaRepository.deleteById(vagaR.getVagaID());

    }
}
