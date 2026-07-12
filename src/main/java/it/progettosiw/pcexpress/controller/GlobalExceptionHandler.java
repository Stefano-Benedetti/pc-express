package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlePCDoesNotExist(PCDoesNotExistException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/400";
    }

    @ExceptionHandler(cartItemDoesNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleCartItemDoesNotExist(cartItemDoesNotExistException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/400";
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

    @ExceptionHandler(InvalidQuantityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNonPositiveQuantity(InvalidQuantityException e, Model model) {
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

    @ExceptionHandler(SaleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleSaleDoesNotExist(SaleNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/404";
    }


    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleIOException(IOException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/500";
    }

}
