package it.progettosiw.pcexpress.exceptions;

public class SaleNotFoundException extends RuntimeException {
    public SaleNotFoundException(Long id) {
        super("L'acquisto con id "+id+" non è stato trovato");
    }
}
