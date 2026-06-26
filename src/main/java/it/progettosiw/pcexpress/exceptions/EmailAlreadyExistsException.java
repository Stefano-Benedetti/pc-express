package it.progettosiw.pcexpress.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("Esiste già un utente registrato con questa email");
    }
}
