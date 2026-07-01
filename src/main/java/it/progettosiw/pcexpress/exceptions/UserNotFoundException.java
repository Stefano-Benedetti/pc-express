package it.progettosiw.pcexpress.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("L'utente con l'id "+id+" non è stato trovato");
    }
}
