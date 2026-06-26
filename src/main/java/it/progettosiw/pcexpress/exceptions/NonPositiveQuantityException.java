package it.progettosiw.pcexpress.exceptions;

public class NonPositiveQuantityException extends RuntimeException {
    public NonPositiveQuantityException() {
        super("Impossibile effettuare l'operazione con quantità minore di 1");
    }
}
