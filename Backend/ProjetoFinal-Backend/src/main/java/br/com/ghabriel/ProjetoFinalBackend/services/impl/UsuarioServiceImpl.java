package br.com.ghabriel.ProjetoFinalBackend.services.impl;

import br.com.ghabriel.ProjetoFinalBackend.exceptions.UserFoundException;
import br.com.ghabriel.ProjetoFinalBackend.model.Usuario;
import br.com.ghabriel.ProjetoFinalBackend.model.UsuarioRol;
import br.com.ghabriel.ProjetoFinalBackend.repository.RolRepository;
import br.com.ghabriel.ProjetoFinalBackend.repository.UsuarioRepository;
import br.com.ghabriel.ProjetoFinalBackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Classe de serviços que implementa a interface UsuarioService.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    /**
     * Anotação Autowired é usada para injetar e as dependências do repositório do usuário nessa classe de serviço.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    /**
     * Cadastra e salva um novo usuário no sistema.
     * @param usuario O objeto Usuario a ser salvo.
     * @param usuarioRoles O conjunto de associações UsuarioRol relacionadas ao usuário.
     * @return O objeto Usuario salvo.
     * @throws Exception Se ocorrer algum erro durante o processo de criação do usuário.
     */
    @Override
    public Usuario salvarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {

        // Verifica se o usuário já está cadastrado no sistema pelo seu username
        Usuario usuarioSalvo = usuarioRepository.findByUsername(usuario.getUsername());
        if (usuarioSalvo != null) {
            System.out.println("Usuário já existe");
            throw new UserFoundException(usuarioSalvo + ": Esse usúario já existe no sistema!");
        } else {

            // Percorre e salva as associações UsuarioRol no repositório de Rols
            for (UsuarioRol usuarioRol:usuarioRoles) {
                rolRepository.save(usuarioRol.getRol());
            }
            // Adiciona as associações UsuarioRol ao usuário cadastrado.
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            // Salva o usuário no repositório de usuários
            usuarioSalvo = usuarioRepository.save(usuario);
        }
        return usuarioSalvo;
    }

    /**
     * Busca um usuário pelo nome de usuário no banco de dados.
     * @param username O nome de usuário do usuário a ser buscado.
     * @return O objeto Usuario encontrado no banco de dados correspondente ao nome de usuário fornecido, ou nulo se não for encontrado.
     */
    @Override
    public Usuario buscaUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    /**
     * Remove um usuário pelo seu ID
     * @param usuarioId O ID do usuário a ser removido e usuado no método deleteByID.
     */
    @Override
    public void removerUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }
}
