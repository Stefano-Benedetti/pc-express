package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.model.Cart;
import it.progettosiw.pcexpress.model.CartItem;
import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.model.User;
import it.progettosiw.pcexpress.repository.CartItemRepository;
import it.progettosiw.pcexpress.repository.CartRepository;
import it.progettosiw.pcexpress.repository.PCRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private PCRepository pcRepository;
    private UserService userService;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, PCRepository pcRepository, UserService userService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.pcRepository = pcRepository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public Cart getCurrentUserCart () {
        User user = userService.getCurrentUser();

        Optional<Cart> optCart = cartRepository.findById(user.getCart().getId());
        if (optCart.isPresent()) {
            Cart cart = optCart.get();
            return cart;
        }
        return null;
    }

    @Transactional
    public void emptyCurrentUserCart(){
        Cart cart = getCurrentUserCart();
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

    @Transactional
    public void addToCurrentUserCart(Long pc_id, Integer quantity){
        Cart cart = getCurrentUserCart();

        Optional<CartItem> cartItemOptional = cartItemRepository.findCartItemByCartIdAndPcId(cart.getId(),pc_id);
        CartItem cartItem;

        if(cartItemOptional.isPresent()){
            cartItem = cartItemOptional.get();
            Integer temp = cartItem.getQuantity();
            temp+=quantity;
            cartItem.setQuantity(temp);
            cartItemRepository.save(cartItem);
            logger.info("Aggiornata quantità nel carrello del seguente pc: {}", cartItem.getPc().getId());
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
        logger.info("Aggiunto al carrello il seguente pc: {}", cartItem.getPc().getId());
    }

    @Transactional
    public void removeCartItemFromCurrentUserCart(Long pc_id){
        Cart cart = getCurrentUserCart();

        Optional<CartItem> cartItemOptional = cartItemRepository.findCartItemByCartIdAndPcId(cart.getId(),pc_id);
        CartItem cartItem;

        if(cartItemOptional.isPresent()){
            cartItem = cartItemOptional.get();
            cart.getCartItems().remove(cartItem);
        }
        //else errore

        cartRepository.save(cart);
    }

    @Transactional
    public void removeOneFromCurrentUserCart(Long pc_id){
        Cart cart = getCurrentUserCart();
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
