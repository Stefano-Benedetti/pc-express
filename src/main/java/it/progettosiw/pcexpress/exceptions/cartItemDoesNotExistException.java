package it.progettosiw.pcexpress.exceptions;

public class cartItemDoesNotExistException extends RuntimeException {
    public cartItemDoesNotExistException(Long cart_id, Long pc_id) {
        super("il cartItem con pc_id "+pc_id+" non esiste nel carrello con id "+cart_id);
    }
}
