package br.com.ghabriel.ProjetoFinalBackend.services;

import br.com.ghabriel.ProjetoFinalBackend.model.Usuario;
import br.com.ghabriel.ProjetoFinalBackend.model.UsuarioRol;

import java.util.Set;

/**
 * Interface de serviços para o Usuario.
 * Qualquer classe que implemente essa interface precisa fornecer a implementação desses métodos para lidar com as operações de salvar, buscar e remover usuários.
 */
public interface UsuarioService {

    /**
     * Service para salvar um novo usuário no sistema.
     * @param usuario O objeto Usuario a ser salvo.
     * @param usuarioRoles O conjunto de associações UsuarioRol relacionadas ao usuário.
     * @throws Exception Se ocorrer algum erro durante o processo de salvamento do usuário.
     */
    public Usuario salvarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;


    /**
     * Service para buscar um usuário pelo seu username.
     * @param username O nome de usuário(username) para ser buscado.
     */
    public Usuario buscaUsuario (String username);


    /**
     * Service para remover um usuário pelo seu ID.
     * @param usuarioId O ID do usuário a ser removido.
     */
    public void removerUsuario (Long usuarioId);
}
