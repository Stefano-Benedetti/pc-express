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

    @GetMapping("/user/cart")
    public String showCart(Model model){
        model.addAttribute("cart", this.cartService.getCurrentUserCart());
        return "/user/cart.html";
    }

    @PostMapping("/user/cart/add")
    public String addToCart(@RequestParam Long pcId, @RequestParam Integer quantity) {
        cartService.addToCurrentUserCart(pcId, quantity);
        return "redirect:/user/cart";
    }

    @PostMapping("/user/cart/remove_cart_item")
    public String removeCartItemFromCart(@RequestParam Long pcId) {
        cartService.removeCartItemFromCurrentUserCart(pcId);
        return "redirect:/user/cart";
    }

    @PostMapping("/user/cart/remove_one")
    public String removeOneFromCart(@RequestParam Long pcId) {
        cartService.removeOneFromCurrentUserCart(pcId);
        return "redirect:/user/cart";
    }

}
