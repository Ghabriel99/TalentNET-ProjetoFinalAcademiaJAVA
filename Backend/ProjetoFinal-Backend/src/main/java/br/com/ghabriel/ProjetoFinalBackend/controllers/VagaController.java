package br.com.ghabriel.ProjetoFinalBackend.controllers;

import br.com.ghabriel.ProjetoFinalBackend.model.Vaga;
import br.com.ghabriel.ProjetoFinalBackend.services.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Indica que a classe é um controladora REST, retornando e enviando objetos JSON
@RequestMapping("/admin/vaga") // mapeia as requisições recebidas para o caminho /admin/vaga
@CrossOrigin("*") // permite solicitações de origens diferentes (cross-origin requests) para serem aceitas com o frontend
public class VagaController {

    @Autowired // injeta a interface do VagaService para usar seus métodos do CRUD.
    private VagaService vagaService;

    /**
     * Controladora para Salvar uma nova vaga no sistema.
     * @return Resposta 200 ok quando a vaga for salva com sucesso
     * @throws Exception Se ocorrer algum erro durante o processo de criação da vaga.
     * @PostMapping método POST HTTP usado para ser enviado a requisição para salvar  /salvar.
     */
    @PostMapping("/salvar")  // http://localhost:8080/admin/vaga/salvar/
    public ResponseEntity<Vaga> salvarVaga (@RequestBody Vaga vaga) {
        Vaga vaga1 = vagaService.salvarVaga(vaga);

        return ResponseEntity.ok(vaga1);
    }

    /**
     * Controladora que obtém uma vaga pelo ID.
     * @param vagaID O ID da vaga obtida.
     * @return O objeto Vaga correspondente ao ID fornecido.
     * @GetMapping método GET HTTP usado para buscar uma vaga pelo ID na rota /{vagaID}.
     */
    @GetMapping("/{vagaID}")
    public Vaga obterVagaID (@PathVariable("vagaID") Long vagaID) {
        return vagaService.obterVagaID(vagaID);
    }

    /**
     * Controladora que obtém a lista de todas as vagas.
     * @return Resposta 200 OK contendo a lista de vagas.
     * @GetMapping método GET HTTP usado para buscar a lista de vagas na rota /lista.
     */
    @GetMapping("/lista")
    public ResponseEntity<?> listarVagas() {
        return ResponseEntity.ok(vagaService.listarVagas());
    }


    /**
     * Controladora para Atualizar uma vaga existente.
     * @param vagaID O ID da vaga a ser atualizada.
     * @param vaga   O objeto Vaga com as informações atualizadas.
     * @PutMapping método PUT HTTP usado para atualizar uma vaga pelo ID na rota /alterar/{vagaID}.
     */
    @PutMapping("/{vagaID}")
    public ResponseEntity<Void> atualizarVaga (@PathVariable Long vagaID, @RequestBody Vaga vaga) {
        vagaService.atualizarVaga(vagaID, vaga);

        return ResponseEntity.noContent().build();
    }

    /**
     * Controlador que obtém uma vaga pelo seu ID para poder alterar e remover.
     * @param vagaID O ID da vaga.
     * @DeleteMapping método DELETE HTTP usado para remover uma vaga pelo ID na rota /deletar/{vagaID}.
     */
    @DeleteMapping("/deletar/{vagaID}")
    public void deletarVaga (@PathVariable("vagaID") Long vagaID) {

        vagaService.deletarVaga(vagaID);
    }
}
