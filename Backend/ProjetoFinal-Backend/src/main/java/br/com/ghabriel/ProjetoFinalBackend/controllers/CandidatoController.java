package br.com.ghabriel.ProjetoFinalBackend.controllers;

import br.com.ghabriel.ProjetoFinalBackend.model.Candidato;
import br.com.ghabriel.ProjetoFinalBackend.services.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Indica que a classe é um controladora REST, retornando objetos JSON
@RequestMapping("/admin/candidato") // mapeia as requisições recebidas para o caminho /admin/candidato
@CrossOrigin("*") // permite solicitações de origens diferentes (cross-origin requests) para serem aceitas pelo controlador
public class CandidatoController {

    @Autowired // injeta a interface do CandidatoService para usar seus métodos do CRUD.
    private CandidatoService candidatoService;

    /**
     * Salva um novo candidato no sistema.
     * @param candidato O objeto Candidato a ser salvo.
     * @return Resposta 200 ok quando o usuário for salvo com sucesso
     * @throws Exception Se ocorrer algum erro durante o processo de criação do usuário.
     * @PostMapping método POST HTTP usado para ser enviado a requisição para salvar  /salvarCandidato.
     */
    @PostMapping("/salvarCandidato/")
    public ResponseEntity<Candidato> salvarCandidato (@RequestBody Candidato candidato) {
        Candidato candidato1 = candidatoService.salvarCandidato(candidato);

        return ResponseEntity.ok(candidato1);
    }

    /**
     * Obtém um candidato pelo ID.
     * @param candidatoID O ID do candidato a ser obtido.
     * @return O objeto Candidato correspondente ao ID fornecido.
     * @GetMapping método GET HTTP usado para buscar um candidato pelo ID na rota /{candidatoID}.
     */
    @GetMapping("/{candidatoID}")
    public Candidato obterCandidatoID (@PathVariable("candidatoID") Long candidatoID) {
        return candidatoService.obterCandidatoID(candidatoID);
    }

    /**
     * Obtém a lista de todos os candidatos.
     * @return Resposta 200 OK contendo a lista de candidatos.
     * @GetMapping método GET HTTP usado para buscar a lista de candidatos na rota /lista.
     */
    @GetMapping("/lista")
    public ResponseEntity<?> listarCandidatos () {
        return ResponseEntity.ok(candidatoService.listarCandidatos());
    }


    /**
     * Atualiza um candidato existente pelo seu ID.
     * @param candidatoID O ID do candidato a ser atualizado.
     * @param candidato   O objeto Candidato com as informações atualizadas.
     * @return Resposta 204 No Content indicando que o candidato foi atualizado com sucesso.
     * @PutMapping método PUT HTTP usado para atualizar um candidato pelo ID na rota /{candidatoID}.
     */
    @PutMapping("/{candidatoID}")
    public ResponseEntity<Void> atualizarCandidato (@PathVariable Long candidatoID, @RequestBody Candidato candidato) {
        candidatoService.atualizarCandidato(candidatoID, candidato);

        return ResponseEntity.noContent().build();
    }

    /**
     * Remove um candidato pelo ID.
     * @param candidatoID O ID do candidato a ser removido.
     * @DeleteMapping método DELETE HTTP usado para remover um candidato pelo ID na rota /deletar/{candidatoID}.
     */
    @DeleteMapping("/deletar/{candidatoID}")
    public void removerCandidato (@PathVariable("candidatoID") Long candidatoID) {

        candidatoService.removerCandidato(candidatoID);

    }

}
