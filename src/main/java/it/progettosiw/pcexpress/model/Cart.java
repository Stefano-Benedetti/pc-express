package it.progettosiw.pcexpress.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name="cart_id")
    private List<CartItem> cartItems;

    public Cart(){

    }

    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
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

    public Float calcTotPrice(){
        Float total_price = 0F;
        for(CartItem ci : this.cartItems){
            total_price += ci.getPc().getPrezzo() * ci.getQuantity();
        }
        return total_price;
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
