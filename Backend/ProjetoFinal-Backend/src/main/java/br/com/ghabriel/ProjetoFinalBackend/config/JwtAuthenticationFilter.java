package br.com.ghabriel.ProjetoFinalBackend.config;

import br.com.ghabriel.ProjetoFinalBackend.services.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filtro responsável pela autenticação JWT.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private Jwt jwt;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * Executa a lógica do filtro para cada requisição.
     * Extrai o token JWT do header "Authorization", valida-o e define a autenticação no contexto de segurança.
     * Se o token estiver expirado ou inválido, uma mensagem de erro é exibida e autenticação não é finalizada.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");  //cabeçalho header da requisição
        String username = null;  //inicializando para armazenar o username do usuário
        String jwtToken = null;  //inicializando para armazenar o token jwt

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {  //verificando se o cabeçalho está no padrão Bearer
            jwtToken = requestTokenHeader.substring(7); // extrai o token JWT do cabeçalho

            try {
                username = this.jwt.extractUsername(jwtToken);  // extrai o username do token jwt
            } catch (ExpiredJwtException expiredJwtException) {
                System.out.println("Seu token está expirado!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { // verifica se o username é válido e não há autenticação no contexto de segurança
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); // carrega os detalhes do usuário com base no username

            if (this.jwt.validateToken(jwtToken, userDetails)) { // valida o token JWT com os detalhes do usuário
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido!"); // Retorna uma resposta HTTP 401 Unauthorized (sem permissão)
                System.out.println("O token não está válido!");
                return;
            }
        }

        filterChain.doFilter(request, response); // chama o filtro
    }
}
