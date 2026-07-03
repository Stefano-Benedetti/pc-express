package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(PCNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePCNotFound(PCNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/404";
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFound(UserNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/404";
    }

    @ExceptionHandler(CartNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCartNotFound(CartNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/404";
    }


    @ExceptionHandler(PCDoesNotExistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handlePCDoesNotExist(PCDoesNotExistException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/500";
    }

    @ExceptionHandler(cartItemDoesNotExistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleCartItemDoesNotExist(cartItemDoesNotExistException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/500";
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUserDoesNotExist(UserDoesNotExistException e, Model model) {
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

    @ExceptionHandler(TooLowAvailabilityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleTooLowAvailability(TooLowAvailabilityException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/400";
    }

    @ExceptionHandler(UserNotLoggedInException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleUserNotLoggedIn(UserNotLoggedInException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/login";
    }


}
