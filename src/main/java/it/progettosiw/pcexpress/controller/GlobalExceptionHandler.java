package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.exceptions.EmptyCartDuringSaleCreationException;
import it.progettosiw.pcexpress.exceptions.NonPositiveQuantityException;
import it.progettosiw.pcexpress.exceptions.PCNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(PCNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handlePCNotFound(PCNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/500";
    }

    @ExceptionHandler(EmptyCartDuringSaleCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEmptyCartDuringSaleCreation(EmptyCartDuringSaleCreationException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/400";
    }

    @ExceptionHandler(NonPositiveQuantityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNonPositiveQuantity(NonPositiveQuantityException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/400";
    }
}
