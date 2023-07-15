package br.com.ghabriel.ProjetoFinalBackend.services.impl;


import br.com.ghabriel.ProjetoFinalBackend.model.Usuario;
import br.com.ghabriel.ProjetoFinalBackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementação da interface UserDetailsService usado pelo Spring Security para carregar os detalhes do usuário durante o processo de autenticação.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Carrega os detalhes do usuário com base no seu nome de usuário.
     * @param username O nome de usuário do usuário a ser carregado.
     * @return Os detalhes do usuário encontrado.
     * @throws UsernameNotFoundException Se o usuário não for encontrado no sistema.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado no sistema!");
        }
        return usuario;
    }
}
