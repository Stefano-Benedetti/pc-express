package it.progettosiw.pcexpress.exceptions;

public class PCWithThisCodeAlreadyExistsException extends RuntimeException {
    public PCWithThisCodeAlreadyExistsException(Long codice) {
        super("Il pc con codice " + codice + " già esiste");
    }
}
