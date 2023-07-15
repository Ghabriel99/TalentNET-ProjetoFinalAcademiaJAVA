package br.com.ghabriel.ProjetoFinalBackend.exceptions;

public class UserFoundException extends Exception {

    public UserFoundException() {
        super("Esse usúario já existe no sistema!");
    }

    public UserFoundException(String msg) {
        super(msg);
    }

}
