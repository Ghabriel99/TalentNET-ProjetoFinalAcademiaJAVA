package br.com.ghabriel.ProjetoFinalBackend.model;

/**
 * Representa uma requisição de autenticação usando o JSON Web Token (JWT).
 */
public class JwtRequest {

    /**
     * Nome e senha do usuario obtidos para a autenticação.
     */
    private String username;
    private String password;

    /**
     * Construtor padrão e construtor com username e password
     */
    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Obtém a nome do usuário para o usar o método getUsername() na autenticação e criar seu token de acesso somente para esse usuário.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Obtém a nome do usuário para o usar o método setUsername() na autenticação e criar seu token de acesso.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtém a senha para o usar o método getPassword() na autenticação.
     */
    public String getPassword() {
        return password;
    }

}
