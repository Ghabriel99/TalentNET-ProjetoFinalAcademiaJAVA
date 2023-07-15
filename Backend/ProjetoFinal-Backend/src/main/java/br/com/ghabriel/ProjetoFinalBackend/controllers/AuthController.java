package br.com.ghabriel.ProjetoFinalBackend.controllers;

import br.com.ghabriel.ProjetoFinalBackend.config.Jwt;
import br.com.ghabriel.ProjetoFinalBackend.exceptions.UserNotFoundException;
import br.com.ghabriel.ProjetoFinalBackend.model.JwtRequest;
import br.com.ghabriel.ProjetoFinalBackend.model.JwtResponse;
import br.com.ghabriel.ProjetoFinalBackend.model.Usuario;
import br.com.ghabriel.ProjetoFinalBackend.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private Jwt jwtUtils;

    /**
     * Autentica o usuário com base nas credenciais fornecidas.
     * @param username O nome de usuário.
     * @param password A senha.
     * @throws Exception Se as credenciais forem inválidas ou o usuário estiver desabilitado.
     */
    private void autenticar(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException exception){
            throw  new Exception("Seu usuário está desabiltiado! " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciais inválidas :" + e.getMessage());
        }
    }

    /**
     * Gera o token JWT para um usuário autenticado com base nas credenciais fornecidas.
     * @param jwtRequest Objeto contendo as informações de autenticação (nome de usuário e senha).
     * @return ResponseEntity contendo a resposta com o token JWT.
     * @throws Exception Se ocorrer algum erro durante a geração do token.
     */
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    /**
     * Retorna os detalhes do usuário atualmente autenticado.
     * @param principal O objeto Principal fornecido pelo Spring Security.
     * @return O objeto Usuario que representa o usuário atualmente autenticado e retorna seu username.
     */
    @GetMapping("/atual-usuario")
    public Usuario actualUser(Principal principal) {
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
