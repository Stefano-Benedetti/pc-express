package it.progettosiw.pcexpress.exceptions;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException() {
        super("Impossibile effettuare l'operazione con quantità minore di 1");
    }
}
