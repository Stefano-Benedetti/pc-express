package it.progettosiw.pcexpress.exceptions;

public class PCDoesNotExistException extends RuntimeException {
    public PCDoesNotExistException(Long id) {
        super("Il pc con l'id " + id + " non esiste");
    }
}
