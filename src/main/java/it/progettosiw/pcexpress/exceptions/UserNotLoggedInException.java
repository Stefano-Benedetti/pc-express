package it.progettosiw.pcexpress.exceptions;

public class UserNotLoggedInException extends RuntimeException {
    public UserNotLoggedInException() {
        super("L'utente non è loggato");
    }
}
