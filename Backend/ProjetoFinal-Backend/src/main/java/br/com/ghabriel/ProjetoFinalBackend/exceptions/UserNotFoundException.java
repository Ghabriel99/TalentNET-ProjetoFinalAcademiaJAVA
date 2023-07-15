package br.com.ghabriel.ProjetoFinalBackend.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("Usúario não encontrado no sistema!");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
