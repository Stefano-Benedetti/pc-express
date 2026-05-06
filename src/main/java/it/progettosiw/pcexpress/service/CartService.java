package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.model.CartItem;
import it.progettosiw.pcexpress.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartItem> getAllCartItems () {
        //return (List<CartItem>) cartRepository.findAll();
        return null;
    }

}
