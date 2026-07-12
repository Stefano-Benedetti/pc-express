package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.exceptions.CartNotFoundException;
import it.progettosiw.pcexpress.exceptions.InvalidQuantityException;
import it.progettosiw.pcexpress.exceptions.PCDoesNotExistException;
import it.progettosiw.pcexpress.exceptions.cartItemDoesNotExistException;
import it.progettosiw.pcexpress.model.Cart;
import it.progettosiw.pcexpress.model.CartItem;
import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.model.User;
import it.progettosiw.pcexpress.repository.CartItemRepository;
import it.progettosiw.pcexpress.repository.CartRepository;
import it.progettosiw.pcexpress.repository.PCRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Long cartid = user.getCart().getId();
        return cartRepository.findById(cartid)
                .orElseThrow(() -> new CartNotFoundException(cartid));
    }

    @Transactional
    public void emptyCurrentUserCart(){
        Cart cart = getCurrentUserCart();
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }


    private void updateCartItemQuantity(CartItem cartItem, Integer quantity){
        cartItem.incrementQuantity(quantity);
        cartItemRepository.save(cartItem);
        logger.info("Aggiornata quantità nel carrello del seguente pc: {}", cartItem.getPc().getId());
    }

    private void createAndAddNewCartItemToCart(Cart cart, Long pc_id, Integer quantity){
        PC pc = pcRepository.findById(pc_id).orElseThrow(()-> new PCDoesNotExistException(pc_id));
        CartItem cartItem = new CartItem(quantity, pc);
        cart.getCartItems().add(cartItem);  // per impostare cart_id in cart_item nel DB
        cartRepository.save(cart);
        logger.info("Aggiunto al carrello il seguente pc: {}", cartItem.getPc().getId());
    }

    @Transactional
    public void addToCurrentUserCart(Long pc_id, Integer quantity){
        if (quantity<1)
            throw new InvalidQuantityException();
        Cart cart = getCurrentUserCart();

        Optional<CartItem> cartItemOptional = cartItemRepository.findCartItemByCartIdAndPcId(cart.getId(),pc_id);

        if(cartItemOptional.isPresent())
            updateCartItemQuantity(cartItemOptional.get(), quantity);
        else
            createAndAddNewCartItemToCart(cart, pc_id, quantity);
    }


    @Transactional
    public void removeCartItemFromCurrentUserCart(Long pc_id){
        Cart cart = getCurrentUserCart();
        CartItem cartItem = cartItemRepository.findCartItemByCartIdAndPcId(cart.getId(),pc_id)
                        .orElseThrow(()-> new cartItemDoesNotExistException(cart.getId(),pc_id));
        cart.getCartItems().remove(cartItem);
        cartRepository.save(cart);
    }

    @Transactional
    public void removeOneFromCurrentUserCart(Long pc_id){
        Cart cart = getCurrentUserCart();
        CartItem cartItem = cartItemRepository.findCartItemByCartIdAndPcId(cart.getId(),pc_id)
                .orElseThrow(()-> new cartItemDoesNotExistException(cart.getId(),pc_id));
        if(cartItem.getQuantity() == 1)
            removeCartItemFromCurrentUserCart(pc_id);
        else{
            cartItem.decrementQuantity(1);
            cartItemRepository.save(cartItem);
        }
    }

}
