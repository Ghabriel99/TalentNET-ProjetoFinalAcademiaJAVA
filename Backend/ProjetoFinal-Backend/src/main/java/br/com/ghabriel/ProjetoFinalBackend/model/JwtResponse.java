package br.com.ghabriel.ProjetoFinalBackend.model;

/**
 * Representa uma resposta de autenticação usando o JSON Web Token (JWT).
 */
public class JwtResponse {

    /**
     * Token de resposta enviado para realizar a autenticação.
     */
    private String token;

    public JwtResponse() {}

    /**
     * Método para gerar o token na classe AuthController,
     */
    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
