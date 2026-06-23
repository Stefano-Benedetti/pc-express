package it.progettosiw.pcexpress.exceptions;

public class PCNotFoundException extends RuntimeException {
    public PCNotFoundException(Long id) {
        super("Il pc con l'id " + id + " non esiste");
    }
}
