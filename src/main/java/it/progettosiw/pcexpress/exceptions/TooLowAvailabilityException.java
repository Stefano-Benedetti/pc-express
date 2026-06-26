package it.progettosiw.pcexpress.exceptions;

public class TooLowAvailabilityException extends RuntimeException {
    public TooLowAvailabilityException() {
        super("Quantità prodotto richiesta maggiore della disponibilità");
    }
}
