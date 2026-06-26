package it.progettosiw.pcexpress.exceptions;

public class PCDoesNotExistsException extends RuntimeException {
    public PCDoesNotExistsException(Long id) {
        super("Il pc con l'id " + id + " non esiste");
    }
}
