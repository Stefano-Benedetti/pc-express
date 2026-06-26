package it.progettosiw.pcexpress.exceptions;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(Long id) {
        super("Carrello con id "+ id + " non trovato");
    }
}
