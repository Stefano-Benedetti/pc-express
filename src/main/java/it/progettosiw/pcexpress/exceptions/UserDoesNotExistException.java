package it.progettosiw.pcexpress.exceptions;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException() {
        super("L'utente non è stato trovato");
    }
}
