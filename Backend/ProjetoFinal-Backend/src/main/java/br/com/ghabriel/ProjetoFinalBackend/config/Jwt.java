package br.com.ghabriel.ProjetoFinalBackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Componente responsável por lidar com a geração, extração e validação de tokens JWT.
 */
@Component
public class Jwt {

    @Value("${jwt.secret-key}")
    private String secretKey;

    /**
     * Gera um token JWT com base nas informações do UserDetails
     * @param userDetails As informações do usuário autenticado
     * @return O token JWT gerado do usuário.
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Cria um token JWT com as claims fornecidas.
     * @param claims As claims a serem incluídas no token.
     * @param subject O nome de usuário a ser incluído no token.
     * @return O token JWT criado.
     */
    private String createToken (Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    /**
     * Valida um token JWT verificando se pertence ao usuário correto e se não está expirado
     * @param token O token JWT
     * @param userDetails As informações do usuário autenticado
     * @return retorna verdadeiro se o token estiver válido, retorna falso caso contrário.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); //verifica se o token é do user e se não está expirado.
    }

    /**
     * Verifica se o token JWT está expirado
     * @param token O token JWT
     * @return retorna verdadeiro se o token estiver expirado e não permite a autorização.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrai o nome de usuário do token JWT.
     * @param token O token JWT.
     * @return O nome de usuário extraído.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrai a data de expiração do token JWT.
     * @param token O token JWT.
     * @return A data de expiração do token.
     */
    public Date extractExpiration (String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Claims são pedaços de informações contidas no payload do token e representam as declarações claims do usuário .
     * @param token O token JWT.
     * @param claimsResolver Os claims são estruturados com o padrão (key-value) chave e valor, servem para identificar um usuário e fornecer permissões de acessos
     * @param <T> O tipo de dado da reivindicação.
     * @return A reivindicação completa extraída do token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);  // o resolvedor da reivindicação aplica aos claims extráidos no token.
    }

    private Claims extractAllClaims (String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
