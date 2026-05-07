package it.progettosiw.pcexpress.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Float totalPrice;

    @OneToMany
    private List<CartItem> cartItems;

    public Cart(){

    }

    public Cart(Float totalPrice, List<CartItem> cartItems, User user) {
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
