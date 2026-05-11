package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.model.Cart;
import it.progettosiw.pcexpress.model.CartItem;
import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.service.CartService;
import it.progettosiw.pcexpress.service.PCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private PCService pcService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // getFirstCart DA SOSTITUIRE CON getCartById
    @GetMapping("/user/cart")
    public String showCart(Model model){
        model.addAttribute("cart", this.cartService.getFirstCart());
        return "/user/cart.html";
    }

    // getFirstCart DA SOSTITUIRE CON getCartById
    @PostMapping("/user/cart/add")
    public String addToCart(@RequestParam Long pcId, @RequestParam Integer quantity) {
        PC pc = pcService.getPCById(pcId);

        CartItem cartItem = new CartItem();
        cartItem.setPc(pc);
        cartItem.setQuantity(quantity);

        Cart cart = cartService.getFirstCart();
        cartItem.setCart(cart);

        cartService.save(cartItem);
        return "redirect:/user/cart";
    }

}
