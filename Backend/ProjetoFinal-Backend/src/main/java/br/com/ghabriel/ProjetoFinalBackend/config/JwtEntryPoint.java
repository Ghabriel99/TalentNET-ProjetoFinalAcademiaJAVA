package br.com.ghabriel.ProjetoFinalBackend.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Ponto de entrada para autenticação JWT.
 * É chamado quando ocorre uma exceção de autenticação não autorizada.
 * Retorna uma resposta HTTP 401 Unauthorized com uma mensagem informando que está sem permissão.
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    /**
     * Método para tratar a exceção de autenticação não autorizada.
     * Retorna uma resposta HTTP 401 Unauthorized com uma mensagem informando que está sem permissão.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Não está autorizado!");
    }
}
