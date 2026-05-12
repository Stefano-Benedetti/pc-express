package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.model.Cart;
import it.progettosiw.pcexpress.model.CartItem;
import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.repository.CartItemRepository;
import it.progettosiw.pcexpress.repository.CartRepository;
import it.progettosiw.pcexpress.repository.PCRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private PCRepository pcRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, PCRepository pcRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.pcRepository = pcRepository;

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

    // getFirstCart DA SOSTITUIRE CON getCartByCurrentUser
    public void addToCurrentUserCart(Long pc_id, Integer quantity){
        Cart cart = getFirstCart();////////////////////////////////////

        Optional<CartItem> cartItemOptional = cartItemRepository.findCartItemByCartIdAndPcId(cart.getId(),pc_id);
        CartItem cartItem;

        if(cartItemOptional.isPresent()){
            cartItem = cartItemOptional.get();
            Integer temp = cartItem.getQuantity();
            temp+=quantity;
            cartItem.setQuantity(temp);
            cartItemRepository.save(cartItem);
            return;
        }

        Optional<PC> optionalPC = pcRepository.findById(pc_id);
        if(optionalPC.isPresent()) {
            cartItem = new CartItem();
            cartItem.setPc(optionalPC.get());
            cartItem.setQuantity(quantity);
            cart.getCartItems().add(cartItem);  // per impostare cart_id in cart_item nel DB
        }
        else{
            throw new RuntimeException();   //da sistemare
        }
        cartRepository.save(cart);
    }

    public void removeCartItemFromCurrentUserCart(Long pc_id){
        Cart cart = getFirstCart();////////////////////////////////////

        Optional<CartItem> cartItemOptional = cartItemRepository.findCartItemByCartIdAndPcId(cart.getId(),pc_id);
        CartItem cartItem;

        if(cartItemOptional.isPresent()){
            cartItem = cartItemOptional.get();
            cart.getCartItems().remove(cartItem);
        }
        //else errore

        cartRepository.save(cart);
    }

    public void removeOneFromCurrentUserCart(Long pc_id){
        Cart cart = getFirstCart();////////////////////////////////////
        Optional<CartItem> cartItemOptional = cartItemRepository.findCartItemByCartIdAndPcId(cart.getId(),pc_id);
        CartItem cartItem;

        if(cartItemOptional.isPresent()){
            cartItem = cartItemOptional.get();
            Integer temp = cartItem.getQuantity();
            if(temp == 1){
                removeCartItemFromCurrentUserCart(pc_id);
                return;
            }
            temp -= 1;
            cartItem.setQuantity(temp);
            cartItemRepository.save(cartItem);
        }
        //else errore
    }

}
