package it.progettosiw.pcexpress.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class RegistrationForm {
    @NotBlank
    @Pattern(regexp = "^[\\p{L}' ]+$", message = "Sono ammesse solo lettere, spazi e apostrofi")
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[\\p{L}' ]+$", message = "Sono ammesse solo lettere, spazi e apostrofi")
    @Column(nullable = false)
    private String lastName;

    @NotBlank
    @Email(message = "Inserisci un indirizzo email valido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Pattern(regexp = "^$|^\\+?[0-9]{6,15}$", message = "Numero di telefono non valido")   //contiene dalle 6 alle 15 cifre
    private String phoneNumber;

    @NotBlank
    @Size(min=6, message="La password deve contenere almeno 6 caratteri")
    private String password;

    public RegistrationForm() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
