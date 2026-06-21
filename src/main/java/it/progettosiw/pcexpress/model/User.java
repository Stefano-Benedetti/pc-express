package it.progettosiw.pcexpress.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    @Pattern(regexp = "^$|^\\+?[0-9]{6,15}$", message = "Numero di telefono non valido")   //contiene dalle 8 alle 15 cifre
    private String phoneNumber;

    //da testare la strategia di fetch
    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Sale> purchases;

    public User(){

    }

    public User(String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String phoneNumber, Cart cart, List<Sale> purchases) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
        this.purchases = purchases;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Sale> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Sale> purchases) {
        this.purchases = purchases;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
