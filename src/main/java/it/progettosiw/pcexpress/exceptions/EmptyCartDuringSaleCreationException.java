package it.progettosiw.pcexpress.exceptions;

public class EmptyCartDuringSaleCreationException extends RuntimeException {
    public EmptyCartDuringSaleCreationException() {
      super("Impossibile effettuare un acquisto su un carrello vuoto");
    }
}
