package br.com.ghabriel.ProjetoFinalBackend.controllers;

import br.com.ghabriel.ProjetoFinalBackend.model.Rol;
import br.com.ghabriel.ProjetoFinalBackend.model.Usuario;
import br.com.ghabriel.ProjetoFinalBackend.model.UsuarioRol;
import br.com.ghabriel.ProjetoFinalBackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController  // Indica que a classe é um controladora REST, retornando e enviando objetos JSON
@RequestMapping("/usuarios")  // mapeia as requisições recebidas para o caminho /usuarios
@CrossOrigin("*") // permite solicitações de origens diferentes (cross-origin requests) para serem aceitas com o frontend
public class UsuarioController {

    @Autowired // injeta a interface do UsuarioService para usar seus métodos do CRUD.
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Método para criar novos Usuários no sistema.
     * @param usuario O objeto Usuario a ser salvo.
     * @return O objeto Usuario salvo com sua Rol de permissão.
     * @throws Exception Se ocorrer algum erro durante o processo de criação do usuário.
     * @PostMapping método POST HTTP usado para ser enviado a requisição para salvar.
     */
    @PostMapping("/")
    public Usuario salvarUsuario (@RequestBody Usuario usuario) throws Exception {

        usuario.setPerfil(".png");
        usuario.setPassword(this.passwordEncoder.encode(usuario.getPassword()));  //criptografa e salva a senha com um hash pelo algoritmo bcrypt

        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol role = new Rol();
        role.setRolId(1L);
        role.setRolNome("ADMIN");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(role);

        usuarioRoles.add(usuarioRol);

        return usuarioService.salvarUsuario(usuario, usuarioRoles);
    }

    /**
     * Controladora para obter um usuário pelo nome de usuário.
     * @param username O nome de usuário do usuário a ser buscado.
     * @return O objeto Usuario correspondente ao nome de usuário fornecido, ou null se não for encontrado.
     * @GetMapping Método GET HTTP usado para enviar uma requisição para buscar um usuário.
     */
    @GetMapping("/{username}")
    public Usuario buscaUsuario (@PathVariable("usuarioId") String username) {
        return usuarioService.buscaUsuario(username);
    }

    /**
     * Controlador que obtém um usuário pelo seu ID para poder alterar e remover o mesmo
     * @param usuarioId O ID do usuário
     * @DeleteMapping Método DELETE HTTP usado para enviar uma requisição para remover um usuário pelo seu ID.
     */
    @DeleteMapping("/{usuarioId}")
    public void removerUsuario (@PathVariable("usuarioId") Long usuarioId) {
        usuarioService.removerUsuario(usuarioId);
    }
}
