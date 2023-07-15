package br.com.ghabriel.ProjetoFinalBackend.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Representa uma autoridade (role) concedida a um usuário no sistema.
 */
public class Authority implements GrantedAuthority {

    private String authority;

    /**
     * Construtor que recebe o nome da autoridade.
     */
    public Authority(String authority) {
        this.authority = authority;
    }

    /**
     * Retorna a representação da autoridade como uma String.
     */
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
