package it.progettosiw.pcexpress.exceptions;

public class NonPositiveQuantityException extends RuntimeException {
    public NonPositiveQuantityException() {
        super("Impossibile effettuare l'acquisto di un pc in quantità minore di 1");
    }
}
