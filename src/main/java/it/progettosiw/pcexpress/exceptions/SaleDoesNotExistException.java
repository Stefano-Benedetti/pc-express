package it.progettosiw.pcexpress.exceptions;

public class SaleDoesNotExistException extends RuntimeException {
    public SaleDoesNotExistException(Long id) {
        super("L'acquisto con id "+id+" non esiste");
    }
}
