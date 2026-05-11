package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.model.Cart;
import it.progettosiw.pcexpress.model.CartItem;
import it.progettosiw.pcexpress.repository.CartItemRepository;
import it.progettosiw.pcexpress.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;

    }

    public void save(CartItem cartItem){
        cartItemRepository.save(cartItem);
    }

    public Cart getFirstCart () {
        if (cartRepository.findById(1L).isPresent()) {
            Cart cart = cartRepository.findById(1L).get();
            System.out.println(cart.getCartItems());
            return cart;
        }
        return null;
    }
    //cart.setCartItems((List<CartItem>)cartItemRepository.findAllById

    public List<CartItem> getAllCartItems () {
        //return (List<CartItem>) cartRepository.findAll();
        return null;
    }

}
