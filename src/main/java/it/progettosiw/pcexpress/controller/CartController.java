package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/user/cart")
    public String showCart(Model model){
        model.addAttribute("cart", this.cartService.getAllCartItems());
        return "/user/cart.html";
    }

}
